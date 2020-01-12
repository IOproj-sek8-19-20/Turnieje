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
import pl.polsl.aei.io.turnieje.model.datamodel.Tournament;
import pl.polsl.aei.io.turnieje.model.datamodel.TournamentId;
import pl.polsl.aei.io.turnieje.model.datamodel.TournamentMode;
import pl.polsl.aei.io.turnieje.model.datamodel.UserId;

/**
 * Realization of repository interface for tournaments.
 * 
 * @author Piotr Uhl
 * @version 0.2.1
 */
public class TournamentRepository implements ITournamentRepository {
    
    private final DBInterface dbInterface;
    
    TournamentRepository(DBInterface dbInterface) {
	this.dbInterface = dbInterface;
    }
    
    @Override
    public boolean add(Tournament tournament) {
	try {
	    PreparedStatement statement = dbInterface.createPreparedStatement("INSERT INTO Tournaments(name, startingDate, endingDate, adminId, modeId, discId, teamSize, finished) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
	    statement.setString(1, tournament.getName());
	    statement.setDate(2, (java.sql.Date)tournament.getStartingDate());
	    statement.setDate(3, (java.sql.Date)tournament.getEndingDate());
	    statement.setInt(4, tournament.getAdmin().id);
	    statement.setInt(5, 0); //todo
	    statement.setInt(6, 0); //todo
	    statement.setInt(7, tournament.getTeamSize());
	    statement.setBoolean(8, tournament.getFinished());
	    statement.execute();
	    return true;
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
    }
    @Override
    public boolean delete(Tournament tournament) {
	return delete(tournament.id);
    }
    @Override
    public boolean delete(TournamentId tournament) {
	try {
	    Statement statement = dbInterface.createStatement();
	    if (statement.executeUpdate(String.format("DELETE FROM Tournaments WHERE tourId=%d", tournament.id)) == 0)
		return false;
	    else
		return true;
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
    }
    @Override
    public Set<Tournament> getAll() {
	try {
	    Statement statement = dbInterface.createStatement();
	    ResultSet rs = statement.executeQuery("SELECT * FROM Tournaments");
	    Set<Tournament> set = new HashSet<>();
	    while (rs.next()) {
		Tournament tournament = new Tournament(rs.getInt("tourId"));
		tournament.setName(rs.getString("name"));
		tournament.setStartingDate(rs.getDate("startingDate"));
		tournament.setEndingDate(rs.getDate("endingDate"));
		tournament.setAdmin(new UserId(rs.getInt("adminId")));
		tournament.setMode(TournamentMode.NONE); //temp, todo
		tournament.setDiscipline(Discipline.NONE); //temp, todo
		tournament.setTeamSize(rs.getInt("teamSize"));
		tournament.setFinished(rs.getBoolean("finished"));
	    }
	    return set;
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
    }
    @Override
    public boolean update(Tournament tournament) {
	try {
	    boolean ret = false;
	    Statement statement = dbInterface.createStatement();
	    ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Tournaments WHERE tourId=%d", tournament.id.id));
	    if (rs.next()) {
		if (!tournament.getName().equals(rs.getString("name"))) {
		    rs.updateString("name", tournament.getName());
		    ret = true;
		}
		if (!tournament.getStartingDate().equals(rs.getDate("startingDate"))) {
		    rs.updateDate("startingDate", (java.sql.Date)tournament.getStartingDate());
		    ret = true;
		}
		if (!tournament.getEndingDate().equals(rs.getDate("endingDate"))) {
		    rs.updateDate("endingDate", (java.sql.Date)tournament.getEndingDate());
		    ret = true;
		}
		if (tournament.getAdmin().id != rs.getInt("adminId")) {
		    rs.updateInt("adminId", tournament.getAdmin().id);
		    ret = true;
		}
		//todo: updating modeId
		//todo: updating discId
		if (tournament.getTeamSize() != rs.getInt("teamSize")) {
		    rs.updateInt("teamSize", tournament.getTeamSize());
		    ret = true;
		}
		if (tournament.getFinished() != rs.getBoolean("finished")) {
		    rs.updateBoolean("finished", tournament.getFinished());
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
