/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.aei.io.turnieje.model.repository;

import java.util.Set;
import pl.polsl.aei.io.turnieje.model.datamodel.Match;
import pl.polsl.aei.io.turnieje.model.datamodel.MatchId;
import pl.polsl.aei.io.turnieje.model.datamodel.Team;
import pl.polsl.aei.io.turnieje.model.datamodel.TeamId;
import pl.polsl.aei.io.turnieje.model.datamodel.Tournament;
import pl.polsl.aei.io.turnieje.model.datamodel.TournamentId;

/**
 * Repository interface for match.
 * 
 * @author Piotr Uhl
 * @version 1.0.1
 */
public interface IMatchRepository {
    public MatchId addMatch(Match match);
    public boolean delete(MatchId match);
    public boolean delete(Match match);
    public Set<Match> getAll();
    public Match getById(MatchId id);
    public Set<Match> getByTeam(Team team);
    public Set<Match> getByTeam(TeamId team);
    public Set<Match> getByTournament(TournamentId tournament);
    public Set<Match> getByTournament(Tournament tournament);
    public boolean update(Match match);
}
