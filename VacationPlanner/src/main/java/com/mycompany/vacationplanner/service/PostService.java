/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vacationplanner.service;

import com.mycompany.vacationplanner.model.Post;
import java.util.List;

/**
 *
 * @author ֲÿקוסכאג
 */
public interface PostService {

    List<Post> findAll();

    List<Post> findAllWithDetails();

    Post save(Post post);

    void delete(Post post);

}
