package top.yuchat.patch.common.netty.result;

import lombok.Data;

import java.util.List;

@Data
public class BeginPatch {

    /**
     * 安装路径
     */
    private String appPath;

    /**
     * 要扫描的文件路径
     */
    private List<String> scanPath;


}
