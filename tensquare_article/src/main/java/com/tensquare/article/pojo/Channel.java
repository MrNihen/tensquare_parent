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
@Table(name="tb_channel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Channel {

    @Id
    private String id;//ID
    private String name;//频道名称
    private String state;//状态
}
