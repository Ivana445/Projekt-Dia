package com.example.demo.Perzistent;

import org.apache.el.stream.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Long> {
    //Long je typ primarneho kluca s ktorym kniha pracuje
}
