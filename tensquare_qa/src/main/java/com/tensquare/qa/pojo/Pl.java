package com.tensquare.qa.pojo;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

/**
 * Creat by nihen on 2020/10/20 18:47
 */
@Entity
@Table(name = "tb_pl")
@Data
public class Pl implements Serializable {
    @Id
    private String problemid;
    @Id
    private String labelid;
}
