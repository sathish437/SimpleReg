package UserRegistration.web.demo.UserEntity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false,unique = true)
    private String emailId;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String confirmPassword;
}
