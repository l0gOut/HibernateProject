package ru.sapteh.release;

import org.hibernate.SessionFactory;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.Books;
import ru.sapteh.model.Users;
import ru.sapteh.service.ServiceBooks;
import ru.sapteh.service.ServiceUsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class ReleaseUsers {
    public void createUser (BufferedReader bf, SessionFactory factory){
        try {
            Users users = new Users();
            DAO<Users, Integer> serviceUsers = new ServiceUsers(factory);
            DAO<Books, Integer> serviceBooks = new ServiceBooks(factory);
            System.out.println("Введите новое имя пользователя: ");
            users.setUserName(bf.readLine());
            users.setDateTime(Calendar.getInstance().getTime());
            System.out.println("Введите id книги для пользователя: ");
            Set<Books> booksSet = new HashSet<>();
            booksSet.add(serviceBooks.read(Integer.valueOf(bf.readLine())));
            users.setUsersSet(booksSet);
            serviceUsers.create(users);
            System.out.println("Готово!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
