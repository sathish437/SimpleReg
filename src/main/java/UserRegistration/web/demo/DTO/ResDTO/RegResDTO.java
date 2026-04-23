package UserRegistration.web.demo.DTO.ResDTO;

import UserRegistration.web.demo.UserEntity.Role;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegResDTO {
    private String userName;
    private String emailId;
    private Role role;
}
