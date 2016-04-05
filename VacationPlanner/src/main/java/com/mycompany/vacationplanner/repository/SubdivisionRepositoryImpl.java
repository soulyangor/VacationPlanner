/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vacationplanner.repository;

import com.mycompany.vacationplanner.model.Subdivision;
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
@Service("jpaSubdivisionService")
@Transactional
@Repository
public class SubdivisionRepositoryImpl implements SubdivisionRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Subdivision> findAll() {
        return em.createNamedQuery("Subdivision.findAll", Subdivision.class)
                .getResultList();
    }

}
