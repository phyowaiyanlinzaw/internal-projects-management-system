package team.placeholder.internalprojectsmanagementsystem.model.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="architecture")
@NoArgsConstructor
@Getter
@Setter
public class Architecture implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String tech_name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Architecture other = (Architecture) o;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    public void setTech_name(String tech_name) {
        if (tech_name == null) {
            throw new NullPointerException("tech_name cannot be null");
        }
        this.tech_name = tech_name;
    }

}
