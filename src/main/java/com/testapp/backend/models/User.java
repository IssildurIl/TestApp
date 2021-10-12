package com.testapp.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long _id;

    @Column(nullable = false)
    protected String name;

    @Column(nullable = false)
    protected String login;

    @Column(nullable = false)
    protected String password;

    @Column(nullable = false)
    protected Roles typeOfUser;

}
