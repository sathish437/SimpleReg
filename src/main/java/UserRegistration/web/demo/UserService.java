package UserRegistration.web.demo;

import UserRegistration.web.demo.DTO.ReqDTO.LoginReqDTO;
import UserRegistration.web.demo.DTO.ReqDTO.RegReqDTO;
import UserRegistration.web.demo.DTO.ResDTO.LoginResDTO;
import UserRegistration.web.demo.DTO.ResDTO.RegResDTO;
import UserRegistration.web.demo.DTOMapping.RegMapping;
import UserRegistration.web.demo.SecurityConfig.JwtUtil;
import UserRegistration.web.demo.UserEntity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    public LoginResDTO Login(LoginReqDTO loginReqDTO) {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    loginReqDTO.getUserName(),
                                    loginReqDTO.getPassword()
                            )
                    );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String token = jwtUtil.generateToken(userDetails);

            LoginResDTO loginResDTO = new LoginResDTO();
            loginResDTO.setUserName(userDetails.getUsername());
            loginResDTO.setToken(token);

            return loginResDTO;

        } catch (Exception e) {
            throw new RuntimeException("Invalid username or password");
        }
    }
    public RegResDTO register(RegReqDTO regReqDTO){
        try {
            if(regReqDTO.getEmailId() == null || regReqDTO.getEmailId().isEmpty()){
                throw new RuntimeException("Email is required");
            }
            if(userRepository.existsByEmailId(regReqDTO.getEmailId())){
                throw new RuntimeException("Email already exists");
            }
            UserEntity user = RegMapping.mapReqToUser(regReqDTO);
            user.setPassword(passwordEncoder.encode(regReqDTO.getPassword()));
            UserEntity savedUser = userRepository.save(user);

            return RegMapping.mapResToRegResDTO(savedUser);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Registration failed: " + e.getMessage(), e);
        }
    }
}
