package UserRegistration.web.demo.DTOMapping;

import UserRegistration.web.demo.DTO.ReqDTO.LoginReqDTO;
import UserRegistration.web.demo.DTO.ResDTO.LoginResDTO;
import UserRegistration.web.demo.UserEntity.UserEntity;

public class LoginMapping {
    public static UserEntity mapLoginToUser(LoginReqDTO loginReqDTO){
        UserEntity user=new UserEntity();
        user.setUserName(loginReqDTO.getUserName());
        user.setPassword(loginReqDTO.getPassword());
        return user;
    }

    public static LoginResDTO mapResToLoginResDTO(UserEntity user, String token){
        LoginResDTO loginResDTO=new LoginResDTO();

        loginResDTO.setUserName(user.getUserName());
        loginResDTO.setToken(token);

        return loginResDTO;
    }

}
