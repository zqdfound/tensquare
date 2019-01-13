package com.tensquare.controller;

import com.tensquare.pojo.Article;
import com.tensquare.service.SearchService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleSearchController {

    @Autowired
    private SearchService articleSearchService;

    @RequestMapping(method= RequestMethod.POST)
    public Result save(@RequestBody Article article){
        articleSearchService.add(article);
        return new Result(true, StatusCode.OK, "操作成功");
    }

    @RequestMapping(value="/{key}/{page}/{size}",method = RequestMethod.GET)
    public Result findKey(@PathVariable String key,@PathVariable int page,@PathVariable int size){
        Page<Article> pages = articleSearchService.findByKey(key,page,size);
        return new Result(true, StatusCode.OK, "操作成功",new PageResult<Article>(pages.getTotalElements(),pages.getContent()));
    }


}
