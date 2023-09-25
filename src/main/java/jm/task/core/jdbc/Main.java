package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;


public class Main {


    public static void main(String[] args) {
        // реализуйте алгоритм здесь


        Util.getSessionFactory();
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Marina", "Orange", (byte) 26);
        userService.saveUser("Tolyan", "Blue", (byte) 30);
        userService.saveUser("Kolyan", "Green", (byte) 29);
        userService.saveUser("Tao", "Bao", (byte) 50);
        userService.removeUserById(4);

        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.saveUser("Kolyan", "Green", (byte) 29);
        userService.saveUser("Tao", "Bao", (byte) 50);

        userService.getAllUsers();
        userService.dropUsersTable();

    }
}
