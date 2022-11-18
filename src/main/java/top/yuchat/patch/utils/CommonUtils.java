package top.yuchat.patch.utils;

import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @authoer: yanlongqi
 * @createDate: 2022/11/18
 * @description: 公共的工具类
 */
public class CommonUtils {


    /**
     * 加载一个json文件
     *
     * @param jsonFilePath JSON文件路径
     * @return 加载好的JSON文件
     */
    public static JSONObject reloadJSON(String jsonFilePath) throws IOException {
        return reloadJSON(new File(jsonFilePath));
    }

    /**
     * 加载一个json文件
     *
     * @param file JSON文件
     * @return 加载好的JSON文件
     */
    public static JSONObject reloadJSON(File file) throws IOException {
        String str = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        return JSONObject.parseObject(str);
    }

}
