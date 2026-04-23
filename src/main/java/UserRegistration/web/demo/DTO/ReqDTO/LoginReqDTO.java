package UserRegistration.web.demo.DTO.ReqDTO;

import UserRegistration.web.demo.UserEntity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginReqDTO {
    @NotBlank
    private String userName;
    @NotNull
    private Role role;
    @NotBlank
    private String password;
}
