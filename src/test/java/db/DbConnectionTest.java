package db;

import core.models.Config;
import core.modules.database.Models.DbConnection;
import core.modules.database.Models.DbDump;
import junit.framework.*;

public class DbConnectionTest extends TestCase {

    public void testDriverManager() {
        Config config = new Config("config.ini");

        DbConnection dbConnection = new DbConnection(config.getPreference().node("Db"), new DbDump(config.getPreference().node("Db")));
        dbConnection.driverManager();

    }

}
