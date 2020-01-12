/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.aei.io.turnieje.model.repository;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import pl.polsl.aei.io.turnieje.model.datamodel.Team;
import pl.polsl.aei.io.turnieje.model.datamodel.TeamId;
import pl.polsl.aei.io.turnieje.model.datamodel.User;
import pl.polsl.aei.io.turnieje.model.datamodel.UserId;

/**
 * Realization of repository interface for users.
 * 
 * @author Piotr Uhl
 * @version 0.2.1
 */
public class UserRepository implements IUserRepository {
    
    private final DBInterface dbInterface;
    
    UserRepository(DBInterface dbInterface) {
	this.dbInterface = dbInterface;
    }
    
    @Override
    public boolean add(User user) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public boolean delete(User user) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public boolean delete(UserId user) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public Set<User> getAll() {
	try {
	    Statement statement = dbInterface.createStatement();
	    ResultSet rs = statement.executeQuery("SELECT * FROM Users");
	    Set<User> set = new HashSet<>();
	    while (rs.next()) {
		User user = new User(rs.getInt("userId"));
		user.setEmail(rs.getString("email"));
		user.setPassHash(rs.getString("passHash"));
		user.setFirstName(rs.getString("firstName"));
		user.setLastName(rs.getString("lastName"));
		user.setActive(rs.getBoolean("active"));
	    }
	    return set;
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
    }
    @Override
    public User getByEmail(String email) {
	try {
	    Statement statement = dbInterface.createStatement();
	    ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Users WHERE email='%s'", email));
	    if (rs.next()) {
		User user = new User(rs.getInt("userId"));
		user.setEmail(rs.getString("email"));
		user.setPassHash(rs.getString("passHash"));
		user.setFirstName(rs.getString("firstName"));
		user.setLastName(rs.getString("lastName"));
		user.setActive(rs.getBoolean("active"));
		return user;
	    }
	    else {
		return null;
	    }
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
    }
    @Override
    public User getById(UserId id) {
	try {
	    Statement statement = dbInterface.createStatement();
	    ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Users WHERE userId='%d'", id));
	    if (rs.next()) {
		User user = new User(rs.getInt("userId"));
		user.setEmail(rs.getString("email"));
		user.setPassHash(rs.getString("passHash"));
		user.setFirstName(rs.getString("firstName"));
		user.setLastName(rs.getString("lastName"));
		user.setActive(rs.getBoolean("active"));
		return user;
	    }
	    else {
		return null;
	    }
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
    }
    @Override
    public Set<User> getByTeam(Team team) {
	return getByTeam(team.id);
    }
    @Override
    public Set<User> getByTeam(TeamId team) {
	try {
	    Statement statement = dbInterface.createStatement();
	    ResultSet rs = statement.executeQuery(String.format("SELECT u.* FROM Users u INNER JOIN PlayersInTeams pt ON u.USERID=pt.USERID WHERE pt.TEAMID=%d", team.id));
	    Set<User> set = new HashSet<>();
	    while (rs.next()) {
		User user = new User(rs.getInt("userId"));
		user.setEmail(rs.getString("email"));
		user.setPassHash(rs.getString("passHash"));
		user.setFirstName(rs.getString("firstName"));
		user.setLastName(rs.getString("lastName"));
		user.setActive(rs.getBoolean("active"));
	    }
	    return set;
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
    }
    @Override
    public boolean update(User user) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
}
