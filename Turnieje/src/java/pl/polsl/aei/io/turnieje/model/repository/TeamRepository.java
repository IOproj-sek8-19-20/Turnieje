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
import pl.polsl.aei.io.turnieje.model.datamodel.Tournament;
import pl.polsl.aei.io.turnieje.model.datamodel.TournamentId;
import pl.polsl.aei.io.turnieje.model.datamodel.UserId;

/**
 * Realization of repository interface for teams.
 * 
 * @author Piotr Uhl
 * @version 0.2.1
 */
public class TeamRepository implements ITeamRepository {
    
    private final DBInterface dbInterface;
    
    TeamRepository(DBInterface dbInterface) {
	this.dbInterface = dbInterface;
    }
    
    @Override
    public boolean add(Team team) {
	try {
	    Statement statement = dbInterface.createStatement();
	    statement.executeUpdate(String.format("INSERT INTO Teams(name, capId) VALUES ('%s', '%d')", team.getName(), team.getCapitan().id));
	    return true;
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
    }
    @Override
    public boolean delete(Team team) {
	return delete(team.id);
    }
    @Override
    public boolean delete(TeamId team) {
	try {
	    Statement statement = dbInterface.createStatement();
	    if (statement.executeUpdate(String.format("DELETE FROM Teams WHERE teamId=%d", team.id)) == 0)
		return false;
	    else
		return true;
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
    }
    @Override
    public Set<Team> getAll() {
	try {
	    Statement statement = dbInterface.createStatement();
	    ResultSet rs = statement.executeQuery("SELECT * FROM Teams");
	    Set<Team> set = new HashSet<>();
	    while (rs.next()) {
		Team team = new Team(rs.getInt("teamId"));
		team.setName(rs.getString("name"));
		team.setCapitan(new UserId(rs.getInt("capId")));
	    }
	    return set;
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
    }
    @Override
    public Team getById(TeamId id) {
	try {
	    Statement statement = dbInterface.createStatement();
	    ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Teams WHERE teamId='%d'", id));
	    Set<Team> set = new HashSet<>();
	    if (rs.next()) {
		Team team = new Team(rs.getInt("teamId"));
		team.setName(rs.getString("name"));
		team.setCapitan(new UserId(rs.getInt("capId")));
		return team;
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
    public Set<Team> getByTournament(Tournament tournament) {
	return getByTournament(tournament.id);
    }
    @Override
    public Set<Team> getByTournament(TournamentId tournament) {
	try {
	    Statement statement = dbInterface.createStatement();
	    ResultSet rs = statement.executeQuery(String.format("SELECT t.* FROM Teams t INNER JOIN TeamsInTournaments tt ON t.teamId=tt.teamId WHERE t.tourId=%d", tournament.id));
	    Set<Team> set = new HashSet<>();
	    while (rs.next()) {
		Team team = new Team(rs.getInt("teamId"));
		team.setName(rs.getString("name"));
		team.setCapitan(new UserId(rs.getInt("capId")));
	    }
	    return set;
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
    }
    @Override
    public boolean update(Team team) {
	try {
	    boolean ret = false;
	    Statement statement = dbInterface.createStatement();
	    ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Teams WHERE teamId=%d", team.id.id));
	    if (rs.next()) {
		if (!team.getName().equals(rs.getString("name"))) {
		    rs.updateString("name", team.getName());
		    ret = true;
		}
		if (team.getCapitan().id != rs.getInt("capId")) {
		    rs.updateInt("capId", team.getCapitan().id);
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
