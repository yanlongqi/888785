package top.yuchat.patch.common.netty.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemcachedRequest {

    /**
     * 命令号
     */
    private int cmd;



    private byte[] data;

}
