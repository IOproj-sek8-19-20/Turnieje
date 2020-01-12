/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.aei.io.turnieje.model.datamodel;

import java.util.Set;

/**
 * Representation of single user.
 * 
 * @author Piotr Uhl
 * @version 0.1.1
 */
public class User {
    //<editor-fold defaultstate="collapsed" desc="Fields">
    public final UserId id;
    String email;
    String firstName;
    String lastName;
    String passHash;
    boolean active;
    Set<Discipline> disciplines;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Parameterless contructor, sets id to 0;
     */
    public User() {
	this.id = new UserId(0);
    }
    /**
     * Parameterized constructor, sets id to given one.
     * @param id - id of created object
     */
    public User(UserId id) {
	this.id = id;
    }
    /**
     * Parameterized constructor, sets id to given one.
     * @param id - id of created object
     */
    public User(int id) {
	this.id = new UserId(id);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
    public boolean addDiscipline(Discipline discipline) {
	if (discipline == null)
	    return false;
	return disciplines.add(discipline);
    }
    public boolean checkPassword(String passHash) {
	return this.passHash.equals(passHash);
    }
    public boolean getActive() {
	return this.active;
    }
    public Set<Discipline> getDisciplines() {
	return this.disciplines;
    }
    public String getEmail() {
	return this.email;
    }
    public String getFirstName() {
	return this.firstName;
    }
    public UserId getId() {
	return this.id;
    }
    public String getLastName() {
	return this.lastName;
    }
    public boolean removeDiscipline(Discipline discipline) {
	if (discipline == null) 
	    return false;
	return this.disciplines.remove(discipline);
    }
    public boolean setActive(boolean val) {
	this.active = val;
	return true;
    }
    public boolean setEmail (String val) {
	if (val == null)
	    return false;
	this.email = val;
	return true;
    }
    public boolean setFirstName(String val) {
	if (val == null)
	    return false;
	this.firstName = val;
	return true;
    }
    public boolean setLastName(String val) {
	if (val == null)
	    return false;
	this.lastName = val;
	return true;
    }
    public boolean setPassHash(String val) {
	if (val == null)
	    return false;
	this.passHash = val;
	return true;
    }
    //</editor-fold>
}
