/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vacationplanner.controller;

import static com.mycompany.vacationplanner.controller.PostRestController.POSTS_PATH;
import com.mycompany.vacationplanner.model.Post;
import com.mycompany.vacationplanner.service.PostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ֲÿקוסכאג
 */
@RestController
@RequestMapping(path = POSTS_PATH,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class PostRestController {

    public static final String POSTS_PATH = "/posts";
    public static final String ITEM_PATH = "/item";

    private PostService postService;

    @Autowired(required = false)
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Post> getPostList() {
        return postService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST,
            path = ITEM_PATH,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Post createPost(@RequestBody Post post) {
        return postService.save(post);
    }

    @RequestMapping(method = RequestMethod.PUT,
            path = ITEM_PATH,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Post updatePost(@RequestBody Post post) {
        return postService.save(post);
    }

    @RequestMapping(method = RequestMethod.DELETE,
            path = ITEM_PATH,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deletePost(@RequestBody Post post) {
        postService.delete(post);
    }

}
