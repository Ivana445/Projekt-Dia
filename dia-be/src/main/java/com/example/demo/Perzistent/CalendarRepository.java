package com.example.demo.Perzistent;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepository extends CrudRepository<CalendarEntity, Long> {
    //Long je typ primarneho kluca s ktorym kniha pracuje
}
