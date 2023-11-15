package team.placeholder.internalprojectsmanagementsystem.dto.uidto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter

public class KPIDto {
    private double review_kpi,detail_kpi,coding_kpi,unit_test_kpi,integrated_test_kpi;
}
