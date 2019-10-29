package pw.react.backend.reactbackend.controllers;

import pw.react.backend.reactbackend.user.User;
import pw.react.backend.reactbackend.repository.UserRepository;
import pw.react.backend.reactbackend.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
        if (!service.validLogin(user)) {
            repository.save(new User(user.getLogin(),
                    user.getFirstName(), user.getLastName(),
                    user.getDateOfBirth(), user.active()));
            return "User was successfully created";
        }
        else {
            return "User with such a login already exists! Try another one.";
        }
    }

    @GetMapping("/findUserByLogin/{login}")
    public String findByLogin(@PathVariable String login) {
        User user = repository.findByLogin(login);
        if(user != null)
            return user.getUserInfo();
        return "The  user with such a login does not exist.";
    }

    @GetMapping("/findUserByID/{id}")
    public String findById(@PathVariable(value = "id") Long id) {
        Optional<User> opt = repository.findById(id);
        User user = null;
        if (opt.isPresent())
            user = opt.get();
        else return "There was a problem of finding the user";
        if (user != null) {
            return user.getUserInfo();
        }
        return "The user with the given ID does not exist.";
    }

    @PutMapping("/updateUser/{id}")
    public String updateUser(@PathVariable(value = "id") Long id, @Valid @RequestBody User user) {
        Optional<User> opt = repository.findById(id);
        User userUpTo;
        if (opt.isPresent())
            userUpTo = opt.get();
        else return "There was a problem setting updates123 to the user.";
        if (userUpTo != null) {
            userUpTo.setFields(user.getLogin(), user.getFirstName(), user.getLastName(), user.getDateOfBirth(), user.active());
            repository.save(userUpTo);
            return userUpTo.getUserInfo();
        }
        else return "There was a problem setting updates to the user.";
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        Optional<User> opt = repository.findById(id);
        User userDel;
        if (opt.isPresent())
            userDel = opt.get();
        else return "There was a problem deleting the user.";
        repository.delete(userDel);
        return "User was successfully deleted";
    }
}