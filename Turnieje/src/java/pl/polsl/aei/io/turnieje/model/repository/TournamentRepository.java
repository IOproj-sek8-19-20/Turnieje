/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.aei.io.turnieje.model.repository;

import java.util.Set;
import pl.polsl.aei.io.turnieje.model.datamodel.Tournament;
import pl.polsl.aei.io.turnieje.model.datamodel.TournamentId;

/**
 * Realization of repository interface for tournaments.
 * 
 * @author Piotr Uhl
 * @version 0.1.0
 */
public class TournamentRepository implements ITournamentRepository {
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
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public boolean update(Tournament tournament) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
}
