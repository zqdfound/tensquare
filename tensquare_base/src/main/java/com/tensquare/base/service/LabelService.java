package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll(){
        return labelDao.findAll();
    }

    public Label findById(String id){
        return labelDao.findById(id).get();
    }

    public void add(Label label){
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }
    //更新
    public void update(Label label){
        //save方法既能保存又能新增
        labelDao.save(label);
    }
    public void deleteById(String id){
        labelDao.deleteById(id);
    }

    /*
    条件查询
     */
    public List<Label> findSearch(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根对象
             * @param query 查询关键字
             * @param cb 封装条件对象
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new LinkedList<Predicate>();
                if(null != label.getLabelname() && !"".equals(label.getLabelname())){
                    predicates.add(cb.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%"));//相当于where labelname like '%XXX%'
                }
                if(null != label.getState() && !"".equals(label.getState())){
                    predicates.add(cb.equal(root.get("state").as(String.class),"%"+label.getState()+"%"));
                }
                if(null != label.getRecommend() && !"".equals(label.getRecommend())){
                    predicates.add(cb.equal(root.get("recommend").as(String.class),"%"+label.getRecommend()+"%"));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

    public Page<Label> queryPage(int page,int size,Label label) {
        Pageable pageable = PageRequest.of(page-1,size);
        return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根对象
             * @param query 查询关键字
             * @param cb 封装条件对象
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new LinkedList<Predicate>();
                if(null != label.getLabelname() && !"".equals(label.getLabelname())){
                    predicates.add(cb.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%"));//相当于where labelname like '%XXX%'
                }
                if(null != label.getState() && !"".equals(label.getState())){
                    predicates.add(cb.equal(root.get("state").as(String.class),"%"+label.getState()+"%"));
                }
                if(null != label.getRecommend() && !"".equals(label.getRecommend())){
                    predicates.add(cb.equal(root.get("recommend").as(String.class),"%"+label.getRecommend()+"%"));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        },pageable);

    }
}
