package team.placeholder.internalprojectsmanagementsystem.model.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="system_outline")
@NoArgsConstructor
@Getter
@Setter
public class SystemOutLine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean analysis;
    private boolean sys_design;
    private boolean coding;
    private boolean testing;
    private boolean deploy;
    private boolean maintenance;
    private boolean document;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemOutLine other = (SystemOutLine) o;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "SystemOutLine{" +
                "id=" + id +
                ", analysis=" + analysis +
                ", sys_design=" + sys_design +
                ", coding=" + coding +
                ", testing=" + testing +
                ", deploy=" + deploy +
                ", maintenance=" + maintenance +
                ", document=" + document +
                ", project=" + project +
                '}';
    }
}
