package com.juan.nunez.controller;

import com.juan.nunez.application_service.CommentApplicationService;
import com.juan.nunez.model.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.juan.nunez.constants.CommentConstants.*;

@Controller
public class HomePageController {

    @Autowired
    protected CommentApplicationService commentApplicationService;

    @RequestMapping(value = REQUEST_MAPPING_HOME)
    protected ModelAndView showHomePage(Model model) {
        List<CommentDTO> commentsList = commentApplicationService.getAllComments();
        model.addAttribute(MODEL_ATTRIBUTE_COMMENTS_LIST, commentsList);
        return new ModelAndView(MODEL_VIEW_PAGE);
    }
}
