package ru.sapteh.release;

import org.hibernate.SessionFactory;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.Authors;
import ru.sapteh.model.Books;
import ru.sapteh.model.Genres;
import ru.sapteh.service.ServiceAuthors;
import ru.sapteh.service.ServiceBooks;
import ru.sapteh.service.ServiceGenres;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class ReleaseBooks {
    public void booksCreate(BufferedReader bf, SessionFactory factory){
        try {
            Books books = new Books();
            DAO<Authors, Integer> serviceAuthors = new ServiceAuthors(factory);
            DAO<Genres, Integer> serviceGenres = new ServiceGenres(factory);
            DAO<Books, Integer> serviceBooks = new ServiceBooks(factory);
            System.out.println("Введите название книги: ");
            books.setTitle(bf.readLine());
            System.out.println("---------------------Дата публикации книги---------------------");
            System.out.println("Введите год: ");
            int year = Integer.parseInt(bf.readLine());
            System.out.println("Введите месяц: ");
            int month = Integer.parseInt(bf.readLine());
            System.out.println("Введите день: ");
            int day = Integer.parseInt(bf.readLine());
            books.setPublishingYear(new GregorianCalendar(year, month, day).getTime());
            System.out.println("Введите id существуещего в базе жанра: ");
            books.setGenres(serviceGenres.read(Integer.valueOf(bf.readLine())));
            System.out.println("Введите id автора написавшего книгу");
            Set<Authors> authorsSet = new HashSet<>();
            authorsSet.add(serviceAuthors.read(Integer.valueOf(bf.readLine())));
            books.setAuthorsSet(authorsSet);
            serviceBooks.create(books);
            System.out.println("Готово!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
