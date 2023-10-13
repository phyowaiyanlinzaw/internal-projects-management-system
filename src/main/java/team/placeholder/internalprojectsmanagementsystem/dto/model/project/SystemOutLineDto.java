package team.placeholder.internalprojectsmanagementsystem.dto.model.project;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemOutLineDto {
    private long id;
    private boolean analysis,sys_design,coding,testing,deploy,maintenance,document;
}
