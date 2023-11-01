package team.placeholder.internalprojectsmanagementsystem.dto.model.project;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ArchitectureDto implements Serializable {
    private Long id;
    private String tech_name;
}
