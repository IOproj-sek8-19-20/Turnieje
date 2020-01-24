/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.aei.io.turnieje.model.repository;

import java.util.Set;
import pl.polsl.aei.io.turnieje.model.datamodel.Discipline;

/**
 * Repository interface for discipline.
 * 
 * @author Piotr Uhl
 */
public interface IDisciplineRepository {
    public Discipline add(String name);
    //public boolean delete(Discipline discipline);
    public Set<Discipline> getAll();
    public Discipline getByName(String name);
    public boolean rename(Discipline discipline, String newName);
    public boolean rename(String oldName, String newName);
}
