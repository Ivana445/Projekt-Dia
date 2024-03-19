package com.example.demo.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //gettre settre to string...
@NoArgsConstructor //konstruktor
@AllArgsConstructor //automaticky doplni parametricky konstruktor
public class PublisherDTO {

    Long id;
    String name;
    String address;
}
