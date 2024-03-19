package com.example.demo.Service;

import com.example.demo.Perzistent.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Perzistent.BookRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookDTO ZiskajKnihuPodlaId(Long id) {
        Optional<BookEntity> opt = bookRepository.findById(id);
        if (opt.isEmpty()) {
            return null;
        }
        BookEntity entity = opt.get();
        BookDTO dto = new BookDTO();
        dto.setId(entity.getId());
        dto.setNazov(entity.getNazov());
        dto.setAutor(entity.getAutor());
        return dto;
    }

    public Long vytvorKnihu(BookDTO dto) {
        BookEntity entity = new BookEntity();
        entity.setNazov(dto.getNazov());
        entity.setAutor(dto.getAutor());
        bookRepository.save(entity);
        return entity.getId();
    }
    public void upravKnihu(Long id, BookDTO dto){
        Optional<BookEntity> opt = bookRepository.findById(id);
        if (opt.isEmpty()) {
            return;
        }
        BookEntity entity = opt.get();

        if (dto.getNazov() != null) {
            entity.setNazov(dto.getNazov());
        }
        if (dto.getAutor() != null) {
            entity.setAutor(dto.getAutor());
        }
        bookRepository.save(entity);
        System.out.println("Nová entita: " + entity);
    }
    public void vymazKnihu(Long id){
        Optional<BookEntity> opt = bookRepository.findById(id);
        if (opt.isEmpty()) {
            return;
        }
        BookEntity entity = opt.get();
        bookRepository.delete(entity);

    }

    public ArrayList<BookDTO> zoznamKnih(String nazov) {
        ArrayList<BookDTO> bookDTO = new ArrayList<>();
        System.out.println("hladam knihu " + nazov);
        ArrayList<BookDTO> result = new ArrayList<>();
        for (BookDTO bookDTO1 : bookDTO) {
            if (bookDTO1.getNazov().equals(nazov)) {
                result.add(bookDTO1);
            }
        }
        return bookDTO;
    }

}
