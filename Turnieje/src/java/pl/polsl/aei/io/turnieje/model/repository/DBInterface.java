/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.aei.io.turnieje.model.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Internal package database connection interface.
 * 
 * @author Piotr Uhl
 * @version 0.1.0
 */
class DBInterface {
    private Connection connection = null;
    
    public void open(String url, String user, String password) throws ClassNotFoundException, SQLException {
	Class.forName("org.apache.derby.jdbc.ClientDriver");
	connection = DriverManager.getConnection(url, user, password);
    }
    
    public void close() throws SQLException {
	connection.close();
    }
    
    public Statement createStatement() throws SQLException {
	return connection.createStatement();
    }
    
    public PreparedStatement createPreparedStatement(String query) throws SQLException {
	return connection.prepareStatement(query);
    }
}
