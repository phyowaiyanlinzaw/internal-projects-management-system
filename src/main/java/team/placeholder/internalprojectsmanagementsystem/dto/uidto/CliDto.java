package team.placeholder.internalprojectsmanagementsystem.dto.uidto;


import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;

@Getter
@Setter
public class CliDto {
    private long id;
    private String name;
    private String email;
    private String phone;


}
