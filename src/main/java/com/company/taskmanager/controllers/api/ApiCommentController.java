package com.company.taskmanager.controllers.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Comments", description = "The Comments API")
@RestController
@RequestMapping("/api/comment")
public class ApiCommentController {
}
