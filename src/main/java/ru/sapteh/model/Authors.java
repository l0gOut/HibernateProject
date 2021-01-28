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
public class Authors {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "create_at")
    private Date createAt;
    @ManyToMany
    @JoinTable(
            name = "authors_books",
            joinColumns = @JoinColumn (name = "authors_id"),
            inverseJoinColumns = @JoinColumn (name = "books_id")
    )
    private Set<Books> booksSet;

    @Override
    public String toString() {
        return "Authors{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}
