import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import provider.IdProvider;

/**
 * Created by mmatosevic on 21.5.2015.
 */
@RestController
@EnableAutoConfiguration
@ComponentScan("config")
public class Status {

    @Autowired
    private IdProvider idProvider;

    @RequestMapping("/")
    public String root(){
        return "Status: OK";
    }

    @RequestMapping("/id")
    public String id(){
        return String.valueOf(idProvider.getId());
    }

    public static void main(String[] args) {
        SpringApplication.run(Status.class, args);
    }
}
