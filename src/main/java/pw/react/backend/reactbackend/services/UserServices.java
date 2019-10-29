package pw.react.backend.reactbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import pw.react.backend.reactbackend.user.User;
import pw.react.backend.reactbackend.repository.UserRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class UserServices {
    @Autowired
    UserRepository repository;

    public Boolean validLogin(User user) {
        User sameLogin = null;
        sameLogin = repository.findByLogin(user.getLogin());
        if(sameLogin == null)
            return false;
        else
            return true;
    }
    public List<User> findAll() {
        return repository.findAll();
    }

}
