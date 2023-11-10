package team.placeholder.internalprojectsmanagementsystem.dto.uidto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@ToString
public class NotiDto {
    private String description;
    private long notiTime;
    private long userId;


}
