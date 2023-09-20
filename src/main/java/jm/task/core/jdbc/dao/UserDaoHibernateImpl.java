package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Cache;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import javax.transaction.*;
import java.util.ArrayList;
import java.util.List;




public class UserDaoHibernateImpl implements UserDao {

    SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx =  session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users" +
                            "(id bigint not null auto_increment, age tinyint, last_name varchar(255)," +
                            " name varchar(255), primary key (id))")
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            session.createSQLQuery("DROP TABLE if exists users").executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            session.persist(new User(name, lastName, age));
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            User user = (User) session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            List<User> result = session.createQuery("from User ").list();
            for (User user : result) {
                System.out.println("User: " + user.toString());
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<User>();
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
