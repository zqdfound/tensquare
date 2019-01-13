package com.tensquare.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * 文章实体类
 */
@Document(indexName="articleindex",type="article")
public class Article {
    @Id
    private String id;//ID
    //是否索引，该域是否能被搜索
    //是否分词，是否按整个搜索条件搜索
    //是否存储，是否在页面上显示（例如文章内容）
    //analyzer，searchAnalyzer前后一致
    @Field(index= true,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
    private String title;//标题
    @Field(index= true,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
    private String content;//文章正文
    private String state;//审核状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
