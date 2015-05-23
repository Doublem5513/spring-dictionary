import data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;
import provider.IdProvider;
import repositories.UserRepository;

import javax.jws.soap.SOAPBinding;
import javax.websocket.server.PathParam;

/**
 * Created by mmatosevic on 21.5.2015.
 */

@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "repositories")
@EntityScan(basePackages = "data")
@ComponentScan(basePackages = {"config", "controller"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
