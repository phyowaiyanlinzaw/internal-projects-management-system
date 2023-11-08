package team.placeholder.internalprojectsmanagementsystem.dto.uidto;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.AmountDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ArchitectureDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.SystemOutLineDto;

@Getter
@Setter
public class NewProDto {

    private long id;
    private String name;
    private long start_date;
    private long end_date;
    private long userId;
    private long clientId;
    private String background;
    private String objective;
    private AmountDto amount;
    private SystemOutLineDto systemOutLine;
    private int[] architecutre;
    private int[] deliverable;

    @Override
    public String toString() {
        return "NewProDto [id=" + id + ", name=" + name + ", start_date=" + start_date + ", end_date=" + end_date
                + ", userId=" + userId + ", clientId=" + clientId + ", background=" + background + ", objective="
                + objective + ", amount=" + amount + ", systemOutLine=" + systemOutLine + ", architecutre="
                + Arrays.toString(architecutre) + ", deliverable=" + Arrays.toString(deliverable) + "]";
    }

}
