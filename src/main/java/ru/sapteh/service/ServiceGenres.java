package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.Genres;

import java.util.List;

public class ServiceGenres implements DAO<Genres, Integer> {

    SessionFactory factory;

    public ServiceGenres(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(Genres genres) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(genres);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Genres genres) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(genres);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Genres genres) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(genres);
            session.getTransaction().commit();
        }
    }

    @Override
    public Genres read(Integer integer) {
        try(Session session = factory.openSession()){
            return session.get(Genres.class, integer);
        }
    }

    @Override
    public List<Genres> readAll() {
        try(Session session = factory.openSession()){
            String hql = "FROM Genres";
            Query<Genres> query = session.createQuery(hql);
            return query.list();
        }
    }
}
