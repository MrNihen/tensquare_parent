package com.tensquare.qa.dao;


import com.tensquare.qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Creat by nihen on 2020/10/20 18:52
 */
public interface QaDao extends JpaRepository<Problem,String> {
    //1.最新问答列表（使用的是本地查询，默认使用HQL查询）
    @Query(nativeQuery=true,value="select * from tb_problem p,tb_pl pl where pl.problemid=p.id and labelid=?1")
    Page<Problem> newlist(String id, Pageable pageable);

    //2.查询热门问题列表
    @Query(nativeQuery=true,value="select * from tb_problem p,tb_pl pl where pl.problemid=p.id order by reply desc")
    Page<Problem> hotlist(String id, Pageable pageable);
}
