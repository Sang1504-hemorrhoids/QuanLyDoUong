package quanly.douong.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UnitConversion {
    private String id;
    private String unitName;
    private Integer factor;
    private boolean isBaseunit;
}
