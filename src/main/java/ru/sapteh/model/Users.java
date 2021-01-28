package ru.sapteh.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String userName;
    @Column(name = "date_time")
    private Date dateTime;
    @ManyToMany
    @JoinTable(
            name = "users_books",
            joinColumns = @JoinColumn(name = "authors_id"),
            inverseJoinColumns = @JoinColumn(name = "books_id")
    )
    private Set<Books> usersSet;
    @Override
    public String toString() {
        return "Authors{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
