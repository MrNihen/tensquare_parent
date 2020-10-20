package com.tensquare.qa.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Creat by nihen on 2020/10/20 18:57
 */
@Entity
@Table(name = "tb_problem")
@Data
public class Problem implements Serializable {
    @Id
    private String id;
    private String title;
    private String content;
    private java.sql.Timestamp createtime;
    private java.sql.Timestamp updatetime;
    private String userid;
    private String nickname;
    private long visits;
    private long thumbup;
    private long reply;
    private String solve;
    private String replyname;
    private java.sql.Timestamp replytime;
}
