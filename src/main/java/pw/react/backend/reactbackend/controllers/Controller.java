package pw.react.backend.reactbackend.controllers;

import pw.react.backend.reactbackend.user.User;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(path = "")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(repository.findAll());
    }

    @PostMapping(path = "")
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        return ResponseEntity.ok().body(repository.save(user));
    }

    @GetMapping(path="login/{login}")
    public ResponseEntity<User> findByLogin(@PathVariable String login) {
        return ResponseEntity.ok().body(repository.findByLogin(login));
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<User> findById(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok().body(repository.findById(id).orElseGet(User::new));
    }

    @PutMapping(path="")
    public ResponseEntity<User> updateWholeUser(@RequestBody @Valid User updatedUser) {
        return ResponseEntity.ok().body(repository.save(updatedUser));
    }

    @DeleteMapping(path="/{id}")
    public void deleteUser(@PathVariable(value = "id") User user) {
        repository.delete(user);

    }

    @PatchMapping(path = "")
    public ResponseEntity<User> updateUser(@RequestBody @Valid User updatedUser) {
        return ResponseEntity.ok().body(service.updateUser(updatedUser));
    }
}