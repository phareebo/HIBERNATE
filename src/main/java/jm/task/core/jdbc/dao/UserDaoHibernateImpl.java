package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Класс UserHibernateDaoImpl в рамках этой задачи не затрагивается (остаётся пустой)
public class UserDaoHibernateImpl implements UserDao {  // должен реализовывать интерефейс UserDao
    //Класс dao должен иметь конструктор пустой/по умолчанию
    //
    // Методы создания и удаления таблицы пользователей в классе UserHibernateDaoImpl должны быть реализованы с помощью SQL.
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("CREATE  TABLE IF NOT EXISTS usersTable(id INT AUTO_INCREMENT, " +
                    "name VARCHAR(50),lastName VARCHAR (50), age INT not NULL, PRIMARY KEY (id));").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }          //Обработка всех исключений, связанных с работой с базой данных должна находиться в dao
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS usersTable;").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            //session.createNativeQuery("INSERT INTO usersTable(name, lastName, age)"
                    //+ "VALUES (\"" + name + "\"," + " \"" + lastName + "\"," + age + ")").executeUpdate();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Transaction transaction = null;
        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            users = session.createNativeQuery("SELECT * FROM usersTable;").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return users;
    }


    @Override
    public void cleanUsersTable() {

    }

}
