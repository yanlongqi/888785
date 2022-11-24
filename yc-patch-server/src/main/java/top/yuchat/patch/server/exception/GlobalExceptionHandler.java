package top.yuchat.patch.server.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.yuchat.patch.server.utils.JsonResult;

import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public JsonResult<?> serviceException(ServiceException e) {
        log.error("服务发生异常，异常信息：", e);
        return JsonResult.error(e.getCode(), e.getMessage());
    }


    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public JsonResult<?> authorizationException(AuthorizationException e) {
        log.error("授权发送异常，异常信息：", e);
        return JsonResult.error(ServiceCodeEnum.AUTH_FAILD);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult<?> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        StringBuilder builder = new StringBuilder();
        if (result.hasErrors()){
            List<ObjectError> errors = result.getAllErrors();
            if (CollectionUtils.isEmpty(errors)){
                errors.forEach(t->{
                    FieldError fieldError = (FieldError) t;
                    builder.append(fieldError.getDefaultMessage());
                });
            }
        }
        return JsonResult.error(builder.toString());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public JsonResult<?> serviceException(Exception e) {
        log.error("异常信息：", e);
        return JsonResult.error(ServiceCodeEnum.ERROR);
    }
}
