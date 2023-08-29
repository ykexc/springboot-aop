package com.ykexc.springbootaop.controller;

import com.ykexc.springbootaop.annotation.GlobalInterceptor;
import com.ykexc.springbootaop.annotation.LogHandle;
import com.ykexc.springbootaop.annotation.LoginVerify;
import com.ykexc.springbootaop.annotation.VerifyParam;
import com.ykexc.springbootaop.entity.RestBean;
import com.ykexc.springbootaop.entity.vo.req.TestReq;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import static com.ykexc.springbootaop.enums.regex.VerifyRegexEnum.EMAIL;
import static com.ykexc.springbootaop.utils.Const.ADMIN;
import static com.ykexc.springbootaop.utils.Const.LOGIN;

/**
 * @author mqz
 */
@RestController
public class MainController {

    @GetMapping("/")
    @LoginVerify
    public RestBean<Void> test() {
        return RestBean.success("ok");
    }

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

    @PostMapping("/login")
    public RestBean<Void> test4(@RequestParam String username, HttpServletRequest request) {
        request.getSession().setAttribute(LOGIN, username);
        return RestBean.success("ok");
    }

    @LogHandle
    @GetMapping("/test4")
    public RestBean<Void> test5(HttpServletRequest request, @RequestParam String username) {
        request.getSession().setAttribute(ADMIN, username);
        return RestBean.success("ok");
    }

    @LoginVerify(value = false, admin = true)
    @GetMapping("/test6")
    @LogHandle
    public RestBean<Void> test6() {
        return RestBean.success("ok");
    }
}
