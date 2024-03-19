package com.example.demo.Perzistent;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String address;

    @OneToMany(mappedBy="publisher")
    private Set<BookEntity> books;

}
