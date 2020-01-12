/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.aei.io.turnieje.model.repository;

import java.sql.SQLException;

/**
 * Singleton repositories objects factory.
 * 
 * @author Piotr Uhl
 * @version 0.1.0
 */
public class RepositoryProvider {
    //<editor-fold desc="Public">
    public static RepositoryProvider getInstance() {
	if (instance == null)
	    instance = new RepositoryProvider();
        return instance;
    }
    public IUserRepository getUserRepository() {
	return new UserRepository(dbInterface);
    }
    public ITeamRepository getTeamRepository() {
	return new TeamRepository(dbInterface);
    }
    public ITournamentRepository getTournamentRepository() {
	return new TournamentRepository(dbInterface);
    }
    public IMatchRepository getMatchRepository() {
	return new MatchRepository(dbInterface);
    }
    //</editor-fold>  
    //<editor-fold defaultstate="collapsed" desc="Private">
    private static RepositoryProvider instance = null;
    private DBInterface dbInterface;
    private RepositoryProvider() {
	dbInterface = new DBInterface();
	try {
	    dbInterface.open("jdbc:derby://localhost:1527/Tournaments", "root", "0000");
	}
	catch (ClassNotFoundException | SQLException exc) {
	    instance = null;
	}
    }
    @Override
    protected void finalize() throws Throwable {
	dbInterface.close();
	super.finalize();
    }
    //</editor-fold>

}
