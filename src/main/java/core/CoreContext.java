package core;

import core.models.Config;
import core.models.DbPrecondition;
import core.models.SshClient;
import core.models.web.Web;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@ComponentScan(basePackages = {"core", "tests"})
@Lazy
public class CoreContext {
    @Bean
    public Config config() {
        return new Config("config.ini");
    }

    @Bean
    public SshClient amazonSshClient() {
        return new SshClient(config().getPreference().node("amazonSsh"));
    }

    @Bean
    public DbPrecondition dbPrecondition() {
        return new DbPrecondition(amazonSshClient(), config().getPreference().node("dbPrecondition"));
    }

    @Bean
    public Web web() {
        ChromeDriverManager.getInstance().setup();
        com.codeborne.selenide.Configuration.browser = "chrome";
        return new Web(config().getPreference().node("Web").get("baseUrl", ""));
    }
}
