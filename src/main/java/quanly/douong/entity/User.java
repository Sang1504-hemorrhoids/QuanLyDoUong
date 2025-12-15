package quanly.douong.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    private String username;
    private String password;
    private String fullname;
    private String citizenNumber;
    private boolean role;
    private boolean status;
}
