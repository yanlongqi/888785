package top.yuchat.patch.utils;

import java.util.Arrays;
import java.util.List;

/**
 * @authoer: yanlongqi
 * @createDate: 2022/6/20
 * @description:
 */
public class ArgsUtils {

    private List<String> argsList;
    private static final String DIR_PATH_KEY = "-po";
    private static final String TARGET_PATH_KEY = "-pt";

    public ArgsUtils(String[] args) {
        this.argsList = Arrays.asList(args);
    }


    public String getCmd() {
        return this.argsList.get(0);
    }

    public String getDirPath() {
        int dirIndex = argsList.indexOf(DIR_PATH_KEY);
        if (dirIndex == -1) {
            throw new RuntimeException();
        }
        return this.argsList.get(dirIndex + 1);
    }

    public String getTargetPath() {
        int targetIndex = argsList.indexOf(TARGET_PATH_KEY);
        if (targetIndex == -1) {
            throw new RuntimeException();
        }
        return this.argsList.get(targetIndex + 1);
    }


}
