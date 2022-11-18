package top.yuchat.patch.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @authoer: yanlongqi
 * @createDate: 2022/11/18
 * @description: 参数
 */
@Getter
@AllArgsConstructor
public enum ParamsEnum {

    SCAN_FOLDER("scanFolder", "扫描的文件夹"),
    INCLUDE("include", "扫描的文件夹"),
    IGNORES("ignores", "扫描的文件夹"),
    APP_BASE_PATH("appBasePath", "应用安装基础路径"),
    PATCH_BASE_PATH("patchBasePath", "patch存放基础路径"),
    TEMPLATE("template", "模板文件路径"),
    PROJECT_NAME("project_name", "项目名称"),
    APP_VERSION("app_version", "应用版本号");

    /**
     * 参数名称
     */
    private String name;

    /**
     * 参数信息
     */
    private String message;
}
