import core.CoreContext;
import core.models.Config;
import core.models.DbPrecondition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RestClientApplication {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(CoreContext.class);


        Config config = (Config) context.getBean("config");

        DbPrecondition dbPrecondition = (DbPrecondition) context.getBean("dbPrecondition");

        dbPrecondition.prepareDb();

//
//        System.out.println(config.getPreference().node("General").get("instanceId", ""));
//
//        ApplicationContext context =
//                new AnnotationConfigApplicationContext(core.CoreContext.class);
//
//
//        System.out.println(configFromContext.getPreference().node("General").get("instanceId", ""));

//        SshClient sshClient = (SshClient) context.getBean("amazonSshClient");
//
//        sshClient.transferFile("","");

    }
}
