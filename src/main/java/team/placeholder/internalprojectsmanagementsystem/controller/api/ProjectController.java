package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.NewProDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.PrjDto;
import team.placeholder.internalprojectsmanagementsystem.service.project.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @PostMapping("/save")
    public String saveProject (@RequestBody NewProDto newProject) {

        // logger.info("Received data: " + data);
        System.out.println("xxxxxxxxxxxxxxxxxxxxxx" + newProject);

        return "{\"message\": \""+newProject+"\"}";

    }

}
