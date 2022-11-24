package top.yuchat.patch.server.exception;

import lombok.Data;


public class ServiceException extends RuntimeException implements CommonException{

    private int code;

    public ServiceException(){
        super(ServiceCodeEnum.ERROR.getMessage());
        this.code = ServiceCodeEnum.ERROR.getCode();
    }


    public ServiceException(CommonException e) {
        super(e.getMessage());
        this.code = e.getCode();
    }

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }
}
