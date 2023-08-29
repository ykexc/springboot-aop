package com.ykexc.springbootaop.enums.regex;

import lombok.Getter;
import lombok.Setter;

/**
 * @author mqz
 */
@Getter
public enum VerifyRegexEnum {


    NO("", "不校验"),
    EMAIL("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", "邮箱校验"),

    PASSWORD("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$", "密码校验");


    private final String regex;

    private final String desc;

    VerifyRegexEnum(String _regex, String _desc) {
        this.regex = _regex;
        this.desc = _desc;
    }


}
