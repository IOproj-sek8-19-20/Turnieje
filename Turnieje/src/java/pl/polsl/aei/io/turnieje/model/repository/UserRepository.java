/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.aei.io.turnieje.model.repository;

import java.util.Set;
import pl.polsl.aei.io.turnieje.model.datamodel.Team;
import pl.polsl.aei.io.turnieje.model.datamodel.TeamId;
import pl.polsl.aei.io.turnieje.model.datamodel.User;
import pl.polsl.aei.io.turnieje.model.datamodel.UserId;

/**
 * Realization of repository interface for users.
 * 
 * @author Piotr Uhl
 * @version 0.1.0
 */
public class UserRepository implements IUserRepository {
    @Override
    public boolean add(User user) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public boolean delete(User user) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public boolean delete(UserId user) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public Set<User> getAll() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public User getByEmail(String email) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public User getById(UserId id) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public Set<User> getByTeam(Team id) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public Set<User> getByTeam(TeamId id) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    @Override
    public boolean update(User user) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
}
