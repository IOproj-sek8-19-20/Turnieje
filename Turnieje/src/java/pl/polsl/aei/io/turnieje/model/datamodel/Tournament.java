/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.aei.io.turnieje.model.datamodel;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Representation of single tournament.
 * 
 * @author Piotr Uhl
 * @version 0.1.1
 */
public class Tournament {
    //<editor-fold defaultstate="collapsed" desc="Fields">
    public final TournamentId id;
    UserId adminId;
    Discipline discipline;
    Date endingDate;
    boolean finished;
    Set<MatchId> matches;
    String name;
    Date startingDate;
    Set<TeamInTournament> teams;
    int teamSize;
    TournamentMode mode;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Parameterless contructor, sets id to 0;
     */
    public Tournament() {
	this(0);
    }
    /**
     * Parameterized constructor, sets id to given one.
     * @param id - id of created object
     */
    public Tournament(TournamentId id) {
	this(id.id);
    }
    /**
     * Parameterized constructor, sets id to given one.
     * @param id - id of created object
     */
    public Tournament(int id) {
	this.id = new TournamentId(id);
	matches = new HashSet<>();
	teams = new HashSet<>();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
    public boolean addMatch(MatchId match) {
	if (match == null)
	    return false;
	return this.matches.add(match);
    }
    public boolean addTeam(TeamInTournament team) {
	if (team == null)
	    return false;
	for (TeamInTournament k : this.teams) {
	    if (k.teamId == team.teamId)
		return false;
	}
	this.teams.add(team);
	return true;
    }
    public UserId getAdmin() {
	return this.adminId;
    }
    public Discipline getDiscipline() {
	return this.discipline;
    }
    public Date getEndingDate() {
	return this.endingDate;
    }
    public boolean getFinished() {
	return this.finished;
    }
    public TournamentId getId() {
	return this.id;
    }
    public Set<MatchId> getMatches() {
	return this.matches;
    }
    public TournamentMode getMode() {
	return this.mode;
    }
    public String getName() {
	return this.name;
    }
    public Date getStartingDate() {
	return this.startingDate;
    }
    public Set<TeamInTournament> getTeams() {
	return this.teams;
    }
    public int getTeamSize() {
	return this.teamSize;
    }
    public boolean removeMatch(Match match) {
	if (match == null)
	    return false;
	return this.matches.remove(match.id);
    }
    public boolean removeMatch(MatchId match) {
	if (match == null)
	    return false;
	return this.matches.remove(match);
    }
    public boolean removeTeam(Team team) {
	if (team == null)
	    return false;
	return this.teams.removeIf(k -> k.teamId == team.id);
    }
    public boolean removeTeam(TeamId team) {
	if (team == null)
	    return false;
	return this.teams.removeIf(k -> k.teamId == team);
    }
    public boolean removeTeam(TeamInTournament team) {
	if (team == null)
	    return false;
	if (team.tourId != this.id)
	    return false;
	return this.teams.removeIf(k -> k.teamId == team.teamId);
    }
    public boolean updateTeam(TeamInTournament team) {
	if (team == null)
	    return false;
	if (team.tourId != this.id)
	    return false;
	if (this.teams.removeIf(k -> k.teamId == team.teamId) == false)
	    return false;
	return this.teams.add(team);
    }
    public boolean setAdmin(User val) {
	if (val == null)
	    return false;
	this.adminId = val.id;
	return true;
    }
    public boolean setAdmin(UserId val) {
	if (val == null)
	    return false;
	this.adminId = val;
	return true;
    }
    public boolean setDiscipline(Discipline val) {
	if (val == null)
	    return false;
	this.discipline = val;
	return true;
    }
    public boolean setEndingDate(Date val) {
	if (val == null)
	    return false;
	this.endingDate = val;
	return true;
    }
    public boolean setFinished(boolean val) {
	this.finished = val;
	return true;
    }
    public boolean setMode(TournamentMode val) {
	if (val == null)
	    return false;
	this.mode = val;
	return true;
    }
    public boolean setName(String val) {
	if (val == null)
	    return false;
	this.name = val;
	return true;
    }
    public boolean setStartingDate(Date val) {
	if (val == null)
	    return false;
	this.startingDate = val;
	return true;
    }
    public boolean setTeamSize(int val) {
	this.teamSize = val;
	return true;
    }
    //</editor-fold>
}
