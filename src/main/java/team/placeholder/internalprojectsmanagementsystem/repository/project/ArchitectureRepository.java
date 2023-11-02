package team.placeholder.internalprojectsmanagementsystem.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;

import team.placeholder.internalprojectsmanagementsystem.model.project.Architecture;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;

import java.util.List;

public interface ArchitectureRepository extends JpaRepository<Architecture, Long> {
    List<Architecture> findAll();
}
