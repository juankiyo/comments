package com.juan.nunez.application_service.impl;

import com.juan.nunez.application_service.QueueApplicationService;
import com.juan.nunez.model.CommentDTO;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Service;

import javax.jms.*;

import static com.juan.nunez.constants.CommentConstants.CONNECTION_FACTORY;

@Service
public class QueueApplicationServiceImpl implements QueueApplicationService {

    @Override
    public void sendCommentToQueue(CommentDTO comment, String queue) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(CONNECTION_FACTORY);

        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(queue);

        MessageProducer producer = session.createProducer(destination);

        ObjectMessage objectMessage = session.createObjectMessage(comment);

        producer.send(objectMessage);

        session.close();
        connection.close();

    }

}
