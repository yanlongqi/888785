package top.yuchat.patch.common.netty.result;

import lombok.Data;

@Data
public class PathDescriptionInfo {

    /**
     * 文件路径
     */
    private String path;

    /**
     * md5
     */
    private String md5;

}
