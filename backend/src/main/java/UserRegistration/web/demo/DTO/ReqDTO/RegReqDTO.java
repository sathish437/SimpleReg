package UserRegistration.web.demo.DTO.ReqDTO;

import UserRegistration.web.demo.UserEntity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegReqDTO {
    @NotBlank
    private String userName;
    @NotBlank
    @Email
    private String emailId;
    @NotNull
    private Role role;
    @NotBlank
    private String password;
}
