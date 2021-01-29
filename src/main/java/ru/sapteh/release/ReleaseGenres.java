package ru.sapteh.release;

import org.hibernate.SessionFactory;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.Genres;
import ru.sapteh.service.ServiceGenres;

import java.io.BufferedReader;
import java.io.IOException;

public class ReleaseGenres {
    public void createGenre (BufferedReader bf, SessionFactory factory){
        try{
            Genres genres = new Genres();
            DAO<Genres, Integer> serviceGenres = new ServiceGenres(factory);
            System.out.println("Введите новый жанр: ");
            genres.setGenreName(bf.readLine());
            serviceGenres.create(genres);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
