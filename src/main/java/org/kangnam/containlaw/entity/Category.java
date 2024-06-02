package org.kangnam.containlaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Bill> bills;
}
