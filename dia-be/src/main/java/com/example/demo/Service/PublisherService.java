package com.example.demo.Service;

import com.example.demo.Perzistent.PublisherEntity;
import com.example.demo.Perzistent.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    public Long vytvorPublishera(PublisherDTO dto){
        PublisherEntity publisher = new PublisherEntity();
        publisher.setName(dto.getName());
        publisher.setAddress(dto.getAddress());
        publisherRepository.save(publisher);
        return publisher.getId();
    }

    public void upravPublishera(Long id, PublisherDTO dto) {
        Optional<PublisherEntity> opt = publisherRepository.findById(id);
        if (opt.isPresent()) {
            PublisherEntity publisher = opt.get();
            if (dto != null) {
                if (dto.getName() != null) {
                    publisher.setName(dto.getName());
                }
                if (dto.getAddress() != null) {
                    publisher.setAddress(dto.getAddress());
                }
                publisherRepository.save(publisher);
                System.out.println(publisher);
            }
        }
    }

    public void vymazPublishera(Long id){
        Optional<PublisherEntity> opt = publisherRepository.findById(id);
        if (opt.isEmpty()) {
            return;
        }
        PublisherEntity publisher = opt.get();
        publisherRepository.delete(publisher);

    }

    public ArrayList<PublisherDTO> zoznamPublisherov(String name) {
        ArrayList<PublisherDTO> publisherDTO = new ArrayList<>();
        System.out.println("hladam publishera " + name);
        ArrayList<PublisherDTO> result = new ArrayList<>();
        for (PublisherDTO publisherDTO1 : publisherDTO) {
            if (publisherDTO1.getName().equals(name)) {
                result.add(publisherDTO1);
            }
        }
        return publisherDTO;
    }
}
