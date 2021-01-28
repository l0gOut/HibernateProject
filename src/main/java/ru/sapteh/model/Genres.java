package ru.sapteh.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table
public class Genres {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "genre_name")
    private String genreName;

    @Override
    public String toString() {
        return "Genres{" +
                "id=" + id +
                ", genreName='" + genreName + '\'' +
                '}';
    }
}



