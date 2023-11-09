package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
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
@Slf4j
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
        // Map the DTO to an Issue entity using ModelMapper
        Issue issue = modelMapper.map(dto, Issue.class);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByEmail(authentication.getName());

        User projectManager = userRepository.findById(user.getProjectManager().getId());

        log.info("new issue : {}", dto.getUser_pic());

        // Check and set the issue category and responsible type
        if (dto.getIssue_category() != null) {
            issue.setIssue_category(Category.valueOf(dto.getIssue_category()));
        }
        if (dto.getResponsible_type() != null) {
            issue.setResponsible_type(dto.getResponsible_type());
        }

        // Map the user and project references using ModelMapper
        Project project = projectRepository.getReferenceById(dto.getProject_id());

        issue.setUser_uploader(user);
        issue.setUser_pic(projectManager);
        issue.setProject(project);

        // Save the Issue entity
        IssueDto savedIssueDto = issueService.save(issue);

        if (savedIssueDto != null) {
            return ResponseEntity.ok(savedIssueDto);
        } else {
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

//    @GetMapping("/clients")
//    public ResponseEntity<List<ClientDto>> getClientsForIssues() {
//        List<IssueDto> issues = issueService.getAllIssues(); // Retrieve all issues
//        List<ClientDto> clients = new ArrayList<>();
//
//        for (IssueDto issue : issues) {
//            ProjectDto project = issue.getProjectDto();
//            if (project != null) {
//                clients.add(project.getClientDto());
//            }
//        }
//
//        return ResponseEntity.ok(clients);
//    }

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
