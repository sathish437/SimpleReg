package UserRegistration.web.demo.DTO.ReqDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginReqDTO {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
