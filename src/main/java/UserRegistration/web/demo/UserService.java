package UserRegistration.web.demo;

import UserRegistration.web.demo.UserEntity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User Create(User user){
        return userRepository.save(user);
    }

    public List<User> GetUser(User user){
        return userRepository.findAll();
    }

    public Optional<User> GetOneUser(Long id){
        return userRepository.findById(id);
    }

}
