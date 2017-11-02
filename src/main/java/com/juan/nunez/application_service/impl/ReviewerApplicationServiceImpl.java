package com.juan.nunez.application_service.impl;

import com.juan.nunez.application_service.CommentApplicationService;
import com.juan.nunez.application_service.QueueApplicationService;
import com.juan.nunez.application_service.ReviewerApplicationService;
import com.juan.nunez.model.CommentDTO;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.ArrayList;
import java.util.Arrays;

import static com.juan.nunez.constants.CommentConstants.*;

@Service
public class ReviewerApplicationServiceImpl implements ReviewerApplicationService, ExceptionListener, MessageListener  {

    @Autowired
    protected QueueApplicationService queueApplicationService;
    @Autowired
    protected CommentApplicationService commentApplicationService;

    @Override
    public void createReviewer(String queue) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(CONNECTION_FACTORY);
        connectionFactory.setTrustedPackages(new ArrayList(Arrays.asList(TRUSTED_PACKAGES)));

        Connection connection = connectionFactory.createConnection();
        connection.start();

        connection.setExceptionListener(this);

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(queue);

        MessageConsumer reviewer = session.createConsumer(destination);
        reviewer.setMessageListener(this);
    }


    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        try {
            CommentDTO comment = (CommentDTO) objectMessage.getObject();
            reviewComment(comment);
        } catch (JMSException jmsEx) {
            System.out.println(EXCEPTION_ON_MESSAGE + jmsEx);
            jmsEx.printStackTrace();
        }
    }

    protected void reviewComment(CommentDTO comment) throws JMSException{
        if (comment != null) {
            queueApplicationService.sendCommentToQueue(comment, QUEUE_ACCEPTED);
            commentApplicationService.saveComment(comment);
        }
    }

    @Override
    public void onException(JMSException jmsEx) {
        System.out.println(jmsEx);
        jmsEx.printStackTrace();
    }

}
