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
    private String billDetailId;
    private Integer quantity;
    private Float unitPrice;
    private String billId;
    private String productId;
    private String id;
}
