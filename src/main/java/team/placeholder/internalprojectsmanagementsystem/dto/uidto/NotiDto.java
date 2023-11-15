package team.placeholder.internalprojectsmanagementsystem.dto.uidto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;

@Getter
@Setter
@Data
@ToString
public class NotiDto {
    private long id;
    private String description;
    private long noti_time;
}