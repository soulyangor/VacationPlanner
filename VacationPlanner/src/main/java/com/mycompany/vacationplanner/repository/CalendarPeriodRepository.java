/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vacationplanner.repository;

import com.mycompany.vacationplanner.model.CalendarPeriod;
import java.util.List;

/**
 *
 * @author ֲÿקוסכאג
 */
public interface CalendarPeriodRepository {

    List<CalendarPeriod> findAll();

    CalendarPeriod save(CalendarPeriod calendarPeriod);

    void delete(CalendarPeriod calendarPeriod);

    void updateCalendarPeriod();

}
