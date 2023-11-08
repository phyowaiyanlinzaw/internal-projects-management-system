package team.placeholder.internalprojectsmanagementsystem.dto.uidto;


import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;

@Getter
@Setter
public class DepDto {

    private long id;
    private String name;

    private UserDto user;

    private int userCount;
    private int projectCount;

}
