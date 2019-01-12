package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
    }

    /**
     * 根据ID查询
     * @param id ID
     * @return
     */
    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public Result findOne(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成 功",spitService.findById(id));
    }
    /**
     * 增加
     * @param spit
     */
    @RequestMapping(method=RequestMethod.POST)
    public Result add(@RequestBody Spit spit ){
        spitService.save(spit);
        return new Result(true,StatusCode.OK,"增加成功");
    }

    /** 修改
    * @param spit
    */
    @RequestMapping(value="/{id}",method=RequestMethod.PUT)
    public Result update(@RequestBody Spit spit,@PathVariable String id )
    {
        spit.set_id(id);
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"修改成功");
    }
    /**
     * 删除
     * @param id
     */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id ){
        spitService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    @RequestMapping(value="/comment/{parentId}/{page}/{size}",method=RequestMethod.GET)
    public Result findByParentId(@PathVariable String parentId, @PathVariable int page, @PathVariable int size){
        Page<Spit> pageList = spitService.findByParentid(parentId,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Spit>(pageList.getTotalElements(), pageList.getContent() ) );
    }

    /**
     * 点赞
     * @param id
     * @return
     */
    @RequestMapping(value="/thumbup/{id}",method=RequestMethod.PUT)
    public Result updateThumbup(@PathVariable String id){
        String userId = "9527";
        //判断该用户是否已经点赞
        if(null != redisTemplate.opsForValue().get("thumb_"+userId+"_"+id)){
            return new Result(false,StatusCode.REPERROR,"你已经点过赞");
        }
        spitService.updateThumbup(id);
        redisTemplate.opsForValue().set("thumb_"+userId+"_"+id,"1");
        return new Result(true,StatusCode.OK,"点赞成功");
    }

}
