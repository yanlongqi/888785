package top.yuchat.patch.patch.exec;

import top.yuchat.patch.utils.ArgsUtils;

import java.io.IOException;
import java.util.Map;

public interface IPatchExec {


    /**
     * 执行命令
     */
    public abstract void run(String ...args);


}
