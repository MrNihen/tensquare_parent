package com.tensquare.spit.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Creat by nihen on 2020/10/21 20:12
 */
@Data
public class Spit {
    @Id
    private String _id;
    private String content;
    private Date publishtime;
    private String userid;
    private String nickname;
    private Integer visits;
    private Integer thumbup;
    private Integer share;
    private Integer comment;
    private String state;
    private String parentid;
}
