package com.proyecto20.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lib")
@NoArgsConstructor
@Getter
@Setter
public class Lib {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String target;
    private Integer capacity;
    private String description;

    @ManyToOne
    @JoinColumn(name = "idCategory")
    @JsonIgnoreProperties("boxes")
    private Category category;

    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "lib")
    @JsonIgnoreProperties({"lib", "client"})
    private List<Message> messages;

    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "lib")
    @JsonIgnoreProperties({"lib", "client"})
    private List<Reservation> reservations;
}
