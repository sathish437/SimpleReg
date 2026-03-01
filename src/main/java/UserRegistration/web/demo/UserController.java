package UserRegistration.web.demo;

import UserRegistration.web.demo.UserEntity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apis/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> CreateUser(@RequestBody User user){
        User create= (User) userService.Create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

    @GetMapping("/getUser")
    public ResponseEntity<List<User>> GetAll(@RequestBody User user){
        return ResponseEntity.ok(userService.GetUser(user));
    }

    @GetMapping("/getOneUser/{id}")
    public ResponseEntity<Optional<User>> GetOneId(@PathVariable Long id){
        return ResponseEntity.ok(userService.GetOneUser(id));
    }
}
