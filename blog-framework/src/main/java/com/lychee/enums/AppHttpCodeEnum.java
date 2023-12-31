package com.lychee.enums;

import lombok.Getter;

@Getter
public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200, "操作成功"),
    // 登录
    NEED_LOGIN(401, "需要登录后操作"),
    NO_OPERATOR_AUTH(403, "没有操作权限"),
    SYSTEM_ERROR(500, "系统错误"),
    USERNAME_EXIST(501, "用户名已存在"),
    PHONE_EXIST(502, "手机号已存在"),
    EMAIL_EXIST(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "用户名不能为空"),
    LOGIN_ERROR(505, "用户名或密码错误"),
    CONTENT_NOT_NULL(506, "评论内容不能为空"),
    FILE_TYPE_ERROR(507, "文件格式错误，请上传png格式的图片"),
    USERNAME_NOT_NULL(508, "用户名不能为空"),
    PASSWORD_NOT_NULL(509, "密码不能为空"),
    EMAIL_NOT_NULL(510, "邮箱不能为空"),
    NICKNAME_NOT_NULL(511, "昵称不能为空"),
    NICKNAME_EXIST(512, "昵称已存在"),
    USER_NOT_EXIST(513, "用户不存在"),;
    final int code;
    final String msg;

    AppHttpCodeEnum (int code, String errorMsg) {
        this.code = code;
        this.msg = errorMsg;
    }

}
