package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {   //КОНТРОЛЛЕР
    // Класс service должен реализовывать интерфейс service

    //Service на этот раз использует реализацию dao через Hibernate

    public static UserDao userDaoHibernateImpl = new UserDaoHibernateImpl();  //экземпляр dao, чтобы переиспользовать его методы

    public void createUsersTable() {
        userDaoHibernateImpl.createUsersTable();
    }   //service переиспользует методы dao

    public void dropUsersTable() {
        userDaoHibernateImpl.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernateImpl.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDaoHibernateImpl.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDaoHibernateImpl.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoHibernateImpl.cleanUsersTable();
    }
}
