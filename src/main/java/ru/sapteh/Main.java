package ru.sapteh;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.Authors;
import ru.sapteh.model.Books;
import ru.sapteh.model.Genres;
import ru.sapteh.release.ReleaseAuthors;
import ru.sapteh.release.ReleaseBooks;
import ru.sapteh.release.ReleaseGenres;
import ru.sapteh.release.ReleaseUsers;
import ru.sapteh.service.ServiceAuthors;
import ru.sapteh.service.ServiceBooks;
import ru.sapteh.service.ServiceGenres;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        try(BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))){

            SessionFactory factory = new Configuration().configure().buildSessionFactory();

            do {
                System.out.println("Выберите с чем будете работать в базе:");
                System.out.println("Author = A");
                System.out.println("Book = B");
                System.out.println("Genres = G");
                System.out.println("User = U");
                String select = bf.readLine();
                if (select.equals("A")) {
                    ReleaseAuthors releaseAuthors = new ReleaseAuthors();
                    releaseAuthors.authorCreate(bf, factory);
                }
                if (select.equals("B")) {
                    ReleaseBooks releaseBooks = new ReleaseBooks();
                    releaseBooks.booksCreate(bf, factory);
                }
                if (select.equals("G")) {
                    ReleaseGenres releaseGenres = new ReleaseGenres();
                    releaseGenres.createGenre(bf, factory);
                }
                if (select.equals("U")) {
                    ReleaseUsers releaseUsers = new ReleaseUsers();
                    releaseUsers.createUser(bf, factory);
                }

                System.out.println("Добавить какие-нибудь еще изменения?");
                System.out.println("(Если да то напишите - Yes)");
            } while(bf.readLine().equals("Yes"));

            factory.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
