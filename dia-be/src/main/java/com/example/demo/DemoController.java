package com.example.demo;

import com.example.demo.Service.BookService;
import com.example.demo.Service.BookDTO;
import com.example.demo.Service.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Perzistent.BookRepository;
import java.util.ArrayList;

@RestController
public class DemoController {

    @Autowired
    BookService bookService;

    private final ArrayList<BookDTO> knihy = new ArrayList<>();
    private final ArrayList<CustomerDTO> customerDTO = new ArrayList<>();


    @PostMapping("/api/books")
    public Long vytvorKnihu(@RequestBody BookDTO k) {
        return bookService.vytvorKnihu(k);
    }
    @GetMapping("/api/books/{id}")
    public BookDTO ziskajKnihuPodlaId(@PathVariable Long id) {
        return bookService.ZiskajKnihuPodlaId(id);
    }
    @DeleteMapping("/api/books/{id}")
    public void vymazKnihu(@PathVariable Long id){
        bookService.vymazKnihu(id);
    }
    @PutMapping("/api/books/{id}")
    public void upravKnihu(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        bookService.upravKnihu(id,bookDTO);
    }

    @GetMapping("/api/books")
    public ArrayList<BookDTO> zoznamKnih(@RequestParam("nazov") String nazov ) {
        return bookService.zoznamKnih(nazov);
    }


}
