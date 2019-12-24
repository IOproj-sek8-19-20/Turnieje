/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.aei.io.turnieje.model.repository;

import java.util.Set;
import pl.polsl.aei.io.turnieje.model.datamodel.Match;
import pl.polsl.aei.io.turnieje.model.datamodel.MatchId;
import pl.polsl.aei.io.turnieje.model.datamodel.Tournament;
import pl.polsl.aei.io.turnieje.model.datamodel.TournamentId;

/**
 * Realization of repository interface for matches.
 * 
 * @author Piotr Uhl
 * @version 0.1.0
 */
public class MatchRepository implements IMatchRepository {
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
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public Match getById(MatchId id) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public Set<Match> getByTournament(TournamentId tournament) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public Set<Match> getByTournament(Tournament tournament) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public boolean update(Match match) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
}
