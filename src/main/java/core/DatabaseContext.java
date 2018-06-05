package core;

import core.models.Config;
import core.modules.database.DatabaseDriver;
import core.modules.database.Models.DbConnection;
import core.modules.database.Models.DbDump;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.prefs.Preferences;

@Configuration
@ComponentScan(basePackages = {"core.modules.database"})
@Lazy
public class DatabaseContext {

    @Bean
    public Config config() {
        return new Config("config.ini");
    }

    @Bean

    public Preferences generalConfig() {
        return config().getPreference().node("General");
    }

    @Bean
    public DbDump dbDump() {
        return new DbDump(config().getPreference().node("Db"));
    }

    @Bean
    public DriverManagerDataSource driverManager () {
        return new DbConnection(config().getPreference().node("Db"), dbDump()).driverManager();
    }

    @Bean
    public DatabaseDriver databaseDriver () {
        return new DatabaseDriver(driverManager());
    }
}
