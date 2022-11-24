package top.yuchat.patch.server.api.oauth.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.yuchat.patch.server.api.oauth.dto.LoginDTO;
import top.yuchat.patch.server.api.oauth.service.AuthService;
import top.yuchat.patch.server.api.oauth.vo.TokenVO;
import top.yuchat.patch.server.utils.JsonResult;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    @PostMapping("/login")
    public JsonResult<TokenVO> login(@RequestBody @Validated LoginDTO login){
        TokenVO token = authService.login(login);
        return JsonResult.ok(token);
    }


}
