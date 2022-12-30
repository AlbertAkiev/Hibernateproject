package com.peaksoft;


import com.peaksoft.dao.UserDaoJdbcImpl;
import com.peaksoft.model.User;

public class App
{
    public static void main( String[] args ){
        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();
        userDaoJdbc.getAllUsers();
        userDaoJdbc.createUsersTable();
        userDaoJdbc.saveUser("Albert","Akiev", (byte) 17);
        userDaoJdbc.removeUserById(1);
        
    }
}
