package com.timeks.issue.controller;

import com.timeks.base.model.BaseResponse;
import com.timeks.issue.model.dto.IssueDto;
import com.timeks.issue.model.dto.IssueStatusUpdateDto;
import com.timeks.issue.model.dto.IssueUpdateDto;
import com.timeks.issue.service.IssueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(IssueController.BASE_URI)
@CrossOrigin(origins = "http://localhost:3000")
public class IssueController {
    private final IssueService issueService;

    public static final String BASE_URI = "/api/v1/issue";

    @GetMapping
    public ResponseEntity<BaseResponse> getAll() {
        var issueDtos = issueService.findAll()
                .stream()
                .map(IssueDto::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(BaseResponse.of(issueDtos));
    }

    @GetMapping("/{projectId}/project")
    public ResponseEntity<BaseResponse> getByProjectId(@PathVariable("projectId") Long projectId) {
        var issues = issueService.findAllByProjectId(projectId)
                .stream()
                .map(IssueDto::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(BaseResponse.of(issues));
    }

    @PutMapping("/close/{issueId}")
    public ResponseEntity<BaseResponse> closeIssue(@PathVariable Long issueId) {
        final var issue = issueService.updateClose(issueId, true);
        return ResponseEntity.ok(BaseResponse.of("Issue closed", IssueDto.from(issue)));
    }

    @PutMapping("/reopen/{issueId}")
    public ResponseEntity<BaseResponse> reopenIssue(@PathVariable Long issueId) {
        final var issue = issueService.updateClose(issueId, false);
        return ResponseEntity.ok(BaseResponse.of("Issue reopened", IssueDto.from(issue)));
    }

    @PutMapping("/update-status")
    public ResponseEntity<BaseResponse> updateIssueStatus(@RequestBody @Valid IssueStatusUpdateDto dto) {
        final var issue = issueService.updateStatus(dto);
        return ResponseEntity.ok(BaseResponse.of("Issue Status updated", IssueDto.from(issue)));
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody @Valid IssueUpdateDto dto) {
        final var issue = issueService.update(dto);
        return ResponseEntity.ok(BaseResponse.of("Updated", IssueDto.from(issue)));
    }

    @PostMapping
    public ResponseEntity<BaseResponse> save(@RequestBody @Valid IssueDto dto) {
        final var issue = issueService.save(dto);
        return ResponseEntity.ok(BaseResponse.of("Issue saved", IssueDto.from(issue)));
    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<BaseResponse> delete(@PathVariable Long issueId) {
        issueService.delete(issueId);
        return ResponseEntity.ok(BaseResponse.of("Issue deleted", null));
    }
}
