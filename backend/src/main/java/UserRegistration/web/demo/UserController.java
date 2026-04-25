package UserRegistration.web.demo;

import UserRegistration.web.demo.DTO.ReqDTO.LoginReqDTO;
import UserRegistration.web.demo.DTO.ReqDTO.RegReqDTO;
import UserRegistration.web.demo.DTO.ResDTO.LoginResDTO;
import UserRegistration.web.demo.DTO.ResDTO.RegResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<LoginResDTO> UserLogin(@RequestBody LoginReqDTO loginReqDTO){
        return ResponseEntity.ok(userService.Login(loginReqDTO));
    }
    @PostMapping("/register")
    public ResponseEntity<RegResDTO> register(@RequestBody RegReqDTO regReqDTO){
        return ResponseEntity.ok(userService.register(regReqDTO));
    }
    @GetMapping("/welcome")
    public String Welcome(){
        return "Login successfully";
    }

}
