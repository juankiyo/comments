package com.juan.nunez.application_service;

import com.juan.nunez.model.CommentDTO;

import java.util.List;

public interface CommentApplicationService {
    CommentDTO saveComment(CommentDTO commentDTO);
    List<CommentDTO> getAllComments();
}
