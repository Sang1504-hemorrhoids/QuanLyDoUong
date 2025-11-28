package quanly.douong.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Customer {
    private String customerId;
    private String customerName;
    private Integer phoneNumber;
    private String address;
    private String email;
}
