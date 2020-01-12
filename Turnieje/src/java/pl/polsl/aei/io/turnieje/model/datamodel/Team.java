/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.aei.io.turnieje.model.datamodel;

import java.util.HashSet;
import java.util.Set;

/**
 * Representation of single team.
 * 
 * @author Piotr Uhl
 * @version 0.1.1
 */
public class Team {
    //<editor-fold defaultstate="collapsed" desc="Fields">
    public final TeamId id;
    UserId capId;
    String name;
    Set<PlayerInTeam> players;
    Set<Discipline> disciplines;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Parameterless contructor, sets id to 0;
     */
    public Team() {
	this(0);
    }
    /**
     * Parameterized constructor, sets id to given one.
     * @param id - id of created object
     */
    public Team(TeamId id) {
	this(id.id);
    }
    /**
     * Parameterized constructor, sets id to given one.
     * @param id - id of created object
     */
    public Team(int id) {
	this.id = new TeamId(id);
	players = new HashSet<>();
	disciplines = new HashSet<>();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
    public boolean addDiscipline(Discipline discipline) {
	if (discipline == null)
	    return false;
	this.disciplines.add(discipline);
	return true;
    }
    public boolean addPlayer(User user) {
	if (user == null)
	    return false;
	PlayerInTeam playerInTeam = new PlayerInTeam();
	playerInTeam.teamId = this.id;
	playerInTeam.userId = user.id;
	this.players.add(playerInTeam);
	return true;
    }
    public boolean addPlayer(PlayerInTeam user) {
	if (user == null)
	    return false;
	for (PlayerInTeam k : this.players) {
	    if (k.userId == user.userId)
		return false;
	}
	this.players.add(user);
	return true;
    }
    public boolean addPlayer(UserId user) {
	if (user == null)
	    return false;
	PlayerInTeam playerInTeam = new PlayerInTeam();
	playerInTeam.teamId = this.id;
	playerInTeam.userId = user;
	this.players.add(playerInTeam);
	return true;
    }
    public UserId getCapitan() {
	return this.capId;
    }
    public Set<Discipline> getDisciplines() {
	return this.disciplines;
    }
    public TeamId getId() {
	return this.id;
    }
    public String getName() {
	return this.name;
    }
    public Set<PlayerInTeam> getPlayers() {
	return this.players;
    }
    public boolean removeDiscipline(Discipline discipline) {
	if (discipline == null)
	    return false;
	return this.disciplines.remove(discipline);
    }
    public boolean removePlayer(UserId user) {
	if (user == null)
	    return false;
	return this.players.removeIf(k -> k.userId == user);
    }
    public boolean removePlayer(PlayerInTeam user) {
	if (user == null)
	    return false;
	if (user.teamId != this.id)
	    return false;
	return this.players.removeIf(k -> k.userId == user.userId);
    }
    public boolean removePlayer(User user) {
	if (user == null)
	    return false;
	return this.players.removeIf(k -> k.userId == user.id);
    }
    public boolean updatePlayer(PlayerInTeam user) {
	if (user == null)
	    return false;
	if (user.teamId != this.id)
	    return false;
	if (this.players.removeIf(k -> k.userId == user.userId) == false)
	    return false;
	return this.players.add(user);
    }
    public boolean setCapitan(UserId user) {
	if (user == null)
	    return false;
	this.capId = user;
	return true;
    }
    public boolean setCapitan(User user) {
	if (user == null)
	    return false;
	this.capId = user.id;
	return true;
    }
    public boolean setName(String name) {
	if (name == null)
	    return false;
	this.name = name;
	return true;
    }
    //</editor-fold>
}
