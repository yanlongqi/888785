package top.yuchat.patch.client.netty.actuator;

import com.alibaba.fastjson2.JSON;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import top.yuchat.patch.client.netty.NettyStart;
import top.yuchat.patch.common.netty.actuator.Actuator;
import top.yuchat.patch.common.netty.actuator.BeginBaseActuator;
import top.yuchat.patch.common.netty.result.BeginPatch;
import top.yuchat.patch.common.netty.result.MemcachedRequest;
import top.yuchat.patch.common.netty.result.PathDescriptionInfo;
import top.yuchat.patch.common.utils.Md5Util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Actuator
public class BeginActuator extends BeginBaseActuator {

    private BeginPatch beginPatch;
    PathDescriptionInfo descriptionInfo;

    @Override
    public void setParams(byte[] params) {
        beginPatch = JSON.parseObject(params, BeginPatch.class);
    }

    @Override
    public void run() {
        log.info("补丁信息：{}", beginPatch);
        List<String> paths = beginPatch.getScanPath()
                .stream()
                .map(t -> beginPatch.getAppPath() + File.separator + t)
                .collect(Collectors.toList());

        try {
            for (String path : paths) {
                List<Path> filePath = Files.walk(Paths.get(path)).filter(Files::isRegularFile).collect(Collectors.toList());
                for (Path p : filePath) {
                    File file = p.toFile();
                    String md5 = Md5Util.getMD5(file);
                    descriptionInfo = new PathDescriptionInfo();
                    descriptionInfo.setPath(file.getAbsolutePath());
                    descriptionInfo.setMd5(md5);
                    exec(NettyStart.getActiveChannel());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exec(Channel channel) {
        log.info("发送补丁信息：{}", descriptionInfo);
        MemcachedRequest memcachedRequest  = new MemcachedRequest();
        memcachedRequest.setCmd(cmd());
        memcachedRequest.setData(JSON.toJSONBytes(descriptionInfo));
        channel.writeAndFlush(memcachedRequest);
    }
}
