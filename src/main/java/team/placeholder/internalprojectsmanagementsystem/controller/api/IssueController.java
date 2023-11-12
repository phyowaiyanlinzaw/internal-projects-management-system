package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.IsuDto;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.IssueStatus;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.impl.issue.IssueServiceImpl;

import java.util.*;

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
        log.info(dto.getStatus());

        dto.setStatus(String.valueOf(IssueStatus.PENDING));

        System.out.println("Status"+dto.getStatus());

        IssueDto issueDto = issueService.save(dto);
        return new ResponseEntity<>(issueDto, HttpStatus.OK);

    }



    @GetMapping("/list/byId/{id}")
    public ResponseEntity<IssueDto> getIssueById(@PathVariable long id) {
        IssueDto issue = issueService.getIssueById(id);

        if (issue != null) {
            return ResponseEntity.ok(issue);
        } else {
            return ResponseEntity.notFound().build();
        }
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

    @GetMapping("/list/ID/{id}/{status}")
    public ResponseEntity<Map<String, Object>> getProjectByIdAndStatus(@PathVariable long id, @PathVariable String status) {
        List<IssueDto> issueList = (List<IssueDto>) issueService.getIssueListsByIdAndStatus(id, status);

        if (issueList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Long currentIssueId = null;

        // Using Java streams for a more concise and functional approach
        Optional<IssueDto> matchingIssue = issueList.stream()
                .filter(issueDto -> issueDto != null && status.equalsIgnoreCase(issueDto.getStatus()))
                .findFirst();

        if (matchingIssue.isPresent()) {
            currentIssueId = matchingIssue.get().getId();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Map<String, Object> projectMap = new HashMap<>();
        projectMap.put("projectId", currentIssueId);

        return new ResponseEntity<>(projectMap, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteIssue(@RequestBody IssueDto issueDto) {
         issueService.deleteIssue(issueDto.getId());
        return ResponseEntity.ok("Issue deleted successfully");
    }

    @GetMapping("/list/pending")
    public ResponseEntity<List<IssueDto>> getIssueByPending() {

        List<IssueDto> issueDtos = issueService.getPendingIssueList();

        return ResponseEntity.ok(issueDtos);
    }



}
