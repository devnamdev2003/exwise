package com.exwise.exwise.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter @Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 255)
    private String icon;

    @Column(length = 20)
    private String color;

    // Optional: bidirectional mapping to expenses
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Expense> expenses;

    // Getters and Setters
}
