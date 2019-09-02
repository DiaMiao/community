package de.zhao.community.dto;

import lombok.Data;

//根据Github 文档，此处创建DIO包（data transfer object)，用来封装对象。
// redirect需要传入5个参数，所以创建该对象
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
