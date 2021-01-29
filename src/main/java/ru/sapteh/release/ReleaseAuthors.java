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
import java.util.List;
import java.util.Set;

public class ReleaseAuthors {
    public void authorCreate(BufferedReader bf, SessionFactory factory){
        try {
            DAO<Authors, Integer> serviceAuthors = new ServiceAuthors(factory);
            DAO<Books, Integer> serviceBooks = new ServiceBooks(factory);
            DAO<Genres, Integer> serviceGenres = new ServiceGenres(factory);
            Authors authors = new Authors();
            System.out.println("Введите имя автора: ");
            authors.setFirstName(bf.readLine());
            System.out.println("Введите фамилию автора: ");
            authors.setLastName(bf.readLine());
            System.out.println("---------------------Дата рождения автора---------------------");
            System.out.println("Введите год: ");
            int year = Integer.parseInt(bf.readLine());
            System.out.println("Введите месяц: ");
            int month = Integer.parseInt(bf.readLine());
            System.out.println("Введите день: ");
            int day = Integer.parseInt(bf.readLine());
            authors.setCreateAt(new GregorianCalendar(year, month, day).getTime());
            do {
                System.out.println("----------------------------------------------------------");
                System.out.println("Создать новую книгу или взять существующую из базы?");
                System.out.println("Создать = C");
                System.out.println("Добавить существующую = A");
                String select = bf.readLine();
                if (select.equals("C")) {
                    Books books = new Books();
                    System.out.println("Введите название книги: ");
                    books.setTitle(bf.readLine());
                    System.out.println("---------------------Дата публикации книги---------------------");
                    System.out.println("Введите год: ");
                    int yearBook = Integer.parseInt(bf.readLine());
                    System.out.println("Введите месяц: ");
                    int monthBook = Integer.parseInt(bf.readLine());
                    System.out.println("Введите день: ");
                    int dayBook = Integer.parseInt(bf.readLine());
                    books.setPublishingYear(new GregorianCalendar(yearBook, monthBook, dayBook).getTime());
                    do {
                        System.out.println("----------------------------------------------------------");
                        System.out.println("Добавить новый жанр или взять сущестующий из базы?");
                        System.out.println("Создать = C");
                        System.out.println("Добавить существующий = A");
                        String selectGenre = bf.readLine();
                        if(selectGenre.equals("C")){
                            Genres genres = new Genres();
                            System.out.println("Введите новый жанр: ");
                            genres.setGenreName(bf.readLine());
                            books.setGenres(genres);
                            serviceGenres.create(genres);
                            Set<Authors> authorsSet = new HashSet<>();
                            authorsSet.add(authors);
                            books.setAuthorsSet(authorsSet);
                            serviceBooks.create(books);
                            serviceAuthors.create(authors);
                            System.out.println("Готово!");
                            return;
                        }
                        if (selectGenre.equals("A")){
                            System.out.println("----------------------------------------------------------");
                            List<Genres> genresList = serviceGenres.readAll();
                            System.out.println("Id \t Genres");
                            for (Genres genre: genresList) {
                                System.out.println(genre.getId() + "\t" + genre.getGenreName());
                            }
                            System.out.println("----------------------------------------------------------");
                            System.out.println("Введите id существуещего в базе жанра: ");
                            books.setGenres(serviceGenres.read(Integer.valueOf(bf.readLine())));
                            Set<Authors> authorsSet = new HashSet<>();
                            authorsSet.add(authors);
                            books.setAuthorsSet(authorsSet);
                            serviceBooks.create(books);
                            serviceAuthors.create(authors);
                            System.out.println("Готово!");
                            return;
                        }
                        System.out.println("Ошибка!");
                        System.out.println("Вы ввели что-то не то.");
                        System.out.println("Пожалуйста повторите.");
                    } while (true);
                }
                if (select.equals("A")) {
                    System.out.println("----------------------------------------------------------");
                    List<Books> booksList = serviceBooks.readAll();
                    System.out.println("Id \t Titles \t Genres \t PublishingDate");
                    for (Books book: booksList) {
                        System.out.println(book.getId() + "\t" + book.getTitle() + "\t" + book.getGenres().getGenreName() + "\t" + book.getPublishingYear());
                    }
                    System.out.println("----------------------------------------------------------");
                    System.out.println("Введите id книги из базы");
                    Books books = serviceBooks.read(Integer.valueOf(bf.readLine()));
                    Set<Books> booksSet = new HashSet<>();
                    booksSet.add(books);
                    authors.setBooksSet(booksSet);
                    serviceAuthors.create(authors);
                    System.out.println("Готово!");
                    return;
                }
                System.out.println("Ошибка!");
                System.out.println("Вы ввели что-то не то.");
                System.out.println("Пожалуйста повторите.");
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
