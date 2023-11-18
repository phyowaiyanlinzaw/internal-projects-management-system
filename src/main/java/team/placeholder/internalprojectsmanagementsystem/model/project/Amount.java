package team.placeholder.internalprojectsmanagementsystem.model.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="amount")
@NoArgsConstructor
@Getter
@Setter
public class Amount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int basic_design;
    private int detail_design;
    private int coding;
    private int unit_testing;
    private int integrated_testing;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount other = (Amount) o;
        return id == other.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
