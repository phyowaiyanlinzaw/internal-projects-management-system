package team.placeholder.internalprojectsmanagementsystem.controller.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        if(issueDto.getCorrective_action().equals(null) && issueDto.getPreventive_action().equals(null)){
            issueDto.setSolved(false);
        }else{
            issueDto.setSolved(true);
        }

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

    @GetMapping("/list/status/{status}")
    public ResponseEntity<List<IssueDto>> getFilteredDetails(@PathVariable(name = "status") String status) {
        List<IssueDto> filteredDetails = issueService.getIssuesByStatus(status);
        return ResponseEntity.ok(filteredDetails);
    }

    @GetMapping("list/solved/{solved}")
    public List<IssueDto> getIssuesBySolvedStatus(@PathVariable(value = "solved", required = false) Boolean solved) {
        if (solved == true) {
            return issueService.getIssuesBySolvedStatus(true);
        }else if(solved == false){
            return issueService.getIssuesBySolvedStatus(false);
        }else{
            return issueService.getAllIssues();
        }
    }

    @GetMapping("/pages")
    public Page<IssueDto> issues(Pageable pageable){
        return issueService.pages(pageable);
    }

}
