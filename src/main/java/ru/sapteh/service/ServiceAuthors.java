package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.Authors;

import java.util.List;

public class ServiceAuthors implements DAO<Authors, Integer> {

    SessionFactory factory;

    public ServiceAuthors(SessionFactory factory){
        this.factory = factory;
    }

    public void create(Authors authors) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(authors);
            session.getTransaction().commit();
        }
    }

    public void update(Authors authors) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(authors);
            session.getTransaction().commit();
        }
    }

    public void delete(Authors authors) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(authors);
            session.getTransaction().commit();
        }
    }

    public Authors read(Integer integer) {
        try(Session session = factory.openSession()){
            return session.get(Authors.class, integer);
        }
    }

    public List<Authors> readAll() {
        try(Session session = factory.openSession()) {
            String hql = "FROM Authors";
            Query<Authors> query = session.createQuery(hql);
            return query.list();
        }
    }
}
