package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.IsuDto;
import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.Category;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.ResponsibleType;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.impl.issue.IssueServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/issue/")
public class IssueController {

    private final IssueServiceImpl issueService;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @GetMapping("list")
    public ResponseEntity<List<IssueDto>> getAllIssues() {
        List<IssueDto> issueDtos = issueService.getAllIssues();
        return new ResponseEntity<>(issueDtos, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<IssueDto> saveIssue(@RequestBody IsuDto dto) {
        Issue issue = modelMapper.map(dto, Issue.class);

        issue.setIssue_category(Category.valueOf(dto.getIssue_category()));
        issue.setResponsible_type(ResponsibleType.valueOf(dto.getResponsible_type()));
        issue.setUser_uploader(modelMapper.map(userRepository.getReferenceById(dto.getUser_uploader()), User.class));
        issue.setUser_pic(modelMapper.map(userRepository.getReferenceById(dto.getUser_pic()), User.class));
        issue.setProject(modelMapper.map(projectRepository.getReferenceById(dto.getProject_id()), Project.class));

        IssueDto savedIssueDto = issueService.save(issue);

        if (savedIssueDto != null) {
            // If the save operation was successful, return the saved IssueDto
            return ResponseEntity.ok(savedIssueDto);
        } else {
            // Handle the case when the issue could not be saved
            return ResponseEntity.badRequest().body(null);
        }
    }




    @GetMapping("/lists/byId/{id}")
    public ResponseEntity<IssueDto> getIssueById(@PathVariable long id) {
        IssueDto issue = issueService.getIssueById(id);

        if (issue != null) {
            return ResponseEntity.ok(issue);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientDto>> getClientsForIssues() {
        List<IssueDto> issues = issueService.getAllIssues(); // Retrieve all issues
        List<ClientDto> clients = new ArrayList<>();

        for (IssueDto issue : issues) {
            ProjectDto project = issue.getProjectDto();
            if (project != null) {
                clients.add(project.getClientDto());
            }
        }

        return ResponseEntity.ok(clients);
    }

    @GetMapping("/lists/byTitle/{title}")
    public ResponseEntity<IssueDto> getIssueByTitle(@PathVariable String title) {
        IssueDto issue = issueService.getIssueByTitle(title);

        if (issue != null) {
            return ResponseEntity.ok(issue);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<IssueDto> updateIssue(@PathVariable long id, @RequestBody IssueDto issueDto) {
        issueDto.setId(id);
        IssueDto updatedIssue = issueService.updateIssue(issueDto);
        if (updatedIssue != null) {
            System.out.println("Issue updated successfully");
            return ResponseEntity.ok(updatedIssue);
        } else {
            System.out.println("Failed to update issue");
            return ResponseEntity.badRequest().body(null);
        }
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteIssue(@RequestBody IssueDto issueDto) {
         issueService.deleteIssue(issueDto.getId());
        return ResponseEntity.ok("Issue deleted successfully");
    }





}
