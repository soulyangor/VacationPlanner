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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ¬€чеслав
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
        /* Post post1 = new Post("engineer");
        Post post2 = new Post("оператор");
        Post post3 = new Post("programmer");
        postService.save(post1);
        postService.save(post2);
        postService.save(post3);*/
        List<Post> result = postService.findAllWithDetails();
        for (Post post : result) {
            System.out.println(post);
        }
        return result;
    }

}
