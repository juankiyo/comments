package com.juan.nunez.data_service.impl;

import com.juan.nunez.converter.CommentConverter;
import com.juan.nunez.data_access.CommentRepository;
import com.juan.nunez.data_service.CommentDataService;
import com.juan.nunez.domain.Comment;
import com.juan.nunez.model.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentDataServiceImpl implements CommentDataService {

    @Autowired
    protected CommentRepository commentRepository;
    @Autowired
    protected CommentConverter commentConverter;

    @Override
    public CommentDTO saveComment(CommentDTO commentDTO) {
        Comment comment = commentConverter.convert(commentDTO);
        comment = commentRepository.save(comment);
        return commentConverter.convert(comment);
    }

    @Override
    public List<CommentDTO> getAllComments() {
        List<Comment> commentList = commentRepository.findAll();
        List<CommentDTO> commentDTOList = new ArrayList<>();
        if (commentList != null) {
            commentDTOList.addAll(commentList.stream().map(commentConverter::convert).collect(Collectors.toList()));
        }
        return commentDTOList;
    }
}
