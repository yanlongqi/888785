package top.yuchat.patch.exception;

public interface CommonException {

    /**
     * 获得错误信息编号
     *
     * @return
     */
    String getCode();

    /**
     * 获得错误信息
     *
     * @return
     */
    String getMessage();
}
