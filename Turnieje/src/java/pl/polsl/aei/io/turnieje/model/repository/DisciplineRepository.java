/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.aei.io.turnieje.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import pl.polsl.aei.io.turnieje.model.datamodel.Discipline;

/**
 * Realization of repository interface for disciplines
 * @author Piotr Uhl
 */
public class DisciplineRepository implements IDisciplineRepository {
    
    private final DBInterface dbInterface;
    
    DisciplineRepository(DBInterface dbInterface) {
	this.dbInterface = dbInterface;
    }
    
    @Override
    public Discipline add(String name) {
	try {
	    PreparedStatement statement = dbInterface.createPreparedStatement("INSERT INTO Disciplines(discName) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);
	    statement.setString(1, name);
	    statement.execute();
	    ResultSet rs = statement.getGeneratedKeys();
	    if (rs.next()) {
	       return new Discipline(rs.getInt(1), name);
	    } 
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
	return null;
    }
    @Override
    public Set<Discipline> getAll() {
	try {
	    Statement statement = dbInterface.createStatement();
	    ResultSet rs = statement.executeQuery("SELECT * FROM Disciplines");
	    Set<Discipline> set = new HashSet<>();
	    while (rs.next()) {
		Discipline discipline = new Discipline(rs.getInt("discId"), rs.getString("discName"));
		set.add(discipline);
	    }
	    return set;
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
    }
    @Override
    public Discipline getByName(String name) {
	try {
	    PreparedStatement statement = dbInterface.createPreparedStatement("SELECT * FROM Disciplines WHERE discName=?");
	    statement.setString(1, name);
	    ResultSet rs = statement.executeQuery();
	    if (rs.next()) {
		return new Discipline(rs.getInt("discId"), rs.getString("discName"));
	    }
	    return null;
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
    }
    @Override
    public boolean rename(Discipline discipline, String newName) {
	try {
	    PreparedStatement statement = dbInterface.createPreparedStatement("UPDATE Disciplines SET discName=? WHERE discId=?");
	    statement.setString(1, newName);
	    statement.setInt(2, discipline.id);
	    return statement.executeUpdate() > 0;
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
    }
    @Override
    public boolean rename(String oldName, String newName) {
	try {
	    PreparedStatement statement = dbInterface.createPreparedStatement("UPDATE Disciplines SET discName=? WHERE discName=?");
	    statement.setString(1, newName);
	    statement.setString(2, oldName);
	    return statement.executeUpdate() > 0;
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
    }
}
