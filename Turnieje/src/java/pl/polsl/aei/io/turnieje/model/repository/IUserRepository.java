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
 * Repository interface for users.
 * 
 * @author Piotr Uhl
 */
public interface IUserRepository {
    public UserId add(User user);
    public boolean delete(User user);
    public boolean delete(UserId user);
    public Set<User> getAll();
    public User getByEmail(String email);
    public User getById(UserId id);
    public Set<User> getByTeam(Team id);
    public Set<User> getByTeam(TeamId id);
    public boolean update(User user);
}
