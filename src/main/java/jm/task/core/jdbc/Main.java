package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        User denis = new User("Denis", "Ivaniv", (byte) 25);
        User petr = new User("Petr", "Petrov", (byte) 35);
        User sergey = new User("Sergey", "Sidorov", (byte) 15);
        User katy = new User("Katy", "Velicaya", (byte) 55);

        userService.createUsersTable();
        userService.saveUser(denis.getName(), denis.getLastName(), denis.getAge());
        userService.saveUser(petr.getName(), petr.getLastName(), petr.getAge());
        userService.saveUser(sergey.getName(), sergey.getLastName(), sergey.getAge());
        userService.saveUser(katy.getName(), katy.getLastName(), katy.getAge());
        List<User> userListNew = userService.getAllUsers();
        userListNew.forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }

}
