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
import pl.polsl.aei.io.turnieje.model.datamodel.Match;
import pl.polsl.aei.io.turnieje.model.datamodel.MatchId;
import pl.polsl.aei.io.turnieje.model.datamodel.TeamId;
import pl.polsl.aei.io.turnieje.model.datamodel.Tournament;
import pl.polsl.aei.io.turnieje.model.datamodel.TournamentId;

/**
 * Realization of repository interface for matches.
 * 
 * @author Piotr Uhl
 * @version 0.2.1
 */
public class MatchRepository implements IMatchRepository {
    
    private final DBInterface dbInterface;
    
    MatchRepository(DBInterface dbInterface) {
	this.dbInterface = dbInterface;
    }
    
    @Override
    public boolean addMatch(Match match) {
	try {
	    PreparedStatement statement = dbInterface.createPreparedStatement("INSERT INTO Matches(tourId, date, finished, winner, team1Id, team2Id) VALUES (?, ?, ?, ?, ?, ?)");
	    statement.setInt(1, match.getTourId().id);
	    statement.setDate(2, (java.sql.Date)match.getDate());
    	    statement.setBoolean(3, match.getFinished());
	    statement.setInt(4, match.getWinner().id);
	    statement.setInt(5, match.getTeamId(1).id);
	    statement.setInt(6, match.getTeamId(1).id);
	    statement.execute();
	    return true;
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
    }
    @Override
    public boolean delete(Match match) {
	return delete(match.id);
    }
    @Override
    public boolean delete(MatchId match) {
	try {
	    Statement statement = dbInterface.createStatement();
	    if (statement.executeUpdate(String.format("DELETE FROM Matches WHERE matchId=%d", match.id)) == 0)
		return false;
	    else
		return true;
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
    }
    @Override
    public Set<Match> getAll() {
	try {
	    Statement statement = dbInterface.createStatement();
	    ResultSet rs = statement.executeQuery("SELECT * FROM Matches");
	    Set<Match> set = new HashSet<>();
	    while (rs.next()) {
		Match match = new Match(rs.getInt("matchId"));
		match.setTourId(new TournamentId(rs.getInt("tourId")));
		match.setDate(rs.getDate("date"));
		match.setFinished(rs.getBoolean("finished"));
		match.setWinner(new TeamId(rs.getInt("winner")));
		match.setTeamId(1, new TeamId(rs.getInt("team1Id")));
		match.setTeamId(2, new TeamId(rs.getInt("team2Id")));
	    }
	    return set;
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
    }
    @Override
    public Match getById(MatchId id) {
	try {
	    Statement statement = dbInterface.createStatement();
	    ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Matches WHERE matchId=%d", id));
	    if (rs.next()) {
		Match match = new Match(rs.getInt("matchId"));
		match.setTourId(new TournamentId(rs.getInt("tourId")));
		match.setDate(rs.getDate("date"));
		match.setFinished(rs.getBoolean("finished"));
		match.setWinner(new TeamId(rs.getInt("winner")));
		match.setTeamId(1, new TeamId(rs.getInt("team1Id")));
		match.setTeamId(2, new TeamId(rs.getInt("team2Id")));
		return match;
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
    public Set<Match> getByTournament(TournamentId tournament) {
	try {
	    Statement statement = dbInterface.createStatement();
	    ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Matches WHERE tourId=%d", tournament.id));
	    Set<Match> set = new HashSet<>();
	    while (rs.next()) {
		Match match = new Match(rs.getInt("matchId"));
		match.setTourId(new TournamentId(rs.getInt("tourId")));
		match.setDate(rs.getDate("date"));
		match.setFinished(rs.getBoolean("finished"));
		match.setWinner(new TeamId(rs.getInt("winner")));
		match.setTeamId(1, new TeamId(rs.getInt("team1Id")));
		match.setTeamId(2, new TeamId(rs.getInt("team2Id")));
	    }
	    return set;
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
    }
    @Override
    public Set<Match> getByTournament(Tournament tournament) {
	return getByTournament(tournament.id);
    }
    @Override
    public boolean update(Match match) {
	try {
	    boolean ret = false;
	    Statement statement = dbInterface.createStatement();
	    ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Matches WHERE matchId=%d", match.id.id));
	    if (rs.next()) {
		if (match.getTourId().id != rs.getInt("tourId")) {
		    rs.updateInt("tourId", match.getTourId().id);
		    ret = true;
		}
		if (!match.getDate().equals(rs.getDate("date"))) {
		    rs.updateDate("date", (java.sql.Date)match.getDate());
		    ret = true;
		}
		if (match.getFinished() != rs.getBoolean("finished")) {
		    rs.updateBoolean("finished", match.getFinished());
		    ret = true;
		}
		if (match.getWinner().id != rs.getInt("winner")) {
		    rs.updateInt("winner", match.getWinner().id);
		    ret = true;
		}
		if (match.getTeamId(1).id != rs.getInt("team1Id")) {
		    rs.updateInt("team1Id", match.getTeamId(1).id);
		    ret = true;
		}
		if (match.getTeamId(2).id != rs.getInt("team1Id")) {
		    rs.updateInt("team1Id", match.getTeamId(2).id);
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
