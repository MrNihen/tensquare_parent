package com.tensquare.base.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by nihen on 2020/10/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_label")
@Entity
public class Label {
    @Id
    private String id;//标签id
    private String labelname;//标签名称
    private String state;//状态
    private  Long count;//使用数量
    private Long fans;//关注数
    private String recommend;//是否推荐
}
