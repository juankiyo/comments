package com.juan.nunez.converter;

import com.juan.nunez.domain.Comment;
import com.juan.nunez.mapping.CommentMapper;
import com.juan.nunez.model.CommentDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CommentConverterTest {

    private CommentConverter commentConverter;
    private static final String ANY_STRING = "anyString";

    @Before
    public void setUp() {
        commentConverter = new CommentConverter();
        commentConverter.commentMapper = new CommentMapper();
    }

    @Test
    public void constructorShouldCreateCommentConverter() {
        assertNotNull(new CommentConverter());
    }

    @Test
    public void convertShouldConvertCommentIntoCommentDTO() {
        Comment comment = new Comment();
        comment.setName(ANY_STRING);
        comment.setComment(ANY_STRING);

        Object returnedObject = commentConverter.convert(comment);

        assertNotNull(returnedObject);
        assertEquals(CommentDTO.class, returnedObject.getClass());
    }

    @Test
    public void convertShouldConvertCommentDTOIntoComment() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setName(ANY_STRING);
        commentDTO.setComment(ANY_STRING);

        Object returnedObject = commentConverter.convert(commentDTO);

        assertNotNull(returnedObject);
        assertEquals(Comment.class, returnedObject.getClass());
    }
}
