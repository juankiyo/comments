package com.juan.nunez.controller;

import com.juan.nunez.application_service.QueueApplicationService;
import com.juan.nunez.model.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;

import static com.juan.nunez.constants.CommentConstants.*;

@RestController
public class CommentController {

    @Autowired
    protected QueueApplicationService queueApplicationService;

    @MessageMapping(BACKEND_POINT_ADD_COMMENT)
    @SendTo(SUBSCRIPTION_ALL_COMMENTS)
    public CommentDTO addComment(CommentDTO comment) {
        try {
            queueApplicationService.sendCommentToQueue(comment, QUEUE_FOR_REVIEW);
        } catch (JMSException jmsEx) {
            System.out.println(EXCEPTION_SEND_COMMENT_TO_QUEUE + jmsEx);
            jmsEx.printStackTrace();
        }
        return new CommentDTO(comment.getName(), comment.getComment());
    }


}
