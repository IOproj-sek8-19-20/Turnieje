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
 * Repository interface for teams.
 * 
 * @author Piotr Uhl
 * @version 1.0.1
 */
public interface ITeamRepository {
    public TeamId add(Team team);
    public boolean delete(Team team);
    public boolean delete(TeamId team);
    public Set<Team> getAll();
    public Team getById(TeamId id);
    public Team getByName(String name);
    public Set<Team> getByTournament(Tournament tournament);
    public Set<Team> getByTournament(TournamentId tournament);
    public boolean update(Team team);
}
