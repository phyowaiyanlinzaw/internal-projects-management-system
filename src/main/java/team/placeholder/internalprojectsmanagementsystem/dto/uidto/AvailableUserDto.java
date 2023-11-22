package team.placeholder.internalprojectsmanagementsystem.dto.uidto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvailableUserDto {

    long id;
    UserDto user;
    boolean avaliable;

}
