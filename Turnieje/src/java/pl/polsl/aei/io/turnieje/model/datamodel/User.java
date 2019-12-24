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
 * @version 0.1.0
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
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean checkPassword(String passHash) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean getActive() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public Set<Discipline> getDisciplines() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public String getEmail() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public String getFirstName() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public UserId getId() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public String getLastName() {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean removeDiscipline(Discipline discipline) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean setActive(boolean val) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean setEmail (String val) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean setFirstName(String val) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean setLastName(String val) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    public boolean setPassHash(String val) {
	throw new UnsupportedOperationException("Not implenented yet.");
    }
    //</editor-fold>
}
