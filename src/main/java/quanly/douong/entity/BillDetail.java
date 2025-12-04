package quanly.douong.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BillDetail {
    private Long billDetailId;
    private Integer quantity;
    private Double unitPrice;
    private Long billId;
    private String productId;
}
