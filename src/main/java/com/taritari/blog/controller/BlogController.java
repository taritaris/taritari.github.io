package com.taritari.blog.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.api.R;

import com.sun.org.apache.regexp.internal.RE;
import com.taritari.blog.common.Result;
import com.taritari.blog.entity.Blog;
import com.taritari.blog.service.BlogService;
import com.taritari.blog.util.ShiroUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Author: @taritari
 * @since 2021-09-17
 */
@RestController
@RequestMapping("/blog")
@Api(tags = "博客管理模块")
@Slf4j
public class BlogController {
    @Autowired
    private BlogService blogService;
    /**
     * 分页接口
     * Integer currentPage 分页参数
     * @RequestParam(defaultValue = "1") 当没有传值的时候默认为第一页
     * import com.baomidou.mybatisplus.core.metadata.IPage;
     * import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
     * orderByDesc 倒序
     *
     * 没有添加@RequiresAuthentication的接口都可以公开进行访问
     * */
    @ApiOperation("分页接口")
    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1")Integer currentPage){
        Page page = new Page(currentPage,5);
        IPage pageDate= blogService.page(page,new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.succ(pageDate);
    }
    /**
     * 文章查询接口
     * */
    @ApiOperation("文章查询接口")
    @GetMapping("/{id}")
    public Result detail(@PathVariable(name = "id")Long id){
        Blog byId = blogService.getById(id);
        Assert.notNull(byId,"该博客已删除");
        return Result.succ(byId);
    }
    /**
     *  编辑和添加是同一体的
     *  @RequiresAuthentication 需要认证权限才能编辑
     * @Validated 校验
     * @RequestBody 从请求体里面获取参数过来
     * @RequestBody Blog blog  如果blog传过来有id则为可编辑；如果没有 则为可添加
     * */
    @ApiOperation("编辑和添加接口")
    @RequiresAuthentication
    @PostMapping("/edit")
    public Result edit(@Validated @RequestBody Blog blog){
        log.info("运行edit:传输值为------>[{}]",blog);
        Blog temp = null;
        //编辑
        if (blog.getId()!=null){
            temp=blogService.getById(blog.getId());
            log.info("执行编辑:文章id为--------->[{}]", ShiroUtil.getProfile().getId());
            Assert.isTrue(temp.getUserId()==ShiroUtil.getProfile().getId(),"没有编辑权限");
        }else {
            temp=new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }
        // 将 blog里的title、description、content三个字段赋到新的对象temp里面 进行保存编辑   剩下的忽略
        BeanUtil.copyProperties(blog,temp,"id","userId","created", "status");
        blogService.saveOrUpdate(temp);
        return Result.succ("操作成功",null);
    }
    /**
     * 文章删除接口
     * */
    @ApiOperation(value = "根据博客ID删除博客",notes = "author：taritari")
    @DeleteMapping
    public Result deleteBlog(Long id){
        boolean flag = blogService.removeById(id);
        return Result.succ("删除成功");
    }
}
