package com.example.demo;

import com.example.demo.Service.CalendarDTO;
import com.example.demo.Service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@RestController
public class CalendarController {

    @Autowired
    CalendarService calendarService;

    @PostMapping("/api/calendar")
    public Long postCalendar(@RequestBody CalendarDTO calendarDTO){
        return calendarService.postCalendar(calendarDTO);
    }

    @GetMapping("/api/calendar/{id}")
    public CalendarDTO getCalendarPodlaId(@PathVariable Long id){
        return calendarService.getCalendarPodlaId(id);
    }

    //@PutMapping("/api/calendar/{id}")
    public void putCalendar(){
        //ToDo
    }

    @DeleteMapping("/api/calendar/{id}")
    public void deleteCalendar(@PathVariable Long id){
        calendarService.deleteCalendar(id);
    }
}
