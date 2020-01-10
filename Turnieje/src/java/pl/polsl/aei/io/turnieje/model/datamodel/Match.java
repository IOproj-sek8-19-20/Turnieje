/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.aei.io.turnieje.model.datamodel;

import java.util.Date;

/**
 * Representation of single match.
 * 
 * @author Piotr Uhl
 * @version 0.1.1
 */
public class Match {
    //<editor-fold defaultstate="collapsed" desc="Fields">
    public final MatchId id;
    Date date;
    boolean finished;
    TeamId team1Id;
    TeamId team2Id;
    TournamentId tourId;
    TeamId winner;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Parameterless contructor, sets id to 0;
     */
    public Match() {
	this.id = new MatchId(0);
    }
    /**
     * Parameterized constructor, sets id to given one.
     * @param id - id of created object
     */
    public Match(MatchId id) {
	this.id = id;
    }
    /**
     * Parameterized constructor, sets id to given one.
     * @param id - id of created object
     */
    public Match(int id) {
	this.id = new MatchId(id);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
    public Date getDate() {
	return this.date;
    }
    public boolean getFinished() {
	return this.finished;
    }
    public MatchId getId() {
	return this.id;
    }
    public TeamId getTeamId(int nr) {
	switch (nr) {
	    case 1:
		return this.team1Id;
	    case 2:
		return this.team2Id;
	    default:
		return null;
	}
    }
    public TournamentId getTourId() {
	return this.tourId;
    }
    public TeamId getWinner() {
	return this.winner;
    }
    public boolean setDate(Date val) {
	this.date = val;
	return true;
    }
    public boolean setFinished(boolean val) {
	this.finished = val;
	return true;
    }
    public boolean setTeamId(int nr, TeamId val) {
	switch (nr) {
	case 1:
	    this.team1Id = val;
	    break;
	case 2:
	    this.team2Id = val;
	    break;
	default:
	    return false;
	}
	return true;
    }
    public boolean setTourId(TournamentId val) {
	this.tourId = val;
	return true;
    }
    public boolean setWinner(TeamId val) {
	this.winner = val;
	return true;
    }
    //</editor-fold>
}
