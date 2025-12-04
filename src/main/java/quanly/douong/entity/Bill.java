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
    private Long billId;
    private Double total;
    private Date startDate;
    private boolean status;
    private String username;
    private String promotionId;
    private Integer customerId;
}
