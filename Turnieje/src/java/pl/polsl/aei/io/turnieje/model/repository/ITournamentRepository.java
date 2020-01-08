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
 * Repository interface for tournaments.
 * 
 * @author Piotr Uhl
 * @version 1.0.0
 */
public interface ITournamentRepository {
    public boolean add(Tournament tournament);
    public boolean delete(Tournament tournament);
    public boolean delete(TournamentId tournament);
    public Set<Tournament> getAll();
    public boolean update(Tournament tournament);
}