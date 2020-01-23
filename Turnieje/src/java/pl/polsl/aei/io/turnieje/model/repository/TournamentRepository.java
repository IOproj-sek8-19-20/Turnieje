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
import pl.polsl.aei.io.turnieje.model.datamodel.TeamId;
import pl.polsl.aei.io.turnieje.model.datamodel.TeamInTournament;
import pl.polsl.aei.io.turnieje.model.datamodel.Tournament;
import pl.polsl.aei.io.turnieje.model.datamodel.TournamentId;
import pl.polsl.aei.io.turnieje.model.datamodel.TournamentMode;
import pl.polsl.aei.io.turnieje.model.datamodel.UserId;

/**
 * Realization of repository interface for tournaments.
 * 
 * @author Piotr Uhl
 */
public class TournamentRepository implements ITournamentRepository {
    
    private final DBInterface dbInterface;
    
    TournamentRepository(DBInterface dbInterface) {
	this.dbInterface = dbInterface;
    }
    
    @Override
    public TournamentId add(Tournament tournament) {
	TournamentId ret = null;
	try {
	    PreparedStatement statement = dbInterface.createPreparedStatement("INSERT INTO Tournaments(name, startingDate, endingDate, adminId, modeId, discId, teamSize, finished) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
	    statement.setString(1, tournament.getName());
	    statement.setDate(2, new java.sql.Date(tournament.getStartingDate().getTime()));
	    statement.setDate(3, new java.sql.Date(tournament.getEndingDate().getTime()));
	    statement.setInt(4, tournament.getAdmin().id);
	    statement.setInt(5, 1); //todo
	    statement.setInt(6, 1); //todo
	    statement.setInt(7, tournament.getTeamSize());
	    statement.setBoolean(8, tournament.getFinished());
	    statement.execute();
	    ResultSet rs = statement.getGeneratedKeys();
	    if (rs.next()) {
	        ret = new TournamentId(rs.getInt(1));
		for (TeamInTournament k : tournament.getTeams()) {
                    PreparedStatement statement2;
		    statement2 = dbInterface.createPreparedStatement("INSERT INTO TeamsInTournaments(tourId, teamId, joinDate, points, eliminated, groupNr) VALUES (?, ?, ?, ?, ?, ?)");
		    statement2.setInt(1, k.tourId.id);
		    statement2.setInt(2, k.teamId.id);
		    if (k.joinDate != null)
			statement2.setDate(3, new java.sql.Date(k.joinDate.getTime()));
		    else
			statement2.setNull(3, java.sql.Types.DATE);
		    //uncomment ifs if throws NullPointerException
		    //if (k.points != null) 
			statement2.setInt(4, k.points);
		    //else
			//statement2.setNull(4, java.sql.Types.INTEGER);
		    //if (k.eliminated != null)
			statement2.setBoolean(5, k.eliminated);
		    //else
			//statement2.setNull(5, java.sql.Types.BOOLEAN);
		    //if (k.groupNr != null)
			statement2.setInt(6, k.groupNr);
		    //else
			//statement2.setNull(6, java.sql.Types.INTEGER);
                    statement2.execute();
		}
	    }
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
	return ret;
    }
    /*@Override
    public boolean delete(Tournament tournament) {
	return delete(tournament.id);
    }*/
    /*@Override
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
    }*/
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
		Statement statement2 = dbInterface.createStatement();
		ResultSet rs2 = statement2.executeQuery(String.format("SELECT teamId, joinDate, points, eliminated, groupNr FROM TeamsInTournaments WHERE tourId=%d", tournament.id.id));
		while (rs2.next()) {
		    TeamInTournament tit = new TeamInTournament();
		    tit.tourId = tournament.id;
		    tit.teamId = new TeamId(rs2.getInt("teamId"));
		    tit.joinDate = rs2.getDate("joinDate");
		    tit.points = rs2.getInt("points");
		    tit.eliminated = rs2.getBoolean("eliminated");
		    tit.groupNr = rs2.getInt("groupNr");
		    tournament.addTeam(tit);
		}
	        rs2.close();
		set.add(tournament);
	    }
	    return set;
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
    }
    
    @Override
    public Tournament getById(TournamentId id) {
	try {
	    Statement statement = dbInterface.createStatement();
	    ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Tournaments WHERE tourId=%d", id.id));
	    if (rs.next()) {
		Tournament tournament = new Tournament(rs.getInt("tourId"));
		tournament.setName(rs.getString("name"));
		tournament.setStartingDate(rs.getDate("startingDate"));
		tournament.setEndingDate(rs.getDate("endingDate"));
		tournament.setAdmin(new UserId(rs.getInt("adminId")));
		tournament.setMode(TournamentMode.NONE); //temp, todo
		tournament.setDiscipline(Discipline.NONE); //temp, todo
		tournament.setTeamSize(rs.getInt("teamSize"));
		tournament.setFinished(rs.getBoolean("finished"));
		return tournament;
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
    public Tournament getByName(String name) {
	try {
	    Statement statement = dbInterface.createStatement();
	    ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Tournaments WHERE name='%s'", name));
	    if (rs.next()) {
		Tournament tournament = new Tournament(rs.getInt("tourId"));
		tournament.setName(rs.getString("name"));
		tournament.setStartingDate(rs.getDate("startingDate"));
		tournament.setEndingDate(rs.getDate("endingDate"));
		tournament.setAdmin(new UserId(rs.getInt("adminId")));
		tournament.setMode(TournamentMode.NONE); //temp, todo
		tournament.setDiscipline(Discipline.NONE); //temp, todo
		tournament.setTeamSize(rs.getInt("teamSize"));
		tournament.setFinished(rs.getBoolean("finished"));
		return tournament;
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
    public boolean update(Tournament tournament) {
	try {
	    PreparedStatement statement = dbInterface.createPreparedStatement("UPDATE Tournaments SET name=?, startingDate=?, endingDate=?, adminId=?, modeId=?, discId=?, teamSize=?, finished=? WHERE tourId=?");
	    statement.setInt(9, tournament.id.id);
	    statement.setString(1, tournament.getName());
	    statement.setDate(2, new java.sql.Date(tournament.getStartingDate().getTime()));
	    statement.setDate(3, new java.sql.Date(tournament.getEndingDate().getTime()));
	    statement.setInt(4, tournament.getAdmin().id);
	    statement.setInt(5, 1); //todo: updating modeId
	    statement.setInt(6, 1); //todo: updating discId
	    statement.setInt(7, tournament.getTeamSize());
	    statement.setBoolean(8, tournament.getFinished());
	    return statement.executeUpdate() > 0;
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
    }
}
