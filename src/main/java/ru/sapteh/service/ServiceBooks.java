package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.Books;

import java.util.List;

public class ServiceBooks implements DAO<Books, Integer> {

    SessionFactory factory;

    public ServiceBooks(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(Books books) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(books);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Books books) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(books);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Books books) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(books);
            session.getTransaction().commit();
        }
    }

    @Override
    public Books read(Integer integer) {
        try(Session session = factory.openSession()){
            return session.get(Books.class, integer);
        }
    }

    @Override
    public List<Books> readAll() {
        try(Session session = factory.openSession()){
            String hql = "FROM Books";
            Query<Books> query = session.createQuery(hql);
            return query.list();
        }
    }
}
