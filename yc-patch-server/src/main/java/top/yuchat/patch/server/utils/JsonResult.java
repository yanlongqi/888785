package top.yuchat.patch.server.utils;

import lombok.Data;
import top.yuchat.patch.server.exception.CommonException;
import top.yuchat.patch.server.exception.ServiceCodeEnum;

import java.io.Serializable;

@Data
public class JsonResult<T> implements Serializable {
    private int code;
    private String message;
    private T data;

    private JsonResult() {
    }

    /**
     * 全参构造
     *
     * @param code
     * @param message
     * @param data
     */
    private JsonResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private JsonResult(CommonException e, T data) {
        this.code = e.getCode();
        this.message = e.getMessage();
        this.data = data;
    }

    /**
     * 只有状态码和信息
     *
     * @param code
     * @param message
     */
    private JsonResult(int code, String message) {
        this(code, message, null);
    }


    private JsonResult(String message) {
        this(ServiceCodeEnum.OK.getCode(), message);
    }

    /**
     * 只有信息
     *
     * @param e
     */
    private JsonResult(CommonException e) {
        this(e, null);
    }


    /**
     * 请求成功
     *
     * @return
     */
    public static <T> JsonResult<T> ok() {
        return new JsonResult<>(ServiceCodeEnum.OK);
    }

    /**
     * 请求成功，返回数据
     *
     * @param data
     * @return
     */
    public static <T> JsonResult<T> ok(T data) {
        return new JsonResult<>(ServiceCodeEnum.OK, data);
    }

    /**
     * 请求成功，并自定义提示语句
     *
     * @param message
     * @return
     */
    public static <T> JsonResult<T> ok(String message) {
        return new JsonResult<>(message);
    }

    /**
     * 请求失败
     *
     * @return
     */
    public static JsonResult<?> error() {
        return new JsonResult<>(ServiceCodeEnum.ERROR);
    }

    /**
     * 请求失败附带信息
     *
     * @param e
     * @return
     */
    public static JsonResult<?> error(CommonException e) {
        return new JsonResult<>(e);
    }

    /**
     * 请求失败，自定义状态码和信息
     *
     * @param code
     * @param message
     * @return
     */
    public static JsonResult<?> error(int code, String message) {
        return new JsonResult<>(code, message);
    }

    /**
     * 请求失败，自定义信息
     *
     * @param message
     * @return
     */
    public static JsonResult<?> error(String message) {
        return new JsonResult<>(ServiceCodeEnum.ERROR.getCode(), message);
    }
}
