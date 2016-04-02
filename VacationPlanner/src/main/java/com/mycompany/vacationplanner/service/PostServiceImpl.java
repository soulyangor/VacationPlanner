/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vacationplanner.service;

import com.mycompany.vacationplanner.model.Post;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ֲÿקוסכאג
 */
@Service("jpaPostService")
@Transactional
@Repository
public class PostServiceImpl implements PostService {

    @PersistenceContext
    private EntityManager em;

    public List<Post> findAll() {
        return em.createNamedQuery("Post.findAll").getResultList();
    }

    public List<Post> findAllWithDetails() {
        return em.createNamedQuery("Post.findAllWithDetail").getResultList();
    }

    public Post save(Post post) {
        if (post.getId() == null) {
            em.persist(post);
        } else {
            em.merge(post);
        }
        return post;
    }

    public void delete(Post post) {
        Post mergedPost = em.merge(post);
        em.remove(mergedPost);
    }

}
