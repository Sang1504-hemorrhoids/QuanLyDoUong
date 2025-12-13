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
public class Promotion {
    private String promotionId;
    private String promoName;
    private Double discount;
    private Date startDate;
    private Date endDate;
    private boolean status;
}
