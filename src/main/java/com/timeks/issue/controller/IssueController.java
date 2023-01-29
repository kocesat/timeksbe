package com.timeks.issue.controller;

import com.timeks.base.model.BaseResponse;
import com.timeks.issue.model.IssueDto;
import com.timeks.issue.service.IssueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/issue")
public class IssueController {
    private final IssueService issueService;

    @GetMapping
    public ResponseEntity<BaseResponse> getAll() {
        var issues = issueService.findAll()
                .stream()
                .map(IssueDto::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(BaseResponse.of(issues));
    }

    @PostMapping
    public ResponseEntity<BaseResponse> save(@RequestBody @Valid IssueDto dto) {
        final var issue = issueService.save(dto);
        return ResponseEntity.ok(BaseResponse.withMessage("Issue saved", IssueDto.from(issue)));
    }
}
