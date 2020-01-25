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
import pl.polsl.aei.io.turnieje.model.datamodel.PlayerInTeam;
import pl.polsl.aei.io.turnieje.model.datamodel.Team;
import pl.polsl.aei.io.turnieje.model.datamodel.TeamId;
import pl.polsl.aei.io.turnieje.model.datamodel.Tournament;
import pl.polsl.aei.io.turnieje.model.datamodel.TournamentId;
import pl.polsl.aei.io.turnieje.model.datamodel.UserId;

/**
 * Realization of repository interface for teams.
 * 
 * @author Piotr Uhl
 */
public class TeamRepository implements ITeamRepository {
    
    private final DBInterface dbInterface;
    
    TeamRepository(DBInterface dbInterface) {
	this.dbInterface = dbInterface;
    }
    
    @Override
    public TeamId add(Team team) {
	TeamId ret = null;
	try {
	    PreparedStatement statement = dbInterface.createPreparedStatement("INSERT INTO Teams(name, capId) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
	    statement.setString(1, team.getName());
	    statement.setInt(2, team.getCapitan().id);
	    statement.execute();
	    ResultSet rs = statement.getGeneratedKeys();
	    if (rs.next()) {
		for (Discipline k : team.getDisciplines()) {
		    Statement statement2 = dbInterface.createStatement();
		    statement2.executeUpdate(String.format("INSERT INTO TeamsInDisciplines(teamId, discId) VALUES (%d, %d)", team.id.id, k.id));;
		}
		ret = new TeamId(rs.getInt(1));
		for (PlayerInTeam k : team.getPlayers()) {
                    PreparedStatement statement2;
		    if (k.joinDate != null) {
			statement2 = dbInterface.createPreparedStatement("INSERT INTO PlayersInTeam(teamId, userId, joinDate) VALUES (?, ?, ?)");
			statement2.setDate(3, new java.sql.Date(k.joinDate.getTime()));
 		    }
		    else {
		       statement2 = dbInterface.createPreparedStatement("INSERT INTO PlayersInTeam(teamId, userId) VALUES (?, ?)");
		    }
		    statement2.setInt(1, k.teamId.id);
		    statement2.setInt(2, k.userId.id);
                    statement2.execute();
		}
	    }
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
	return ret;
    }
    @Override
    public boolean delete(Team team) {
	return delete(team.id);
    }
    @Override
    public boolean delete(TeamId team) {
	try {
	    Statement statement = dbInterface.createStatement();
	    statement.executeUpdate(String.format("DELETE FROM TeamsInDisciplines WHERE teamId=%d", team.id));
	    statement.executeUpdate(String.format("DELETE FROM PlayersInTeams WHERE teamId=%d", team.id));
	    statement.executeUpdate(String.format("DELETE FROM TeamsInTournaments WHERE teamId=%d", team.id));
	    statement.executeUpdate(String.format("DELETE FROM TeamsInDisciplines WHERE teamId=%d", team.id));
	    statement.executeUpdate(String.format("UPDATE Matches SET winner=NULL WHERE winner=%d", team.id));
	    statement.executeUpdate(String.format("UPDATE Matches SET team1Id=NULL WHERE team1Id=%d", team.id));
	    statement.executeUpdate(String.format("UPDATE Matches SET team2Id=NULL WHERE team2Id=%d", team.id));
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
                Statement statement2 = dbInterface.createStatement();
		ResultSet rs2 = statement2.executeQuery(String.format("SELECT userId, joinDate FROM PlayersInTeams WHERE teamId=%d", team.id.id));
		while (rs2.next()) {
		    PlayerInTeam pit = new PlayerInTeam();
		    pit.teamId = team.id;
		    pit.userId = new UserId(rs2.getInt("userId"));
		    pit.joinDate = rs2.getDate("joinDate");
		    team.addPlayer(pit);
		}
	        rs2.close();
		Statement statement3 = dbInterface.createStatement();
		ResultSet rs3 = statement3.executeQuery(String.format("SELECT tid.*, d.discName FROM TeamsInDisciplines tid INNER JOIN Disciplines d ON tid.discId=d.discId WHERE teamId=%d", team.id.id));
		while (rs3.next()) {
		    team.addDiscipline(new Discipline(rs3.getInt("discId"), rs3.getString("discName")));
		}
		rs3.close();
		set.add(team);
	    }
            rs.close();
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
	    ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Teams WHERE teamId=%d", id.id));
	    if (rs.next()) {
		Team team = new Team(rs.getInt("teamId"));
		team.setName(rs.getString("name"));
		team.setCapitan(new UserId(rs.getInt("capId")));
                Statement statement2 = dbInterface.createStatement();
		ResultSet rs2 = statement2.executeQuery(String.format("SELECT userId, joinDate FROM PlayersInTeams WHERE teamId=%d", team.id.id));
		while (rs2.next()) {
		    PlayerInTeam pit = new PlayerInTeam();
		    pit.teamId = team.id;
		    pit.userId = new UserId(rs2.getInt("userId"));
		    pit.joinDate = rs2.getDate("joinDate");
		    team.addPlayer(pit);
		}
		rs2.close();
		Statement statement3 = dbInterface.createStatement();
		ResultSet rs3 = statement3.executeQuery(String.format("SELECT tid.*, d.discName FROM TeamsInDisciplines tid INNER JOIN Disciplines d ON tid.discId=d.discId WHERE teamId=%d", team.id.id));
		while (rs3.next()) {
		    team.addDiscipline(new Discipline(rs3.getInt("discId"), rs3.getString("discName")));
		}
		rs3.close();
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
    public Team getByName(String name) {
	try {
	    Statement statement = dbInterface.createStatement();
	    ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Teams WHERE name='%s'", name));
	    if (rs.next()) {
		Team team = new Team(rs.getInt("teamId"));
		team.setName(rs.getString("name"));
		team.setCapitan(new UserId(rs.getInt("capId")));
                Statement statement2 = dbInterface.createStatement();
		ResultSet rs2 = statement2.executeQuery(String.format("SELECT userId, joinDate FROM PlayersInTeams WHERE teamId=%d", team.id.id));
		while (rs2.next()) {
		    PlayerInTeam pit = new PlayerInTeam();
		    pit.teamId = team.id;
		    pit.userId = new UserId(rs2.getInt("userId"));
		    pit.joinDate = rs2.getDate("joinDate");
		    team.addPlayer(pit);
		}
		rs2.close();
		Statement statement3 = dbInterface.createStatement();
		ResultSet rs3 = statement3.executeQuery(String.format("SELECT tid.*, d.discName FROM TeamsInDisciplines tid INNER JOIN Disciplines d ON tid.discId=d.discId WHERE teamId=%d", team.id.id));
		while (rs3.next()) {
		    team.addDiscipline(new Discipline(rs3.getInt("discId"), rs3.getString("discName")));
		}
		rs3.close();
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
	    ResultSet rs = statement.executeQuery(String.format("SELECT t.* FROM Teams t INNER JOIN TeamsInTournaments tt ON t.teamId=tt.teamId WHERE tt.tourId=%d", tournament.id));
	    Set<Team> set = new HashSet<>();
	    while (rs.next()) {
		Team team = new Team(rs.getInt("teamId"));
		team.setName(rs.getString("name"));
		team.setCapitan(new UserId(rs.getInt("capId")));
                Statement statement2 = dbInterface.createStatement();
		ResultSet rs2 = statement2.executeQuery(String.format("SELECT userId, joinDate FROM PlayersInTeams WHERE teamId=%d", team.id.id));
		while (rs2.next()) {
		    PlayerInTeam pit = new PlayerInTeam();
		    pit.teamId = team.id;
		    pit.userId = new UserId(rs2.getInt("userId"));
		    pit.joinDate = rs2.getDate("joinDate");
		    team.addPlayer(pit);
		}
		rs2.close();
		Statement statement3 = dbInterface.createStatement();
		ResultSet rs3 = statement3.executeQuery(String.format("SELECT tid.*, d.discName FROM TeamsInDisciplines tid INNER JOIN Disciplines d ON tid.discId=d.discId WHERE teamId=%d", team.id.id));
		while (rs3.next()) {
		    team.addDiscipline(new Discipline(rs3.getInt("discId"), rs3.getString("discName")));
		}
		rs3.close();
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
	    
	    Statement statement00 = dbInterface.createStatement();
	    ResultSet rs0 = statement00.executeQuery(String.format("SELECT * FROM TeamsInDisciplines WHERE teamId=%d", team.id.id));
	    while (rs0.next()) {
		Boolean del = true;
		for (Discipline k : team.getDisciplines()) {
		    if (k.id == rs0.getInt("discId")) {
			del = false;
			break;
		    }
		}
		if (del) {
		    Statement statement1 = dbInterface.createStatement();
		    statement1.executeUpdate(String.format("DELETE FROM TeamsInDisciplines WHERE teamId=%d AND discId=%d", team.id.id, rs0.getInt("discId")));
		}
	    }
	    
	    for (Discipline k : team.getDisciplines()) {
		Boolean add = true;
		rs0.absolute(0);
		while (rs0.next()) {
		    if (k.id == rs0.getInt("discId")) {
			add = false;
			break;
		    }
		}
		if (add) {
		    Statement statement1 = dbInterface.createStatement();
		    statement1.executeUpdate(String.format("INSERT INTO TeamsInDisciplines(teamId, discId) VALUES (%d, %d)", team.id.id, k.id));
		}
	    }
	    
	    Statement statement = dbInterface.createStatement();
	    ResultSet rs = statement.executeQuery(String.format("SELECT * FROM PlayersInTeams WHERE teamId=%d", team.id.id));
	    while (rs.next()) {
		int currentId = rs.getInt("userId");
		boolean del = true;
		for (PlayerInTeam k : team.getPlayers()) {
		    if (currentId == k.userId.id) {
			del = false;
			if (!rs.getDate("joinDate").equals(new java.sql.Date(k.joinDate.getTime()))) { // może nie trzeba tego new, nie wiem
			    PreparedStatement prepStatement = dbInterface.createPreparedStatement("UPDATE PlayersInTeams SET joinDate=? WHERE teamId=? AND userId=?");
			    prepStatement.setInt(2, team.id.id);
			    prepStatement.setInt(3, currentId);
			    prepStatement.setDate(1, new java.sql.Date(k.joinDate.getTime()));
			    prepStatement.executeUpdate();
			}
		    }
		}
		if (del) {
		    Statement statement2 = dbInterface.createStatement();
		    statement2.executeUpdate(String.format("DELETE FROM PlayersInTeams WHERE teamId=%d AND userId=%d", team.id.id, currentId));
		}
	    }
	    for (PlayerInTeam k : team.getPlayers()) {
		rs.absolute(0); 
		boolean add = true;
		while (rs.next()) {
		    if (k.userId.id == rs.getInt("userId")) {
			add = false;
		    }
		}
		if (add) {
		    PreparedStatement prepStatement;
		    if (k.joinDate != null) {
			prepStatement = dbInterface.createPreparedStatement("INSERT INTO PlayersInTeams(teamId, userId, joinDate) VALUES (?, ?, ?)");
			prepStatement.setDate(3, new java.sql.Date(k.joinDate.getTime()));
		    }
		    else {
			prepStatement = dbInterface.createPreparedStatement("INSERT INTO PlayersInTeams(teamId, userId) VALUES (?, ?)");
		    }
		    prepStatement.setInt(1, k.teamId.id);
		    prepStatement.setInt(2, k.userId.id);
		    prepStatement.executeUpdate();
		}
	    }
	    PreparedStatement prepStatement = dbInterface.createPreparedStatement("UPDATE Teams SET name=?, capId=? WHERE teamId=?");
	    prepStatement.setInt(3, team.id.id);
	    prepStatement.setString(1, team.getName());
	    prepStatement.setInt(2, team.getCapitan().id);
	    rs.close();
	    return prepStatement.executeUpdate() > 0;
	}
	catch (Exception exc) {
	    throw new RuntimeException(exc);
	}
    }
}
