package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Perzistent.CalendarRepository;

@Service
public class CalendarService {
    @Autowired
    private CalendarRepository calendarRepository;




}
