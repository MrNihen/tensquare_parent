package com.tensquare.article.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Creat by nihen on 2020/10/20 19:23
 */
@Entity
@Table(name="tb_column")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Column {
    @Id
    private String id;//ID
    private String name;//专栏名称
    private String summary;//专栏简介
    private String userid;//用户ID
    private java.util.Date createtime;//申请日期
    private java.util.Date checktime;//审核日期
    private String state;//状态
}
