package com.ykexc.springbootaop.entity.vo.req;

import com.ykexc.springbootaop.annotation.VerifyParam;

import static com.ykexc.springbootaop.enums.regex.VerifyRegexEnum.EMAIL;

/**
 * @author mqz
 */
public record TestReq(
        @VerifyParam(max = 4) String code,

        @VerifyParam(regex = EMAIL) String email
) {
}
