package com.juan.nunez.converter;

import com.juan.nunez.domain.Comment;
import com.juan.nunez.mapping.CommentMapper;
import com.juan.nunez.model.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {

    @Autowired
    protected CommentMapper  commentMapper;

    public Comment convert(CommentDTO commentDTO) {
        return commentMapper.map(commentDTO, Comment.class);
    }

    public CommentDTO convert(Comment comment) {
        return commentMapper.map(comment, CommentDTO.class);
    }
}
