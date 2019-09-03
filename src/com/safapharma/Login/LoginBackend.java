/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Login;

import com.safapharma.DataAccessObjects.UserDAO;
import com.safapharma.ModelObjects.User;
import java.io.IOException;
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Natasha Malik
 */
public class LoginBackend {
    UserDAO userDAO;
    
    public LoginBackend(){
        userDAO = new UserDAO();
    }
    
    public boolean checkUsername(String userName) throws Exception {
        return (userDAO.isUserNameEnteredValid(userName));
    }
    
    public String getUserPassword(String userName) throws Exception {
        String password = userDAO.fetchPassword(userName);
        return password;
    }
    
    public User getDetails(User u) {
        User user = userDAO.fetchUserDetail(u);
        return user;
    }
    
}
