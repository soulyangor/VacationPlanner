/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vacationplanner.repository;

import com.mycompany.vacationplanner.model.CalendarPeriod;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ֲÿקוסכאג
 */
@Service("jpaCalendarPeriodService")
@Transactional
@Repository
public class CalendarPeriodRepositoryImpl implements CalendarPeriodRepository {

    private static final long UPDATE_PERIOD = 86400 * 1000;

    @PersistenceContext
    private EntityManager em;

    public List<CalendarPeriod> findAll() {
        return em.createNamedQuery("CalendarPeriod.findAll").getResultList();
    }

    public CalendarPeriod save(CalendarPeriod calendarPeriod) {
        if (calendarPeriod.getId() == null) {
            em.persist(calendarPeriod);
        } else {
            CalendarPeriod period = em.
                    createNamedQuery("CalendarPeriod.findById", CalendarPeriod.class).
                    setParameter("id", calendarPeriod.getId()).getSingleResult();
            period.setName(calendarPeriod.getName());
            period.setStartDate(calendarPeriod.getStartDate());
            period.setEndDate(calendarPeriod.getEndDate());
            em.merge(period);
        }
        return calendarPeriod;
    }

    public void delete(CalendarPeriod calendarPeriod) {
        CalendarPeriod mergedCalendarPeriod = em.merge(calendarPeriod);
        em.remove(mergedCalendarPeriod);
    }

    @Scheduled(initialDelay = 1000, fixedRate = UPDATE_PERIOD)
    public void updateCalendarPeriod() {
        List<CalendarPeriod> periods = findAll();
        DateTime currentDate = DateTime.now();
        boolean f = true;
        for (CalendarPeriod period : periods) {
            f = period.getEndDate().before(currentDate.toDate());
        }
        if (f) {
            createPeriod(currentDate);
        }
    }

    private void createPeriod(DateTime currentDate) {
        Date start = new GregorianCalendar(currentDate.getYear(), 8, 2).getTime();
        Date end = new GregorianCalendar(currentDate.getYear() + 1, 8, 1).getTime();
        save(new CalendarPeriod(Integer.toString(currentDate.getYear()), start, end));
    }

}
