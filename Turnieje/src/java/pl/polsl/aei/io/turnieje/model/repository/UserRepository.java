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
import pl.polsl.aei.io.turnieje.model.datamodel.Team;
import pl.polsl.aei.io.turnieje.model.datamodel.TeamId;
import pl.polsl.aei.io.turnieje.model.datamodel.User;
import pl.polsl.aei.io.turnieje.model.datamodel.UserId;

/**
 * Realization of repository interface for users.
 * 
 * @author Piotr Uhl
 * @version 0.2.3
 */
public class UserRepository implements IUserRepository {
    
    private final DBInterface dbInterface;
    
    UserRepository(DBInterface dbInterface) {
	this.dbInterface = dbInterface;
    }
    
    @Override
    public UserId add(User user) {
	try {
	    Statement statement = dbInterface.createStatement();
	    if (user.getActive())
		statement.executeUpdate(String.format("INSERT INTO Users(email, passHash, firstName, lastName, active) VALUES ('%s', '%s', '%s', '%s', TRUE)", user.getEmail(), user.getPassHash(), user.getFirstName(), user.getLastName()), Statement.RETURN_GENERATED_KEYS);
	    else
		statement.executeUpdate(String.format("INSERT INTO Users(email, passHash, firstName, lastName, active) VALUES ('%s', '%s', '%s', '%s', FALSE)", user.getEmail(), user.getPassHash(), user.getFirstName(), user.getLastName()), Statement.RETURN_GENERATED_KEYS);
	    ResultSet rs = statement.getGeneratedKeys();
	    if (rs.next()) {
	       return new UserId(rs.getInt(1));
	    } 
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
	return null;
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
		set.add(user);
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
	    ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Users WHERE userId=%d", id.id));
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
		set.add(user);
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
	    PreparedStatement statement = dbInterface.createPreparedStatement("UPDATE Users SET email=?, passhash=?, firstname=?, lastname=?, active=? WHERE userId=?");
	    statement.setInt(6, user.id.id);
	    statement.setString(1, user.getEmail());
	    statement.setString(2, user.getPassHash());
	    statement.setString(3, user.getFirstName());
	    statement.setString(4, user.getLastName());
	    statement.setBoolean(5, user.getActive());
	    return statement.executeUpdate() > 0;
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
    }
}
