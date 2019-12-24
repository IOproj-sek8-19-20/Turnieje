/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.aei.io.turnieje.model.datamodel;

import java.util.Set;

/**
 * Representation of single team.
 * 
 * @author Piotr Uhl
 * @version 0.1.0
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
	this.id = new TeamId(0);
    }
    /**
     * Parameterized constructor, sets id to given one.
     * @param id - id of created object
     */
    public Team(TeamId id) {
	this.id = id;
    }
    /**
     * Parameterized constructor, sets id to given one.
     * @param id - id of created object
     */
    public Team(int id) {
	this.id = new TeamId(id);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
    public boolean addDiscipline(Discipline discipline) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean addPlayer(User user) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean addPlayer(PlayerInTeam user) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean addPlayer(UserId user) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public UserId getCapitan() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public Set<Discipline> getDisciplines() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public TeamId getId() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public String getName() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public Set<PlayerInTeam> getPlayers() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean removeDiscipline(Discipline discipline) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean removePlayer(UserId user) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean removePlayer(PlayerInTeam user) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean removePlayer(User user) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean replacePlayer(PlayerInTeam user) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean setCapitan(UserId user) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean setCapitan(User user) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean setName(String name) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    //</editor-fold>
}
