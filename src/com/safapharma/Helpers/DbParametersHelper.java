/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Helpers;

import com.safapharma.ModelObjects.DbParameters;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Natasha Malik
 */
public class DbParametersHelper {

    private static final String fileName = "env.xml";
    private DbParameters dbModel;

    public DbParametersHelper() throws ParserConfigurationException, SAXException, IOException {
        dbModel = new DbParameters();
        xmlReader();
    }

    private void xmlReader() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(fileName);
        document.getDocumentElement().normalize();
        dbModel.setUrl(document.getElementsByTagName("url").item(0).getTextContent());
        dbModel.setPort(document.getElementsByTagName("port").item(0).getTextContent());
        dbModel.setDbName(document.getElementsByTagName("dbName").item(0).getTextContent());
        dbModel.setUsername(document.getElementsByTagName("username").item(0).getTextContent());
        dbModel.setPassword(document.getElementsByTagName("password").item(0).getTextContent());
    }

    public DbParameters getDBModel() {
        return dbModel;
    }

}
