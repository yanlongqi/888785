package top.yuchat.patch.utils;


import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import top.yuchat.patch.constant.CmdEnum;
import top.yuchat.patch.constant.CommonConstant;
import top.yuchat.patch.constant.ErrorInfoEnum;
import top.yuchat.patch.constant.ParamsEnum;
import top.yuchat.patch.exception.ErrorException;

import java.util.Arrays;
import java.util.List;

/**
 * @authoer: yanlongqi
 * @createDate: 2022/6/20
 * @description:
 */
public class ArgsUtils {

    /**
     * 参数列表
     */

    private List<String> argsList;

    /**
     * 当前对象
     */

    private static ArgsUtils argsUtils = null;

    /**
     * 配置信息
     */
    private static JSONObject confInfo = null;

    private static final String DIR_PATH_KEY = "-po";
    private static final String TARGET_PATH_KEY = "-pt";


    private ArgsUtils(String[] args) {
        this.argsList = Arrays.asList(args);
    }

    public static ArgsUtils getInstance(String[] args) {
        if (argsUtils == null) {
            argsUtils = new ArgsUtils(args);
            try {
                confInfo = CommonUtils.reloadJSON(CommonConstant.CONF_PATH);
            } catch (Exception e) {
                throw new ErrorException(ErrorInfoEnum.CONF_FILE_LOAD_ERROR);
            }
        }
        return argsUtils;
    }

    /**
     * 获得一个命令
     *
     * @return
     */
    public CmdEnum getCmd() {
        return CmdEnum.getCmd(argsList.get(0));
    }

    /**
     * 获取参数
     *
     * @param paramsEnum
     * @return
     */
    public Object get(ParamsEnum paramsEnum) {
        return confInfo.get(paramsEnum.getName());
    }

    /**
     * 获取参数
     *
     * @param paramsEnum
     * @return
     */
    public String getString(ParamsEnum paramsEnum) {
        return confInfo.getString(paramsEnum.getName());
    }

    /**
     * 获取参数
     *
     * @param paramsEnum
     * @return
     */
    public Long getLong(ParamsEnum paramsEnum) {
        return confInfo.getLong(paramsEnum.getName());
    }

    /**
     * 获取参数
     *
     * @param paramsEnum
     * @return
     */
    public Integer getInteger(ParamsEnum paramsEnum) {
        return confInfo.getInteger(paramsEnum.getName());
    }

    /**
     * 获取参数
     *
     * @param paramsEnum
     * @return
     */
    public Double getDouble(ParamsEnum paramsEnum) {
        return confInfo.getDouble(paramsEnum.getName());
    }

    /**
     * 获取参数
     *
     * @param paramsEnum
     * @return
     */
    public Float getFloat(ParamsEnum paramsEnum) {
        return confInfo.getFloat(paramsEnum.getName());
    }

    public JSONArray getJSONArray(ParamsEnum paramsEnum) {
        return confInfo.getJSONArray(paramsEnum.getName());
    }


    public JSONObject getJSONObject(ParamsEnum paramsEnum){
        return confInfo.getJSONObject(paramsEnum.getName());
    }

    /**
     * 获取参数
     *
     * @param paramsEnum
     * @return
     */
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
