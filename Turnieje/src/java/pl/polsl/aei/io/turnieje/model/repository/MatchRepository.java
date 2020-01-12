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
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public boolean delete(MatchId match) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public boolean delete(Match match) {
	throw new UnsupportedOperationException("Not implenented yet.");
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
	    ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Matches WHERE matchId='%d'", id));
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
	throw new UnsupportedOperationException("Not implenented yet.");
    }
}
