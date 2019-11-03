package pw.react.backend.reactbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import pw.react.backend.reactbackend.user.User;
import pw.react.backend.reactbackend.repository.UserRepository;
import pw.backend.lab.backlab.appException.ResourceNotFoundException;


import java.util.Optional;
import java.util.List;

@org.springframework.stereotype.Service
public class UserServices implements IUserServices{
    @Autowired
    private UserRepository repository;

    @Autowired
    public UserServices(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public Boolean validLogin(User user) {
        User sameLogin = null;
        sameLogin = repository.findByLogin(user.getLogin());
        if(sameLogin == null)
            return false;
        else
            return true;
    }

    @Override
    public User updateUser(User user) {
        Optional<User> existingUser = repository.findById(user.getId());
        if (existingUser.isPresent()) {
            return repository.save(user);
        }
        throw new ResourceNotFoundException(String.format("User with id [%s] not found.", user.getId()));
    }

}
