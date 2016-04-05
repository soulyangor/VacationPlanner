/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vacationplanner.controller;

import static com.mycompany.vacationplanner.controller.CalendarPeriodController.CALENDAR_PERIODS_PATH;
import com.mycompany.vacationplanner.model.CalendarPeriod;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mycompany.vacationplanner.repository.CalendarPeriodRepository;

/**
 *
 * @author ֲÿקוסכאג
 */
@RestController
@RequestMapping(path = CALENDAR_PERIODS_PATH,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class CalendarPeriodController {

    public static final String CALENDAR_PERIODS_PATH = "/calendar_periods";
    public static final String ITEM_PATH = "/item";

    private CalendarPeriodRepository calendarPeriodRepository;

    @Autowired(required = false)
    public void setCalendarPeriodRepository(CalendarPeriodRepository calendarPeriodRepository) {
        this.calendarPeriodRepository = calendarPeriodRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CalendarPeriod> getCalendarPeriodList() {
        return calendarPeriodRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST,
            path = ITEM_PATH,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public CalendarPeriod createCalendarPeriod(@RequestBody CalendarPeriod calendarPeriod) {
        return calendarPeriodRepository.save(calendarPeriod);
    }

    @RequestMapping(method = RequestMethod.PUT,
            path = ITEM_PATH,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public CalendarPeriod updateCalendarPeriod(@RequestBody CalendarPeriod calendarPeriod) {
        return calendarPeriodRepository.save(calendarPeriod);
    }

    @RequestMapping(method = RequestMethod.DELETE,
            path = ITEM_PATH,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deletePost(@RequestBody CalendarPeriod calendarPeriod) {
        calendarPeriodRepository.delete(calendarPeriod);
    }
}
