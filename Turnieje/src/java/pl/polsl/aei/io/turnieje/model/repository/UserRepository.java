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
import pl.polsl.aei.io.turnieje.model.datamodel.Team;
import pl.polsl.aei.io.turnieje.model.datamodel.TeamId;
import pl.polsl.aei.io.turnieje.model.datamodel.User;
import pl.polsl.aei.io.turnieje.model.datamodel.UserId;

/**
 * Realization of repository interface for users.
 * 
 * @author Piotr Uhl
 */
public class UserRepository implements IUserRepository {
    
    private final DBInterface dbInterface;
    
    UserRepository(DBInterface dbInterface) {
	this.dbInterface = dbInterface;
    }
    
    @Override
    public UserId add(User user) {
	UserId ret = null;
	try {
	    /*Statement statement = dbInterface.createStatement();
	    if (user.getActive())
		statement.executeUpdate(String.format("INSERT INTO Users(email, passHash, firstName, lastName, active) VALUES ('%s', '%s', '%s', '%s', TRUE)", user.getEmail(), user.getPassHash(), user.getFirstName(), user.getLastName()), Statement.RETURN_GENERATED_KEYS);
	    else
		statement.executeUpdate(String.format("INSERT INTO Users(email, passHash, firstName, lastName, active) VALUES ('%s', '%s', '%s', '%s', FALSE)", user.getEmail(), user.getPassHash(), user.getFirstName(), user.getLastName()), Statement.RETURN_GENERATED_KEYS);
	    */
	    PreparedStatement statement = dbInterface.createPreparedStatement("INSERT INTO Users(email, passHash, firstName, lastName, active) VALUES (?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
	    statement.setString(1, user.getEmail());
	    statement.setString(2, user.getPassHash());
	    statement.setString(3, user.getFirstName());
	    statement.setString(4, user.getLastName());
	    statement.setBoolean(5, user.getActive());
	    statement.execute();
	    ResultSet rs = statement.getGeneratedKeys();
	    if (rs.next()) {
		for (Discipline k : user.getDisciplines()) {
		    Statement statement2 = dbInterface.createStatement();
		    statement2.executeUpdate(String.format("INSERT INTO UsersInDisciplines(userId, discId) VALUES (%d, %d)", user.id.id, k.id));;
		}
	        ret = new UserId(rs.getInt(1));
	    } 
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
	return ret;
    }
    @Override
    public boolean delete(User user) {
	return delete(user.id);
    }
    @Override
    public boolean delete(UserId user) {
	try {
	    Statement statement0 = dbInterface.createStatement();
	    statement0.executeUpdate(String.format("DELETE FROM UsersInDisciplines WHERE userId=%d", user.id));
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
		Statement statement2 = dbInterface.createStatement();
		ResultSet rs2 = statement2.executeQuery(String.format("SELECT uid.*, d.discName FROM UsersInDisciplines uid INNER JOIN Disciplines d ON uid.discId=d.discId WHERE userId=%d", user.id.id));
		while (rs2.next()) {
		    user.addDiscipline(new Discipline(rs2.getInt("discId"), rs2.getString("discName")));
		}
		rs2.close();
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
		Statement statement2 = dbInterface.createStatement();
		ResultSet rs2 = statement2.executeQuery(String.format("SELECT uid.*, d.discName FROM UsersInDisciplines uid INNER JOIN Disciplines d ON uid.discId=d.discId WHERE userId=%d", user.id.id));
		while (rs2.next()) {
		    user.addDiscipline(new Discipline(rs2.getInt("discId"), rs2.getString("discName")));
		}
		rs2.close();
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
		Statement statement2 = dbInterface.createStatement();
		ResultSet rs2 = statement2.executeQuery(String.format("SELECT uid.*, d.discName FROM UsersInDisciplines uid INNER JOIN Disciplines d ON uid.discId=d.discId WHERE userId=%d", user.id.id));
		while (rs2.next()) {
		    user.addDiscipline(new Discipline(rs2.getInt("discId"), rs2.getString("discName")));
		}
		rs2.close();
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
		Statement statement2 = dbInterface.createStatement();
		ResultSet rs2 = statement2.executeQuery(String.format("SELECT uid.*, d.discName FROM UsersInDisciplines uid INNER JOIN Disciplines d ON uid.discId=d.discId WHERE userId=%d", user.id.id));
		while (rs2.next()) {
		    user.addDiscipline(new Discipline(rs2.getInt("discId"), rs2.getString("discName")));
		}
		rs2.close();
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
	    
	    Statement statement0 = dbInterface.createStatement();
	    ResultSet rs = statement0.executeQuery(String.format("SELECT * FROM UsersInDisciplines WHERE userId=%d", user.id.id));
	    while (rs.next()) {
		Boolean del = true;
		for (Discipline k : user.getDisciplines()) {
		    if (k.id == rs.getInt("discId")) {
			del = false;
			break;
		    }
		}
		if (del) {
		    Statement statement1 = dbInterface.createStatement();
		    statement1.executeUpdate(String.format("DELETE FROM UsersInDisciplines WHERE userId=%d AND discId=%d", user.id.id, rs.getInt("discId")));
		}
	    }
	    
	    for (Discipline k : user.getDisciplines()) {
		Boolean add = true;
		rs.absolute(0);
		while (rs.next()) {
		    if (k.id == rs.getInt("discId")) {
			add = false;
			break;
		    }
		}
		if (add) {
		    Statement statement1 = dbInterface.createStatement();
		    statement1.executeUpdate(String.format("INSERT INTO UsersInDisciplines(userId, discId) VALUES (%d, %d)", user.id.id, rs.getInt("discId")));
		}
	    }
	    
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
