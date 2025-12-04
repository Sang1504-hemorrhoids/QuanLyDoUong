package quanly.douong.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Product {
    private String productId;
    private String name;
    private Integer quantity;
    private Double costPrice;
    private boolean status;
    private String categoryId;
    
}
