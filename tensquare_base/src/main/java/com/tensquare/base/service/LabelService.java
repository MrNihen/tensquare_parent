package com.tensquare.base.service;

import cn.hutool.core.util.StrUtil;
import com.tensquare.base.pojo.Label;
import com.tensquare.base.dao.LabelDao;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import util.IdWorker;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nihen on 2020/10/19
 */
@Service
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;
    //1.查询所有标签
    public List<Label> findAll() {
        return labelDao.findAll();
    }
    //2.根据id查询标签
    public Label findById(String labelId) {
        return labelDao.findById(labelId).get();
    }
    //3.添加标签
    public void add(Label label) {
        //3.1)设置标签id
        long nextId = idWorker.nextId();
        label.setId(nextId + "");
        //3.2)保存标签
        labelDao.save(label);
    }
    //4.修改标签成功
    public void update(Label label) {
        labelDao.save(label);
    }
    //5.删除标签
    public void delete(String labelId) {
        labelDao.deleteById(labelId);
    }
    //6.条件查询
    public List<Label> search(Label label) {
        //6.1)创建查询条件
        Specification<Label> specification= createSpecification(label);
        //6.2)返回条件查询结果
        return labelDao.findAll(specification);
    }
    //7.创建查询条件
    //HQL：select st.* from Student st; --->  new Student(sid,sname,sex,age,addr,cid);
    private Specification<Label> createSpecification(Label label) {
        return new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> Query, CriteriaBuilder cb) {
                //7.1)定义存放查询条件的集合
                List<Predicate> predicateList = new ArrayList<>();
                //7.2)向集合中添加查询条件
                if(label != null){
                    if(StrUtil.isNotBlank(label.getLabelname())){       //模糊查询
                        predicateList.add(cb.like(root.get("labelname").as(String.class),"%" + label.getLabelname() + "%"));
                    }
                    if(StrUtil.isNotBlank(label.getState())){           //等值查询
                        predicateList.add(cb.equal(root.get("state").as(String.class),label.getState()));
                    }
                    if(StrUtil.isNotBlank(label.getRecommend())){       //等值查询
                        predicateList.add(cb.equal(root.get("recommend").as(String.class),label.getRecommend()));
                    }
                }
                //7.3)定义泛型数组
                Predicate[] preArrs = new Predicate[predicateList.size()];
                //7.4)将条件集合转换为数组
                Predicate[] predicates = predicateList.toArray(preArrs);
                //7.5)返回查询条件
                return cb.and(predicates);
            }
        };
    }
    //8.条件查询带分页
    public PageResult<Label> search(Label label, int page, int size) {
        //8.1)分页查询
        Page<Label> labelPage = labelDao.findAll(createSpecification(label), PageRequest.of(page-1, size));
        //8.2)返回
        return  new PageResult<>(labelPage.getTotalElements(),labelPage.getContent());
    }
}
