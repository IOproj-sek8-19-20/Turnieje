/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.aei.io.turnieje.model.datamodel;

/**
 * Representation of single discipline
 * 
 * @author Piotr Uhl
 */
public class Discipline {
    public final int id;
    private String name;
    public Discipline(int id) {
	this.id = id;
    }
    public Discipline(String id) throws NumberFormatException {
	this(Integer.parseInt(id));
    }
    public Discipline(int id, String name) {
	this.id = id;
	this.name = name;
    }
    public Discipline(String id, String name) throws NumberFormatException {
	this(Integer.parseInt(id), name);
    }
    public int getId() {
	return id;
    }
    public String getName() {
	return name;
    }
    public Boolean setName(String name) {
	this.name = name;
	return true;
    }
}
