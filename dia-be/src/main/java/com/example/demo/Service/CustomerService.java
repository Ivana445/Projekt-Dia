package com.example.demo.Service;

import com.example.demo.Perzistent.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Perzistent.CustomerRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerDTO ZiskajCustomeraPodlaId(Long Id) {
        Optional<CustomerEntity> opt = customerRepository.findById(Id);
        if (opt.isEmpty()) {
            return null;
        }
        CustomerEntity entity = opt.get();
        CustomerDTO dto = new CustomerDTO();
        dto.setId(entity.getId());
        dto.setMeno(entity.getMeno());
        dto.setKontakt(entity.getKontakt());
        return dto;
    }
    public Long vytvorCustomera(CustomerDTO dto) {
        CustomerEntity entity = new CustomerEntity();
        entity.setMeno(dto.getMeno());
        entity.setKontakt(dto.getKontakt());
        customerRepository.save(entity);
        return entity.getId();
    }
    public void upravCustomera(Long Id, CustomerDTO dto){
        Optional<CustomerEntity> opt = customerRepository.findById(Id);
        if (opt.isEmpty()) {
            return;
        }
        CustomerEntity entity = opt.get();

        if (dto.getMeno() != null) {
            entity.setMeno(dto.getMeno());
        }
        if (dto.getKontakt() != null) {
            entity.setKontakt(dto.getKontakt());
        }
        customerRepository.save(entity);
        System.out.println("Nová entita: " + entity);
    }
    public void vymazCustomera(Long Id){
        Optional<CustomerEntity> opt = customerRepository.findById(Id);
        if (opt.isEmpty()) {
            return;
        }
        CustomerEntity entity = opt.get();
        customerRepository.delete(entity);

    }

    public ArrayList<CustomerDTO> zoznamZakaznikov(String meno) {
        ArrayList<CustomerDTO> customerDTO = new ArrayList<>();
        System.out.println("hladam zakaznika " + meno);
        ArrayList<CustomerDTO> result = new ArrayList<>();
        for (CustomerDTO customerDTO1 : customerDTO) {
            if (customerDTO1.getMeno().equals(meno)) {
                result.add(customerDTO1);
            }
        }
        return customerDTO;
    }

}
