package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Mechanic_Details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mechanic_Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MechanicID")
    private int mechanicId;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private Long phone;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "experience", nullable = false)
    private Float experience;


}
