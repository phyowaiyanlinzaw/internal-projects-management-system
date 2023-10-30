package team.placeholder.internalprojectsmanagementsystem.dto.model.project;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemOutLineDto {
    private long id;
    private boolean analysis;
    private boolean sys_design;
    private boolean coding;
    private boolean testing;
    private boolean deploy;
    private boolean maintenance;
    private boolean document;

    @Override
    public String toString() {
        return "SystemOutLineDto [id=" + id + ", analysis=" + analysis + ", sys_design=" + sys_design + ", coding="
                + coding + ", testing=" + testing + ", deploy=" + deploy + ", maintenance=" + maintenance
                + ", document=" + document + "]";
    }

}
