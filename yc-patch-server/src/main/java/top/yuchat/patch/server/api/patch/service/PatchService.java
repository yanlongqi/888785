package top.yuchat.patch.server.api.patch.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yuchat.patch.common.netty.result.BeginPatch;
import top.yuchat.patch.server.api.patch.entity.PathDescription;
import top.yuchat.patch.server.api.patch.mapper.PathDescriptionMapper;
import top.yuchat.patch.server.netty.ChannelManage;
import top.yuchat.patch.server.netty.UserChannel;
import top.yuchat.patch.server.netty.actuator.BeginActuator;

import java.util.Arrays;

@Service
public class PatchService extends ServiceImpl<PathDescriptionMapper, PathDescription> {


    public void beginPatch() {

        BeginActuator beginActuator = new BeginActuator();
        BeginPatch beginPatch = new BeginPatch();
        beginPatch.setAppPath("D:\\plus\\JH_Appform_V5.3.1_Linux_x64_r20408.tar\\appform");
        beginPatch.setScanPath(Arrays.asList("conf","tomcat","etc"));
        beginActuator.setBeginPatch(beginPatch);
        UserChannel lqyan = ChannelManage.getChannelManage().getUserChannel("lqyan");
        lqyan.exec(beginActuator);
    }

}
