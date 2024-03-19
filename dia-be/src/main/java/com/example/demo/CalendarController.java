package com.example.demo;

import com.example.demo.Service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalendarController {

    @Autowired
    CalendarService calendarService;
}
