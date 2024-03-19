package com.example.demo;


import com.example.demo.Service.CustomerDTO;
import com.example.demo.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;
    private final ArrayList<CustomerDTO> customerDTO = new ArrayList<>();

    @PostMapping("/api/customers")
    public Long vytvorZakaznika(@RequestBody CustomerDTO l){
        return customerService.vytvorCustomera(l);
    }
    @GetMapping("/api/customers/{id}")
    public CustomerDTO ziskajZakaznikaPodlaId(@PathVariable Long id) {
        return customerService.ZiskajCustomeraPodlaId(id);
    }
    @DeleteMapping("/api/customers/{id}")
    public void vymazZakaznika(@PathVariable Long id){
        customerService.vymazCustomera(id);
    }
    @PutMapping("/api/customers/{id}")
    public void upravZakaznika(@PathVariable Long id, @RequestBody CustomerDTO zakaznik) {
        customerService.upravCustomera(id,zakaznik);
    }
    @GetMapping("/api/customers")
    public ArrayList<CustomerDTO> zoznamZakaznikov(@RequestParam("meno") String meno ) {
        return customerService.zoznamZakaznikov(meno);
    }
}
