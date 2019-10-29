package pw.react.backend.reactbackend.controllers;

import pw.react.backend.reactbackend.user.User;
import pw.react.backend.reactbackend.repository.UserRepository;
import pw.react.backend.reactbackend.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/controller")
public class Controller {

    @Autowired
    UserRepository repository;
    @Autowired
    UserServices service;

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        List<User> result;
        result = service.findAll();
        return result;
    }

    @PostMapping(value = "/userCreation")
    public String create(@Valid @RequestBody User user) {
        if (service.validLogin(user)) {
            repository.save(new User(user.getLogin(),
                    user.getFirstName(), user.getLastName(),
                    user.getDateOfBirth(), user.active()));
            return "User was successfully created";
        }
        else {
            return "User with such a login already exists! Try another one.";
        }
    }

    @GetMapping("/findByLogin/{login}")
    public String findByLogin(@PathVariable String login) {
        User user = repository.findByLogin(login);
        if(user == null)
            return "The  user with such a login does not exist.";
        return user.getUserInfo();
    }
}