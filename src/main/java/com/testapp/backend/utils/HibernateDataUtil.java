package com.testapp.backend.utils;

import com.testapp.backend.models.Roles;
import com.testapp.backend.models.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@AllArgsConstructor
public class HibernateDataUtil {

    private final HibernateUtil hibernateUtil = new HibernateUtil();

    private synchronized <T> Query<T> query(Session session, Class<T> tClass, String query, Object[] object){
        Query<T> tQuery = session.createQuery(query, tClass);
        for (int i=0;i<object.length; i++){
            tQuery.setParameter(i + 1, object[i]);
        }
        return tQuery;
    }

    public synchronized <T> Optional<T> execQSingle(Class<T> tClass, String query, Object... args) {
        try {
            Session session = hibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            T entity = query(session, tClass, query, args).getSingleResult();
            session.flush();
            return Optional.ofNullable(entity);
        } catch (ConstraintViolationException e) {
            return Optional.empty();
        }
    }

    public synchronized <T> Integer execQInt(Class<T> tClass, String query, Object... args) {
        try {
            Session session = hibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            int entity = query(session, tClass, query, args).getMaxResults();
            session.flush();
            return entity;
        } catch (ConstraintViolationException e) {
            return 0;
        }
    }

    public synchronized <T> List<T> execQList(Class<T> tClass, String queryString, Object... args) {
        try {
            Session session = hibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            List<T> resultList = query(session, tClass, queryString, args).getResultList();
            session.flush();
            return resultList;
        } catch (ConstraintViolationException e) {
            return new ArrayList<>();
        }
    }


    public synchronized <T extends User> Optional<T> getUserById(Class<T> tClass, long id) {
        try{
            Session session = hibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            T entity = session.get(tClass, id);
            session.getTransaction().commit();
            session.close();
            return entity != null
                    ? Optional.of(entity)
                    : Optional.empty();
        } catch (ConstraintViolationException e) {
            log.error(e.toString());
            return Optional.empty();
        }
    }

    public synchronized <T extends User> Outcomes createUser(T entity) {
        try {
            Session session = hibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.save(entity);
            session.getTransaction().commit();
            session.close();
            return Outcomes.SUCCESS;
        } catch (ConstraintViolationException e) {
            log.error(e.toString());
            return Outcomes.FAILED;
        }
    }

    public synchronized <T extends Roles> Outcomes createRoles(T entity) {
        try {
            Session session = hibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.save(entity);
            session.getTransaction().commit();
            session.close();
            return Outcomes.SUCCESS;
        } catch (ConstraintViolationException e) {
            log.error(e.toString());
            return Outcomes.FAILED;
        }
    }

    public synchronized <T extends User> Outcomes updateUser(T entity) {
        try {
            Session session = hibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.update(entity);
            session.getTransaction().commit();
            session.close();
            return Outcomes.SUCCESS;
        } catch (ConstraintViolationException e) {
            log.error(e.toString());
            return Outcomes.FAILED;
        }
    }

    public synchronized <T extends User> Outcomes deleteUser(Class<T> tClass, long id) {
        try {
            Optional<T> optEntity = getUserById(tClass, id);
            Session session = hibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.delete(optEntity.get());
            session.getTransaction().commit();
            session.close();
            return Outcomes.SUCCESS;
        } catch (ConstraintViolationException e) {
            log.error(e.toString());
            return Outcomes.FAILED;
        }
    }

    public synchronized <T extends User> Outcomes deleteAll() {
        try {
            Session session = hibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.createQuery(" delete from User").executeUpdate();
            session.getTransaction().commit();
            session.close();
            return Outcomes.SUCCESS;
        } catch (ConstraintViolationException e) {
            log.error(e.toString());
            return Outcomes.FAILED;
        }
    }


}
