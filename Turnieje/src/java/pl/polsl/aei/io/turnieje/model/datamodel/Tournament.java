/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.aei.io.turnieje.model.datamodel;

import java.util.Date;
import java.util.Set;

/**
 * Representation of single tournament.
 * 
 * @author Piotr Uhl
 * @version 0.1.0
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
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Parameterless contructor, sets id to 0;
     */
    public Tournament() {
	this.id = new TournamentId(0);
    }
    /**
     * Parameterized constructor, sets id to given one.
     * @param id - id of created object
     */
    public Tournament(TournamentId id) {
	this.id = id;
    }
    /**
     * Parameterized constructor, sets id to given one.
     * @param id - id of created object
     */
    public Tournament(int id) {
	this.id = new TournamentId(id);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
    public boolean addMatch(MatchId match) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean addTeam(TeamInTournament team) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public UserId getAdmin() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public Discipline getDiscipline() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public Date getEndingDate() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean getFinished() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public TournamentId getId() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public Set<MatchId> getMatches() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public TournamentMode getMode() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public String getName() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public Date getStartingDate() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public Set<TeamInTournament> getTeams() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public int getTeamSize() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean removeMatch(Match match) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean removeMatch(MatchId match) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean removeTeam(Team team) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean removeTeam(TeamId team) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean removeteam(TeamInTournament team) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean replaceTeam(TeamInTournament team) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean setAdmin(User val) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean setAdmin(UserId val) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean setDiscipline(Discipline val) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean setEndingDate(Date val) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean setFinished(boolean val) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean setMode(TournamentMode val) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean setName(String val) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean setStartingDate(Date val) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean setTeamSize(int val) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    //</editor-fold>
}
