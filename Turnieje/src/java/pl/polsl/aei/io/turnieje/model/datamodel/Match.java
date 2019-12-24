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
 * @version 0.1.0
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
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean getFinished() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public MatchId getId() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public TeamId getTeamId(int nr) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public TournamentId getTourId() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public TeamId getWinner() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean setDate(Date val) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean setFinished(boolean val) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean setTeamId(int nr, TeamId val) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean setTourId(TournamentId val) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean setWinner(TeamId val) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    //</editor-fold>
}
