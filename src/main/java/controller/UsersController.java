package controller;

import data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import provider.IdProvider;
import repositories.UserRepository;

/**
 * Created by mmatosevic on 22.5.2015.
 */
@RestController
public class UsersController {
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

}
