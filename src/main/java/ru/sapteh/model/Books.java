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
public class Books {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String title;
    @Column(name = "publishing_year")
    private Date publishingYear;

    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "genres_id", nullable = false)
    private Genres genres;

    @ManyToMany(mappedBy = "usersSet")
    private Set<Users> booksSet;
    @ManyToMany(mappedBy = "booksSet")
    private Set<Authors> authorsSet;

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publishingYear='" + publishingYear + '\'' +
                ", genreId=" + genres +
                '}';
    }
}
