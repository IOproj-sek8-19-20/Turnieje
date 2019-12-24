/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.aei.io.turnieje.model.repository;

import java.util.Set;
import pl.polsl.aei.io.turnieje.model.datamodel.Team;
import pl.polsl.aei.io.turnieje.model.datamodel.TeamId;
import pl.polsl.aei.io.turnieje.model.datamodel.Tournament;
import pl.polsl.aei.io.turnieje.model.datamodel.TournamentId;

/**
 * Realization of repository interface for teams.
 * 
 * @author Piotr Uhl
 * @version 0.1.0
 */
public class TeamRepository implements ITeamRepository {
    @Override
    public boolean add(Team team) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public boolean delete(Team team) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public boolean delete(TeamId team) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public Set<Team> getAll() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public Team getById(TeamId id) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public Set<Team> getByTournament(Tournament tournament) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public Set<Team> getByTournament(TournamentId tournament) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public boolean update(Team team) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
}
