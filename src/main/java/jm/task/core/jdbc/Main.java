package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserService userService = new UserServiceImpl();

        //userService.createUsersTable();     //Создание таблицы User(ов)

        userService.saveUser("Жёлтый", "Микеланджело", (byte) 15);  //Добавление 4 User(ов) в таблицу с
        userService.saveUser("Красный", "Рафаэль", (byte) 15);      //данными на свой выбор. После
        userService.saveUser("Синий", "Леонардо", (byte) 15);       //каждого добавления должен быть
        userService.saveUser("Фиолетовый", "Донателло", (byte) 15); //вывод в консоль (User с именем –
                                                                                    //name добавлен в базу данных)
        //userService.getAllUsers();      //Получение всех User из базы и вывод в консоль ( должен быть переопределен
                                                //toString в классе User)
        //userService.cleanUsersTable();  //Очистка таблицы User(ов)

        //userService.dropUsersTable();   //Удаление таблицы
    }
}
