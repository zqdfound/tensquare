package com.tensquare.dao;

import com.tensquare.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 文章数据访问层接口
 */
public interface SearchDao extends ElasticsearchRepository<Article,String> {

    public Page<Article> findByTitleorContentLike(String title, String content, Pageable pageable);
}
