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
@RestController
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "repositories")
@EntityScan(basePackages = "data")
@ComponentScan("config")
public class Application {

    @Autowired
    private IdProvider idProvider;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String root(){
        return "Status: OK";
    }

    @RequestMapping("/id")
    public String id(){
        return String.valueOf(idProvider.getId());
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public String users(){
        Iterable<User> users = userRepository.findAll();
        StringBuilder response = new StringBuilder();
        response.append("Users: ");
        for(User user : users){
            response.append(user);
        }
        return response.toString();
    }

    @RequestMapping(value = "/users/{username}", method = RequestMethod.POST)
    public String insert(@PathVariable("username") String username, @RequestParam("pass") String password, @RequestParam String email){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password != null ? password : "sa");
        user.setEmail(email);
        User save = userRepository.save(user);
        return save.toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
