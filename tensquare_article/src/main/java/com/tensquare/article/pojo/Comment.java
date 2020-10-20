package com.tensquare.article.pojo;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

/**
 * Creat by nihen on 2020/10/20 19:24
 */
@Data
public class Comment {
    @Id
    private String _id;
    private String articleid;
    private String content;
    private String userid;
    private String parentid;
    private Date publishdate;
}

