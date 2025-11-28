package quanly.douong.util;

import quanly.douong.entity.User;

public class XAuth {
    public static User user = User.builder()
            .username("user")
            .password("123")
            .fullName("Tran Nguyen A")
            .role(false)
            .status(true)
            .build();
}
