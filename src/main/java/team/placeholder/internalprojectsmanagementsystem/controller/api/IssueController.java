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

    final IssueServiceImpl issueService;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapper modelmapper;

    @GetMapping("/list")
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

    @GetMapping("/list/status/{status}")
    public ResponseEntity<List<IssueDto>> getFilteredDetails(@PathVariable(name = "status") String status) {
        List<IssueDto> filteredDetails = issueService.getIssuesByStatus(status);
        return ResponseEntity.ok(filteredDetails);
    }

    @GetMapping("list/solved/{solved}")
    public List<IssueDto> getIssuesBySolvedStatus(@PathVariable(value = "solved", required = false) Boolean solved) {
        if (Boolean.TRUE.equals(solved)) {
            return issueService.getIssuesBySolvedStatus(true);
        } else if (Boolean.FALSE.equals(solved)) {
            return issueService.getIssuesBySolvedStatus(false);
        } else {
            return issueService.getAllIssues();
        }
    }


    @GetMapping("/list/category/{category}")
    public ResponseEntity<List<IssueDto>> getFilteredCategory(@PathVariable(name = "category") String category) {
        List<IssueDto> filteredDetails = issueService.getIssuesByCategory(category);
        return ResponseEntity.ok(filteredDetails);
    }

    @GetMapping("/list/pending/{id}")
    public ResponseEntity<List<IssueDto>> getIssueByPending(@PathVariable long id) {

        List<IssueDto> issueDtos = issueService.getPendingIssueList(id);

        for(var a : issueDtos) {
            log.info(a.getTitle());
            System.out.print("i have no idea +++++++" + a.getTitle());
        }

        return ResponseEntity.ok(issueDtos);
    }

    @PutMapping("/update/status/issuelist")
    public ResponseEntity<List<IssueDto>> updateIssueList(@RequestBody List<IssueDto> issueDtos) {
        List<IssueDto> updatedIssueList = issueService.updateStatusOfIssueList(issueDtos);
        return ResponseEntity.ok(updatedIssueList);
    }

    @GetMapping("/list/unsolved/{id}")
    public ResponseEntity<List<IssueDto>> getUnsolvedIssues(@PathVariable long id) {
        List<IssueDto> issueDtos = issueService.getUnsolvedIssues(id);
        return ResponseEntity.ok(issueDtos);
    }

    @GetMapping("/count/project-manager/{id}")
    public ResponseEntity<Long> countIssuesByProjectManagerId(@PathVariable long id) {
        long count = issueService.countIssuesByProjectManagerId(id);
        return ResponseEntity.ok(count);
    }

}
