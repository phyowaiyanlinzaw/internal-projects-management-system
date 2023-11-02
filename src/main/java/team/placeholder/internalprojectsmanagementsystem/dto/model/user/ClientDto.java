package team.placeholder.internalprojectsmanagementsystem.dto.model.user;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClientDto implements Serializable {
    private long id;
    private String name,email,phone;
}
