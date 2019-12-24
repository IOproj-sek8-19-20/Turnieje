/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.aei.io.turnieje.model.datamodel;

import java.util.Date;

/**
 * Representation of single team in single tournament.
 * 
 * @author Piotr Uhl
 * @version 1.0.0
 */
public class TeamInTournament { //struct
    public TournamentId tourId;
    public TeamId teamId;
    public int groupNr;
    public Date joinDate;
    public int points;
    public boolean eliminated;
}
