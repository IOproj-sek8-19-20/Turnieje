/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.aei.io.turnieje.model.datamodel;

import java.util.Date;

/**
 * Representation of single user in single team.
 * 
 * @author Piotr Uhl
 * @version 1.0.0
 */
public class PlayerInTeam { //struct
    public TeamId teamId;
    public UserId userId;
    public Date joinDate;
}
