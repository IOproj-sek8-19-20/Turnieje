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
import pl.polsl.aei.io.turnieje.model.datamodel.Team;
import pl.polsl.aei.io.turnieje.model.datamodel.TeamId;
import pl.polsl.aei.io.turnieje.model.datamodel.Tournament;
import pl.polsl.aei.io.turnieje.model.datamodel.TournamentId;

/**
 * Realization of repository interface for matches.
 * 
 * @author Piotr Uhl
 */
public class MatchRepository implements IMatchRepository {
    
    private final DBInterface dbInterface;
    
    MatchRepository(DBInterface dbInterface) {
	this.dbInterface = dbInterface;
    }
    
    @Override
    public MatchId addMatch(Match match) {
	try {
	    PreparedStatement statement = dbInterface.createPreparedStatement("INSERT INTO Matches(tourId, matchDate, finished, winner, team1Id, team2Id) VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
	    statement.setInt(1, match.getTourId().id);
	    if (match.getDate() != null)
		statement.setDate(2, new java.sql.Date(match.getDate().getTime()));
	    else
		statement.setNull(2, java.sql.Types.DATE);
    	    statement.setBoolean(3, match.getFinished());
            if (match.getWinner() != null)
		statement.setInt(4, match.getWinner().id);
	    else
		statement.setNull(4, java.sql.Types.INTEGER);
	    if (match.getTeamId(1) != null)
		statement.setInt(5, match.getTeamId(1).id);
	    else
		statement.setNull(5, java.sql.Types.INTEGER);
	    if (match.getTeamId(2) != null)
		statement.setInt(6, match.getTeamId(2).id);
	    else
		statement.setNull(6, java.sql.Types.INTEGER);
	    statement.execute();
	    
	    ResultSet rs = statement.getGeneratedKeys();
	    if (rs.next()) {
	       return new MatchId(rs.getInt(1));
	    } 
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
	return null;
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
		match.setDate(rs.getDate("matchDate"));
		match.setFinished(rs.getBoolean("finished"));
		match.setWinner(new TeamId(rs.getInt("winner")));
		match.setTeamId(1, new TeamId(rs.getInt("team1Id")));
		match.setTeamId(2, new TeamId(rs.getInt("team2Id")));
		set.add(match);
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
	    ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Matches WHERE matchId=%d", id.id));
	    if (rs.next()) {
		Match match = new Match(rs.getInt("matchId"));
		match.setTourId(new TournamentId(rs.getInt("tourId")));
		match.setDate(rs.getDate("matchDate"));
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
    public Set<Match> getByTeam(Team team) {
	return getByTeam(team.id);
    }
    @Override
    public Set<Match> getByTeam(TeamId team) {
	try {
	    Statement statement = dbInterface.createStatement();
	    ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Matches WHERE team1Id=%d OR team2Id=%d", team.id, team.id));
	    Set<Match> set = new HashSet<>();
	    while (rs.next()) {
		Match match = new Match(rs.getInt("matchId"));
		match.setTourId(new TournamentId(rs.getInt("tourId")));
		match.setDate(rs.getDate("matchDate"));
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
    public Set<Match> getByTournament(TournamentId tournament) {
	try {
	    Statement statement = dbInterface.createStatement();
	    ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Matches WHERE tourId=%d", tournament.id));
	    Set<Match> set = new HashSet<>();
	    while (rs.next()) {
		Match match = new Match(rs.getInt("matchId"));
		match.setTourId(new TournamentId(rs.getInt("tourId")));
		match.setDate(rs.getDate("matchDate"));
		match.setFinished(rs.getBoolean("finished"));
		match.setWinner(new TeamId(rs.getInt("winner")));
		match.setTeamId(1, new TeamId(rs.getInt("team1Id")));
		match.setTeamId(2, new TeamId(rs.getInt("team2Id")));
                set.add(match);
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
	    PreparedStatement statement = dbInterface.createPreparedStatement("UPDATE Matches SET tourId=?, matchDate=?, finished=?, winner=?, team1Id=?, team2Id=? WHERE matchId=?");
	    statement.setInt(7, match.id.id);
	    statement.setInt(1, match.getTourId().id);
	    if (match.getDate() != null)
		statement.setDate(2, new java.sql.Date(match.getDate().getTime()));
	    else
		statement.setNull(2, java.sql.Types.DATE);
    	    statement.setBoolean(3, match.getFinished());
            if (match.getWinner() != null)
		statement.setInt(4, match.getWinner().id);
	    else
		statement.setNull(4, java.sql.Types.INTEGER);
	    if (match.getTeamId(1) != null)
		statement.setInt(5, match.getTeamId(1).id);
	    else
		statement.setNull(5, java.sql.Types.INTEGER);
	    if (match.getTeamId(2) != null)
		statement.setInt(6, match.getTeamId(2).id);
	    else
		statement.setNull(6, java.sql.Types.INTEGER);
	    return statement.executeUpdate() > 0;
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
    }
}
