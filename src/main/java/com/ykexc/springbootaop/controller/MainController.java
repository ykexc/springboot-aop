package com.ykexc.springbootaop.controller;

import com.ykexc.springbootaop.annotation.GlobalInterceptor;
import com.ykexc.springbootaop.annotation.VerifyParam;
import com.ykexc.springbootaop.entity.RestBean;
import com.ykexc.springbootaop.entity.vo.req.TestReq;
import org.springframework.web.bind.annotation.*;

import static com.ykexc.springbootaop.enums.regex.VerifyRegexEnum.EMAIL;

/**
 * @author mqz
 */
@RestController
public class MainController {

    @GlobalInterceptor(true)
    @GetMapping("/test1")
    public RestBean<String> test1(@RequestParam @VerifyParam(min = 5) String code) {
        return RestBean.success("ok");
    }

    @GlobalInterceptor(true)
    @GetMapping("/test2")
    public RestBean<String> test2(@RequestParam @VerifyParam(regex = EMAIL) String email) {
        return RestBean.success("ok");
    }

    @GlobalInterceptor(true)
    @PostMapping("/test3")
    public RestBean<String> test3(@RequestBody @VerifyParam TestReq req) {
        return RestBean.success("ok");
    }

}
