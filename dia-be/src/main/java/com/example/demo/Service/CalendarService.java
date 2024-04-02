package com.example.demo.Service;

import com.example.demo.Perzistent.CalendarEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Perzistent.CalendarRepository;

@Service
public class CalendarService {
    @Autowired
    private CalendarRepository calendarRepository;

    public Long postCalendar(CalendarDTO calendarDTO){
        CalendarEntity entity = new CalendarEntity();
        CalendarEntity savedEntity = calendarRepository.save(entity);
        return savedEntity.getId();
    }

    public CalendarDTO getCalendarPodlaId(Long id){
        CalendarEntity entity = calendarRepository.findById(id).orElse(null);
        if (entity != null){
            CalendarDTO dto = new CalendarDTO();
            dto.setId(entity.getId());
            return dto;
        }
        return null;
    }

    public void putCalendar(Long id){
        //ToDo
    }

    public void deleteCalendar(Long id){
        calendarRepository.deleteById(id);
    }
}
