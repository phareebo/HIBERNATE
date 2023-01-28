package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {   //Контроллер???
    //Классы dao должны реализовывать интерфейс dao
    public UserDaoJDBCImpl() {      //Класс dao должен иметь конструктор пустой/по умолчанию

    }

    //Создание таблицы для User(ов) – не должно приводить к исключению, если такая таблица уже существует
    public void createUsersTable() {
        try (Connection connection = Util.createConnection();
             Statement statement = connection.createStatement()) {
            String SQL = "CREATE  TABLE IF NOT EXISTS usersTable(id INT AUTO_INCREMENT," +
                    "name VARCHAR(50), lastName VARCHAR (50), age INT not NULL, PRIMARY KEY (id));";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {              //Обработка всех исключений, связанных с работой с базой данных
            e.printStackTrace();      //должна находиться в dao
        }
    }

    //Удаление таблицы User(ов) – не должно приводить к исключению, если таблицы не существует
    public void dropUsersTable() {
        try (Connection connection = Util.createConnection();   //объект для соединения с БД = соединение с БД
             Statement statement = connection.createStatement()) { //объект state для исполнения запросов к базе данных
            String SQL = "DROP TABLE IF EXISTS usersTable;";  //команда SQL
            statement.executeUpdate(SQL);   //метод state'a для выполнения команды SQL
        } catch (SQLException e) {          //ловим исключение для создания подключения
            e.printStackTrace();    //для информативности при выбросе исключения можно на печтать стектрейс отправить.
        }                           //Так хоть след в логах останется
    }

    //Добавление User в таблицу
    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.createConnection();       //обойдись без файнали блока (используй ресурсы)
             Statement statement = connection.createStatement()) {
            String SQL = "INSERT INTO usersTable(name, lastName, age)"
                    + "VALUES (\"" + name + "\"," + " \"" + lastName + "\"," + age + ")";
            System.out.println("User с именем " + name + " добавлен в базу данных");
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Удаление User из таблицы ( по id )
    public void removeUserById(long id) {
        try (Connection connection = Util.createConnection();
             Statement statement = connection.createStatement()) {
            String SQL = "DELETE FROM usersTable WHERE ID = (" + id + ")";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Получение всех User(ов) из таблицы
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.createConnection();
             Statement statement = connection.createStatement()) {
            String SQL = "SELECT * FROM usersTable;";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                users.add(user);
                System.out.println(user.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    //Очистка содержания таблицы
    public void cleanUsersTable() {
        try (Connection connection = Util.createConnection();
             Statement statement = connection.createStatement()) {
            String SQL = "DELETE FROM usersTable";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
