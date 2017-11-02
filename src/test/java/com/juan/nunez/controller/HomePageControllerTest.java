package com.juan.nunez.controller;

import com.juan.nunez.application_service.CommentApplicationService;
import com.juan.nunez.model.CommentDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.juan.nunez.constants.CommentConstants.MODEL_VIEW_PAGE;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

public class HomePageControllerTest {

    @InjectMocks
    private HomePageController homePageController;
    @Mock
    private CommentApplicationService mockCommentApplicationService;
    @Mock
    private List<CommentDTO> mockCommentDTOList;
    @Mock
    private Model mockModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void showHomePageShouldReturnHomePageView() {
        when(mockCommentApplicationService.getAllComments()).thenReturn(mockCommentDTOList);

        ModelAndView returnedView = homePageController.showHomePage(mockModel);

        verify(mockCommentApplicationService).getAllComments();
        verify(mockModel).addAttribute(anyString(), any(List.class));
        assertViewName(returnedView, MODEL_VIEW_PAGE);

    }
}
