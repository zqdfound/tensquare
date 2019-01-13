package com.tensquare.service;

import com.tensquare.dao.SearchDao;
import com.tensquare.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    @Autowired
    private SearchDao articleSearchDao;
    /**
     * 增加文章
     * @param article
     */
    public void add(Article article){
        articleSearchDao.save(article);
    }

    /**
     * 查询文章
     * @param key 关键词
     * @param page
     * @param size
     * @return
     */
    public Page<Article> findByKey(String key, int page, int size) {
        Pageable pageable = PageRequest.of(page-1,size);
        return articleSearchDao.findByTitleorContentLike(key,key,pageable);
    }
}
