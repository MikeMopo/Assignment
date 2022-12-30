package com.arobot.assignment.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "survivor")
public class Survivor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @JsonIgnore
    private Long id;
    @Column (name = "firstname")
    private String firstname;
    @Column(name="surname")
    private String surname;
    @Column(name= "age")
    private int age;
    @Column(name="gender")
    private String gender;
    @OneToOne(cascade = {CascadeType.ALL})
    private Location location;
    @OneToOne(cascade = {CascadeType.ALL})
    private Resource resources;

    @Column(name = "infected",nullable = false)
    private boolean infected;



}
