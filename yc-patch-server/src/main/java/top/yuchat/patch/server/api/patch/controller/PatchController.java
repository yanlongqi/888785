package top.yuchat.patch.server.api.patch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yuchat.patch.server.api.patch.service.PatchService;
import top.yuchat.patch.server.exception.ServiceException;

@RestController
@RequestMapping("/patch")
public class PatchController {

    @Autowired
    private PatchService patchService;

    @GetMapping("/begin")
    public String beginPatch(){
        patchService.beginPatch();
        return "success";
    }
    @GetMapping("/eee")
    public String eee(){
        throw new ServiceException();
    }
}
