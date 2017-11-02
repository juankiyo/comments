package com.juan.nunez.application_service;

import com.juan.nunez.model.CommentDTO;

import javax.jms.JMSException;

public interface QueueApplicationService {

    void sendCommentToQueue(CommentDTO comment, String queue) throws JMSException;
}
