package cn.edu.nju.FindIt.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.nju.FindIt.model.Post;
import cn.edu.nju.FindIt.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = { "公告接口" })
@RequestMapping("/api/posts")
@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @ApiOperation(value = "公告列表", notes = "分页，搜索条件返回物品列表")
    @GetMapping("")
    public Collection<Post> postList(
        @ApiParam(value = "页数(默认为0)", required = false)
        @RequestParam(value = "page", required = false)
        Optional<Integer> page,

        @ApiParam(value = "天数", required = false)
        @RequestParam(value = "days", required = false)
        Optional<Integer> days,

        @ApiParam(value = "描述",required = false)
        @RequestParam(value = "description", required = false)
        Optional<String> description
    ){
        return postService.postList(page.orElse(0), days, description);
    }

    @ApiOperation(value = "公告详情", notes = "返回公告的详细信息")
    @GetMapping("/{id}")
    public Post postDetail(@PathVariable("id") Long id){
        return postService.findPostById(id);
    }
}
