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
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public boolean delete(Tournament tournament) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public boolean delete(TournamentId tournament) {
	throw new UnsupportedOperationException("Not implenented yet.");
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
	throw new UnsupportedOperationException("Not implenented yet.");
    }
}
