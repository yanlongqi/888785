package top.yuchat.patch.utils;

import com.alibaba.fastjson2.JSONArray;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import top.yuchat.patch.constant.ParamsEnum;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Params {


    private static final ArgsUtils instance = ArgsUtils.getInstance(null);
    public static final JSONArray scanFolderArray = instance.getJSONArray(ParamsEnum.SCAN_FOLDER);

    /**
     * 安装基础路径
     */
    public static final String appBasePath = instance.getString(ParamsEnum.APP_BASE_PATH);

    /**
     * patch基础路径
     */
    public static final String patchBasePatch = instance.getString(ParamsEnum.PATCH_BASE_PATH);

    /**
     * app版本
     */
    public static final String appVersion = instance.getString(ParamsEnum.APP_VERSION);

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");


    /**
     * 描述文件路径
     *
     * @param projectFolder 项目文件夹
     * @param patchVersion  版本号
     * @return
     */
    public static String getPatchDescriptionPath(String projectFolder, String patchVersion) {
        String str = getPatchDescriptionFileName(projectFolder, patchVersion);
        return patchBasePatch + File.separator + projectFolder + File.separator + str;
    }


    /**
     * 描述文件名称
     *
     * @param projectFolder
     * @param patchVersion
     * @return
     */
    public static String getPatchDescriptionFileName(String projectFolder, String patchVersion) {
        return getPatchDirName(projectFolder, patchVersion) + ".json";
    }

    /**
     * patch文件夹名称
     *
     * @param projectFolder
     * @param patchVersion
     * @return
     */
    public static String getPatchDirName(String projectFolder, String patchVersion) {
        return "patch_" + projectFolder + "-" + patchVersion;
    }


    /**
     * patch文件路径
     *
     * @param projectName
     * @param patchVersion
     * @return
     */
    public static String getPatchDirPath(String projectName, String patchVersion) {
        return patchBasePatch + File.separator + getProjectFolder(projectName) + File.separator + getPatchDirName(projectName, patchVersion);
    }


    /**
     * app安装路径
     *
     * @param projectName
     * @return
     */
    public static String getAppsInstallPath(String projectName) {
        if (StringUtils.isBlank(projectName)) {
            projectName = instance.getString(ParamsEnum.PROJECT_NAME);
        }
        return appBasePath + File.separator + "appform-" + projectName;
    }

    /**
     * patch项目文件夹名称
     *
     * @param projectName
     * @return
     */
    public static String getProjectFolder(String projectName) {
        return "JH_Appform_" + appVersion + "_Release_" + projectName;
    }

    /**
     * 获得一个描述文件
     *
     * @param projectFolder
     * @return
     */
    public static String getDescriptionFile(String projectFolder) {
        return "patch_" + projectFolder + "-" + format.format(new Date()) + ".json";
    }

    /**
     * 获得描述文件
     *
     * @param projectFolder
     * @param descriptionFile
     * @return
     */

    public static String getDescriptionPath(String projectFolder, String descriptionFile) {
        return patchBasePatch + File.separator + projectFolder + File.separator + descriptionFile;
    }
}
