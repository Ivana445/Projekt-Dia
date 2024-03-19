package com.example.demo;

import com.example.demo.Service.PublisherDTO;
import com.example.demo.Service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    @Autowired
    PublisherService publisherService;

    @PostMapping
    public Long vytvorPublishera(@RequestBody PublisherDTO p){
        return publisherService.vytvorPublishera(p);
    }

    @PutMapping("/{id}")
    public void upravPublishera(@PathVariable Long id, @RequestBody PublisherDTO publisherDTO) {
        publisherService.upravPublishera(id, publisherDTO);
    }

    @DeleteMapping("/{id}")
    public void vymazPublishera(@PathVariable Long id){
        publisherService.vymazPublishera(id);
    }

    @GetMapping
    public ArrayList<PublisherDTO> zoznamPublisherov(@RequestParam("name") String name ) {
        return publisherService.zoznamPublisherov(name);
    }
}
