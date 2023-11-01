package team.placeholder.internalprojectsmanagementsystem.dto.model.project;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AmountDto implements Serializable {
    private long id;
    private int basic_design;
    private int detail_design;
    private int coding;
    private int unit_testing;
    private int integrated_testing;

    @Override
    public String toString() {
        return "AmountDto [id=" + id + ", basic_design=" + basic_design + ", detail_design=" + detail_design
                + ", coding=" + coding + ", unit_testing=" + unit_testing + ", integrated_testing=" + integrated_testing
                + "]";
    }

}
