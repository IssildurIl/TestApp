package com.testapp.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

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

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(nullable = false)
    protected Set<Roles> typeOfUser;

}
