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
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.impl.issue.IssueServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/issue/")
public class IssueController {

    private final IssueServiceImpl issueService;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @GetMapping("list")
    public ResponseEntity<List<IsuDto>> getAllIssues() {
        List<IsuDto> issueDtos = issueService.getAllIssues();
        return new ResponseEntity<>(issueDtos, HttpStatus.OK);
    }


//    @PostMapping("/save")
//    public ResponseEntity<Issue> saveIssue(@RequestBody IsuDto dto) {
//        // Convert pic_id, pm_id, and uploader_id to User entities
//        User pic = userRepository.findById(dto.getPic_id()).orElse(null);
//        User pm = userRepository.findById(dto.getResponsible_party()).orElse(null);
//        User uploader = userRepository.findById(dto.getUploader_id()).orElse(null);
//
//        if (pic == null || pm == null || uploader == null) {
//            return ResponseEntity.badRequest().body(null);
//        }
//        Issue issue = modelMapper.map(dto, Issue.class);
//        issue.setUser_pic(pic);
//        issue.setUser_uploader(uploader);
//        issue.setResponsible_party(pm.getId());
//        Issue savedIssue = issueService.save(dto);
//
//        if (savedIssue != null) {
//            return ResponseEntity.ok(savedIssue);
//        } else {
//            return ResponseEntity.badRequest().body(null);
//        }
//    }


    @PostMapping("/save")
    public ResponseEntity<Issue> saveIssue(@RequestBody IsuDto dto) {
        Issue savedIssue = issueService.save(dto);

        if (savedIssue != null) {
            return ResponseEntity.ok(savedIssue);
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
//        List<IsuDto> issues = issueService.getAllIssues(); // Retrieve all issues
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
