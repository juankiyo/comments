package com.juan.nunez.controller;

import com.juan.nunez.application_service.QueueApplicationService;
import com.juan.nunez.model.CommentDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.jms.JMSException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class CommentControllerTest {

    @InjectMocks
    private CommentController commentController;
    @Mock
    private QueueApplicationService mockQueueApplicationService;
    @Mock
    private CommentDTO mockCommentDTO;
    @Mock
    private JMSException mockJmsException;
    private CommentController spyCommentController;
    private static final String ANY_STRING = "anyString";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        spyCommentController = spy(new CommentController());
    }

    @Test
    public void addCommentShouldReturnCommentDTO() throws JMSException {
        doNothing().when(mockQueueApplicationService).sendCommentToQueue(any(CommentDTO.class), anyString());
        when(mockCommentDTO.getName()).thenReturn(ANY_STRING);
        when(mockCommentDTO.getComment()).thenReturn(ANY_STRING);

        CommentDTO returnedCommentDTO = commentController.addComment(mockCommentDTO);

        verify(mockQueueApplicationService).sendCommentToQueue(any(CommentDTO.class), anyString());
        assertEquals(ANY_STRING, returnedCommentDTO.getName());
        assertEquals(ANY_STRING, returnedCommentDTO.getComment());
    }

}
