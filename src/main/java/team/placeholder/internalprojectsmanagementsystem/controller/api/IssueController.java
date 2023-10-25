//package team.placeholder.internalprojectsmanagementsystem.controller.api;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
//import team.placeholder.internalprojectsmanagementsystem.service.impl.issue.IssueServiceImpl;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/issue/")
//public class IssueController {
//
//    private final IssueServiceImpl issueService;
//
//    @GetMapping("lists")
//    public ResponseEntity<List<IssueDto>> getAllIssues() {
//        List<IssueDto> issueDtos = issueService.getAllIssues();
//        return new ResponseEntity<>(issueDtos, HttpStatus.OK);
//    }
//
//    @PostMapping("save")
//    public ResponseEntity<String> save(IssueDto issueDto) {
//        IssueDto savedIssue = issueService.save(issueDto);
//        if (savedIssue != null) {
//            return ResponseEntity.ok("Issue saved successfully");
//        } else {
//            return ResponseEntity.badRequest().body("Failed to save issue");
//        }
//    }
//
//    @GetMapping("/lists/{id}")
//    public ResponseEntity<IssueDto> getIssueById(long id) {
//        IssueDto issue = issueService.getIssueById(id);
//
//        if (issue != null) {
//            return ResponseEntity.ok(issue);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @GetMapping("/lists/{name}")
//    public ResponseEntity<IssueDto> getIssueByName(String name) {
//        IssueDto issue = issueService.getIssueByName(name);
//
//        if (issue != null) {
//            return ResponseEntity.ok(issue);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<String> updateIssue(@PathVariable long id, @RequestBody IssueDto issueDto) {
//        IssueDto updatedIssue = issueService.updateIssue(issueDto);
//
//        if (updatedIssue != null) {
//            return ResponseEntity.ok("Issue updated successfully");
//        } else {
//            return ResponseEntity.badRequest().body("Failed to update issue");
//        }
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteIssue(@RequestBody IssueDto issueDto) {
//         issueService.deleteIssue(issueDto.getId());
//        return ResponseEntity.ok("Issue deleted successfully");
//    }
//
//
//
//
//
//
//}
