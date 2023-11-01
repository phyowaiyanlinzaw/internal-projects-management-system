package team.placeholder.internalprojectsmanagementsystem.dto.uidto;

import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.AmountDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ArchitectureDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.SystemOutLineDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.model.project.*;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.DevelopmentPhase;
import team.placeholder.internalprojectsmanagementsystem.model.user.Client;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class PrjDto {
    
    private long id;
    private String name;
    private long start_date;
    private long end_date;
    private UserDto user;
    private String background;
    private int currentPhase;
    private DepartmentDto department;
    private String objective;
    private int inReview;
    private int exReview;
    private ClientDto client;
    private int totalTask;
    private int doneTask;
    private AmountDto amount;
    private SystemOutLineDto systemOutLine;
    private List<ArchitectureDto> architecutre;

   

}
