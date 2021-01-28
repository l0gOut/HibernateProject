package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.Users;

import java.util.List;

public class ServiceUsers implements DAO<Users, Integer> {

    private SessionFactory factory;

    public ServiceUsers(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(Users users) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(users);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Users users) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(users);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Users users) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(users);
            session.getTransaction().commit();
        }
    }

    @Override
    public Users read(Integer integer) {
        try(Session session = factory.openSession()){
            return session.get(Users.class, integer);
        }
    }

    @Override
    public List<Users> readAll() {
        try(Session session = factory.openSession()){
            String hql = "FROM Users";
            Query<Users> query = session.createQuery(hql);
            return query.list();
        }
    }
}
