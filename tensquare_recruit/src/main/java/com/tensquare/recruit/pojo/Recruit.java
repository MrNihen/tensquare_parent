package com.tensquare.recruit.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Creat by nihen on 2020/10/20 18:16
 */
@Entity
@Table(name = "tb_recruit")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recruit {
    @Id
    private String id;
    private String jobname;
    private String salary;
    private String condition;
    private String education;
    private String type;
    private String address;
    private String createtime;
    private String eid;
    private String state;
    private String label;
    private String url;
    private String content1;
    private String  content2;

    public Recruit(String jobname, String salary, String condition, String education, String type, String address, String createtime, String eid, String state, String label, String url, String content1, String content2) {
        this.jobname = jobname;
        this.salary = salary;
        this.condition = condition;
        this.education = education;
        this.type = type;
        this.address = address;
        this.createtime = createtime;
        this.eid = eid;
        this.state = state;
        this.label = label;
        this.url = url;
        this.content1 = content1;
        this.content2 = content2;
    }
}
