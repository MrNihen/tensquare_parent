package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Creat by nihen on 2020/10/20 18:31
 */
public interface RecruitDao extends JpaRepository<Recruit,String> {
    //1.查询推荐职位列表
    List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state);

    //2.查询最新职位列表
    List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state);
}
