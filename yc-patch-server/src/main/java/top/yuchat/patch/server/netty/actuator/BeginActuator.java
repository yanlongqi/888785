package top.yuchat.patch.server.netty.actuator;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import top.yuchat.patch.common.netty.actuator.Actuator;
import top.yuchat.patch.common.netty.actuator.BeginBaseActuator;
import top.yuchat.patch.common.netty.result.BeginPatch;
import top.yuchat.patch.common.netty.result.MemcachedRequest;
import top.yuchat.patch.common.netty.result.PathDescriptionInfo;
import top.yuchat.patch.server.api.patch.service.PatchService;
import top.yuchat.patch.server.utils.SpringContextUtil;

@Slf4j
@Actuator
public class BeginActuator extends BeginBaseActuator {

    private BeginPatch beginPatch;

    private PathDescriptionInfo pathDescriptionInfo;

    @Override
    public void run() {
        PatchService patchService = SpringContextUtil.getBean(PatchService.class);
        log.info("文件描述信息：{}", pathDescriptionInfo);
    }

    @Override
    public void setParams(byte[] params) {
        pathDescriptionInfo = JSON.parseObject(params, PathDescriptionInfo.class);
    }

    public void setBeginPatch(BeginPatch beginPatch) {
        this.beginPatch = beginPatch;
    }

    @Override
    public void exec(Channel channel) {
        MemcachedRequest memcachedRequest = new MemcachedRequest();
        memcachedRequest.setCmd(cmd());
        memcachedRequest.setData(JSON.toJSONBytes(beginPatch));
        channel.writeAndFlush(memcachedRequest);
    }
}
