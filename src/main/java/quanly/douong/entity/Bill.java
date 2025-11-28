package quanly.douong.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Bill {
    private String billId;
    private Float total;
    private Date startDate;
    private boolean status;
    private String userId;
    private String promotionId;
    private String customerId;
}
