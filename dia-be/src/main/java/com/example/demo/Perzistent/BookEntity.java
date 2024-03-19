package com.example.demo.Perzistent;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data //gettre settre to string...
@NoArgsConstructor //konstruktor
@AllArgsConstructor //automaticky doplni parametricky konstruktor
public class BookEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String Nazov;
    private String Autor;
    @ManyToOne
    private PublisherEntity publisher;

}
