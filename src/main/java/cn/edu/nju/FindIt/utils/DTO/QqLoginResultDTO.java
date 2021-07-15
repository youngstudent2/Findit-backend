package cn.edu.nju.FindIt.utils.DTO;

import lombok.Data;

@Data
public class QqLoginResultDTO {
    private String openid;
    private String session_key;
    private String errcode;
    private String errmsg;
}
