package UserRegistration.web.demo.DTOMapping;

import UserRegistration.web.demo.DTO.ReqDTO.RegReqDTO;
import UserRegistration.web.demo.DTO.ResDTO.RegResDTO;
import UserRegistration.web.demo.UserEntity.UserEntity;

public class RegMapping {
    public static UserEntity mapReqToUser(RegReqDTO regReqDTO){
        UserEntity user=new UserEntity();
        user.setUserName(regReqDTO.getUserName());
        user.setEmailId(regReqDTO.getEmailId());
        user.setRole(regReqDTO.getRole());
        user.setPassword(regReqDTO.getPassword());

        return user;
    }
    public static RegResDTO mapResToRegResDTO(UserEntity user){
        RegResDTO regResDTO=new RegResDTO();
        regResDTO.setUserName(user.getUserName());
        regResDTO.setEmailId(user.getEmailId());
        regResDTO.setRole(user.getRole());
        return regResDTO;
    }
}
