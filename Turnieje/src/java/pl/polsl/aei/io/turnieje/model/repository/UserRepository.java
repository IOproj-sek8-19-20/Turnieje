/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.aei.io.turnieje.model.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
	try {
	    Statement statement = dbInterface.createStatement();
	    if (user.getActive())
		statement.executeUpdate(String.format("INSERT INTO Users(email, passHash, firstName, lastName, active) VALUES ('%s', '%s', '%s', '%s', TRUE)", user.getEmail(), user.getPassHash(), user.getFirstName(), user.getLastName()));
	    else
		statement.executeUpdate(String.format("INSERT INTO Users(email, passHash, firstName, lastName, active) VALUES ('%s', '%s', '%s', '%s', FALSE)", user.getEmail(), user.getPassHash(), user.getFirstName(), user.getLastName()));
	    return true;
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
    }
    @Override
    public boolean delete(User user) {
	return delete(user.id);
    }
    @Override
    public boolean delete(UserId user) {
	try {
	    Statement statement = dbInterface.createStatement();
	    if (statement.executeUpdate(String.format("DELETE FROM Users WHERE userId=%d", user.id)) == 0)
		return false;
	    else
		return true;
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
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
            System.out.println(exc.getMessage());
	    throw new RuntimeException(exc);
	}
    }
    @Override
    public User getById(UserId id) {
	try {
	    Statement statement = dbInterface.createStatement();
	    ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Users WHERE userId=%d", id));
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
	try {
	    boolean ret = false;
	    Statement statement = dbInterface.createStatement();
	    ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Users WHERE userId=%d", user.id.id));
	    if (rs.next()) {
		if (!user.getEmail().equals(rs.getString("email"))) {
		    rs.updateString("email", user.getEmail());
		    ret = true;
		}
		if (!user.getPassHash().equals(rs.getString("passHash"))) {
		    rs.updateString("passHash", user.getPassHash());
		    ret = true;
		}
		if (user.getFirstName().equals(rs.getString("firstName"))) {
		    rs.updateString("firstName", user.getFirstName());
		    ret = true;
		}
		if (user.getLastName().equals(rs.getString("lastName"))) {
		    rs.updateString("lastName", user.getLastName());
		    ret = true;
		}
		if (user.getActive() == rs.getBoolean("active")) {
		    rs.updateBoolean("active", user.getActive());
		    ret = true;
		}
		rs.updateRow();
	    }
	    return ret;
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
    }
}
