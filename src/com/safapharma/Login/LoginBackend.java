/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Login;

import com.safapharma.DataAccessObjects.UserDAO;
import com.safapharma.ModelObjects.User;

/**
 *
 * @author Natasha Malik
 */
public class LoginBackend {

    UserDAO userDAO;

    public LoginBackend() {
        userDAO = new UserDAO();
    }

    public boolean validateUsername(String userName) throws Exception {
        return (userDAO.isUserNameEnteredValid(userName));
    }

    public String fetchUserPassword(String userName) throws Exception {
        String password = userDAO.getPassword(userName);
        return password;
    }

    public void fetchCompleteUserDetails(User u) throws Exception {
        userDAO.getUser(u);
    }

}
