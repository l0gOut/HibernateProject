package ru.sapteh;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.Authors;
import ru.sapteh.model.Books;
import ru.sapteh.model.Genres;
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

        try(BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {

            SessionFactory factory = new Configuration().configure().buildSessionFactory();

            do {


                System.out.println("Выберите что добавить в базу:");
                System.out.println("Author = A");
                System.out.println("Book = B");
                System.out.println("Genres = G");
                System.out.println("User = U");
                System.out.println();
                if (bf.readLine().equals("A")) {
                    authorCreate(bf, factory);
                }
                if (bf.readLine().equals("B")){
                    booksCreate(bf, factory);
                }

                System.out.println("Добавить какие-нибудь еще изменения?");
                System.out.println("(Если да то напишите - Yes)");
            } while(bf.readLine().equals("Yes"));

            factory.close();

//
//            DAO<Authors, Integer> serviceAuthors = new ServiceAuthors(factory);
//            DAO<Users, Integer> serviceUsers = new ServiceUsers(factory);
//            DAO<Books, Integer> serviceBooks = new ServiceBooks(factory);
//
//            Authors authors = new Authors();
//
//            authors.setFirstName("Гена");
//            authors.setLastName("Букин");
//            authors.setCreateAt(new GregorianCalendar(2020, Calendar.NOVEMBER,10).getTime());
//
//            Users users = new Users();
//
//            users.setDateTime(new GregorianCalendar(2020, Calendar.NOVEMBER,10).getTime());
//            users.setUserName("Login");
//
//            Genres genres = new Genres();
//
//            genres.setGenreName("Роман");
//
//            Books books = new Books();
//
//            books.setTitle("Вирус Алексея");
//            books.setPublishingYear(new GregorianCalendar(2020, Calendar.NOVEMBER,10).getTime());
//
//
//            Set<Authors> authorsSet = new HashSet<>();
//            authorsSet.add(authors);
//            books.setAuthorsSet(authorsSet);
//
//            Set<Users> usersSet = new HashSet<>();
//            usersSet.add(users);
//            books.setBooksSet(usersSet);
//
//            Set<Books> booksSet = new HashSet<>();
//            booksSet.add(books);
//            users.setUsersSet(booksSet);
//            authors.setBooksSet(booksSet);
//            books.setGenres(genres);
//
//            serviceBooks.create(books);
//            serviceAuthors.create(authors);
//            serviceUsers.create(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void authorCreate(BufferedReader bf, SessionFactory factory) {
        try {
            DAO<Authors, Integer> serviceAuthors = new ServiceAuthors(factory);
            DAO<Books, Integer> serviceBooks = new ServiceBooks(factory);
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
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
            System.out.println("Введите id книги из базы");
            Books books = serviceBooks.read(Integer.valueOf(bf.readLine()));
            Set<Books> booksSet = new HashSet<>();
            booksSet.add(books);
            authors.setBooksSet(booksSet);
            serviceAuthors.create(authors);
            System.out.println("Готово!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void booksCreate(BufferedReader bf, SessionFactory factory){
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
            System.out.println("Введите id автора написавшего книгу");
            Set<Authors> authorsSet = new HashSet<>();
            authorsSet.add(serviceAuthors.read(Integer.valueOf(bf.readLine())));
            books.setAuthorsSet(authorsSet);
            System.out.println("Введите id существуещего в базе жанра: ");
            books.setGenres(serviceGenres.read(Integer.valueOf(bf.readLine())));
            serviceBooks.create(books);
            System.out.println("Готово!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
