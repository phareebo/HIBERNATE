package jm.task.core.jdbc.model;

import javax.persistence.*;

//приложение, которое взаимодействует с базой оперируя пользователем (класс User)
//Архитектура приложения создана с опорой на паттерн проектирования MVC ( частично, у нас не WEB приложение)

@Entity     // класс будет отображаться в БД
@Table(name = "usersTable")     //к какой таблице привяжем класс
public class User {     //МОДЕЛЬ, которая хранит данные
         // этот столбец в таблице является Primary key
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        //Все поля должны быть private

    @Column(name = "name")      // к какому столбцу из таблицы привяжем поле
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "age")
    private Byte age;

    public User() {

    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public User(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User: id = " + id +
                ", name ='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age;
    }
}
