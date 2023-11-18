package team.placeholder.internalprojectsmanagementsystem.dto.model.project;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
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
    @Override
    public int hashCode() {
        return Objects.hash(id, analysis, sys_design, coding, testing, deploy, maintenance, document);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemOutLineDto that = (SystemOutLineDto) o;
        return id == that.id &&
                analysis == that.analysis &&
                sys_design == that.sys_design &&
                coding == that.coding &&
                testing == that.testing &&
                deploy == that.deploy &&
                maintenance == that.maintenance &&
                document == that.document;
    }


}
