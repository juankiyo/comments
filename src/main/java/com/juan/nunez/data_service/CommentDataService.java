package com.juan.nunez.data_service;

import com.juan.nunez.model.CommentDTO;

import java.util.List;

public interface CommentDataService {

    CommentDTO saveComment(CommentDTO commentDTO);
    List<CommentDTO> getAllComments();
}
