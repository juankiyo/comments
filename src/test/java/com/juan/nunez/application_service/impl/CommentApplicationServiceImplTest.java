package com.juan.nunez.application_service.impl;

import com.juan.nunez.data_service.CommentDataService;
import com.juan.nunez.model.CommentDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CommentApplicationServiceImplTest {

    @InjectMocks
    private CommentApplicationServiceImpl commentApplicationServiceImpl;
    @Mock
    private CommentDataService mockCommentDataService;
    @Mock
    private CommentDTO mockCommentDTO;
    @Mock
    private List<CommentDTO> mockCommentDTOList;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveCommentShouldReturnCommentDTO() {
        when(mockCommentDataService.saveComment(any(CommentDTO.class))).thenReturn(mockCommentDTO);

        CommentDTO returnedComment = commentApplicationServiceImpl.saveComment(mockCommentDTO);

        verify(mockCommentDataService).saveComment(any(CommentDTO.class));
        assertEquals(mockCommentDTO, returnedComment);
    }

    @Test
    public void getAllCommentsShouldReturnListOfCommentDTO() {
        when(mockCommentDataService.getAllComments()).thenReturn(mockCommentDTOList);

        List<CommentDTO> returnedCommentDTOList = commentApplicationServiceImpl.getAllComments();

        verify(mockCommentDataService).getAllComments();
        assertEquals(mockCommentDTOList, returnedCommentDTOList);
    }


}
