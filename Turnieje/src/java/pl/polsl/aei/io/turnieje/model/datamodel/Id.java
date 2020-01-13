/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.aei.io.turnieje.model.datamodel;

/**
 * Id datatype pattern
 * 
 * @author Piotr Uhl
 * @version 1.0.0
 */
public abstract class Id {
    public final int id;
    public Id(int id) {
	this.id = id;
    }
    public Id(String id) throws NumberFormatException {
	this(Integer.parseInt(id));
    }
}