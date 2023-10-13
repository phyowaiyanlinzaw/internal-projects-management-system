package team.placeholder.internalprojectsmanagementsystem.dto.model.project;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AmountDto {
    private long id;
    private int basic_design;
    private int detail_design;
    private int coding;
    private int unit_testing;
    private int integrated_testing;
}
