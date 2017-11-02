package com.juan.nunez.application_service.impl;

import com.juan.nunez.application_service.CommentApplicationService;
import com.juan.nunez.data_service.CommentDataService;
import com.juan.nunez.model.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentApplicationServiceImpl implements CommentApplicationService {

    @Autowired
    protected CommentDataService commentDataService;

    @Override
    public CommentDTO saveComment(CommentDTO commentDTO) {
        return commentDataService.saveComment(commentDTO);
    }

    @Override
    public List<CommentDTO> getAllComments() {
        return commentDataService.getAllComments();
    }
}
