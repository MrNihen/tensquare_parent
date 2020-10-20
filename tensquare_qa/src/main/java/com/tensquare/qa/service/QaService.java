package com.tensquare.qa.service;


import com.tensquare.qa.dao.QaDao;
import com.tensquare.qa.pojo.Problem;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Creat by nihen on 2020/10/20 18:45
 */
@Service
public class QaService {
    @Autowired
    private QaDao qaDao;

    public PageResult<Problem> newList(String id, int page, int size) {
        //1.1)查询分页对象
        Page<Problem> problemPage = qaDao.newlist(id, PageRequest.of(page - 1, size));
        //1.2)返回pageResult对象
        return new PageResult<>(problemPage.getTotalElements(), problemPage.getContent());
    }

    //2.查询热门问题列表
    public PageResult<Problem> hotlist(String id, int page, int size) {
        //2.1)查询分页对象
        Page<Problem> problemPage = qaDao.hotlist(id, PageRequest.of(page - 1, size));
        //3.2)返回pageResult对象
        return new PageResult<>(problemPage.getTotalElements(), problemPage.getContent());
    }
}
