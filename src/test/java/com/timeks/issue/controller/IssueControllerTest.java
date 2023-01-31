package com.timeks.issue.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.timeks.base.enums.IssueType;
import com.timeks.base.model.BaseResponse;
import com.timeks.issue.model.Issue;
import com.timeks.issue.model.Project;
import com.timeks.issue.model.dto.IssueDto;
import com.timeks.issue.service.IssueService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = IssueController.class)
class IssueControllerTest {
    @MockBean
    private IssueService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Test
    void save_shouldReturnOk() {
        var issueDto = IssueDto.builder()
                        .issueTypeCode(IssueType.TASK.getCode())
                        .description("Test Issue")
                        .build();


        var issue = Issue.from(issueDto);
        issue.setId(1L);
        var expectedResponse = BaseResponse.of("Issue saved", IssueDto.from(issue));

        when(service.save(any(IssueDto.class)))
                .thenReturn(issue);

        MvcResult result = mockMvc.perform(
            post(IssueController.BASE_URI)
                    .content(objectMapper.writeValueAsString(issueDto))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        String actual = result.getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.writeValueAsString(expectedResponse), actual);

        ArgumentCaptor<IssueDto> issueDtoCaptor = ArgumentCaptor.forClass(IssueDto.class);
        verify(service, times(1)).save(issueDtoCaptor.capture());
        assertThat(issueDtoCaptor.getValue()).isEqualTo(issueDto);
    }

    @SneakyThrows
    @Test
    void saveShouldReturnOkWhenProjectIdIsGiven() {
        var issueDto = IssueDto.builder()
                .issueTypeCode(IssueType.TASK.getCode())
                .description("Test Issue")
                .projectId(1L)
                .build();

        var project = Project.builder()
                .id(1L)
                .name("Test Project")
                .build();

        var issue = Issue.from(issueDto);
        issue.setId(1L);
        issue.setProject(project);
        var expectedResponse = BaseResponse.of("Issue saved", IssueDto.from(issue));

        when(service.save(any(IssueDto.class)))
                .thenReturn(issue);

        mockMvc.perform(
                    post(IssueController.BASE_URI)
                            .content(objectMapper.writeValueAsString(issueDto))
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.description").value("Test Issue"))
            .andExpect(jsonPath("$.data.key").value(1))
            .andExpect(jsonPath("$.data.project.key").value(1));

        var issueDtoCapture = ArgumentCaptor.forClass(IssueDto.class);
        verify(service, times(1)).save(issueDtoCapture.capture());

        assertThat(issueDtoCapture.getValue()).isEqualTo(issueDto);
    }

    @SneakyThrows
    @Test
    void save_shouldReturn400() {
        var issueDto = IssueDto.builder()
                .description("Test Issue")
                .build();

        ArgumentCaptor<IssueDto> issueDtoCaptor = ArgumentCaptor.forClass(IssueDto.class);

        var errorPhrase = "Following fields are not compatible";

        MvcResult result = mockMvc.perform(
                        post(IssueController.BASE_URI)
                                .content(objectMapper.writeValueAsString(issueDto))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        String actual = result.getResponse().getContentAsString();
        Assertions.assertTrue(actual.contains(errorPhrase));

        verify(service, times(0)).save(issueDtoCaptor.capture());
    }
}
