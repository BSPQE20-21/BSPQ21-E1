package es.deusto.bspq21e1.server.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

public class DBManager {
    
    private PersistenceManagerFactory pmf;

    public DBManager() {
        pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
    }

    // Methods
}
