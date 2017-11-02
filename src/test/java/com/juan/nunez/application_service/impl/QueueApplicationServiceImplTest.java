package com.juan.nunez.application_service.impl;

import com.juan.nunez.model.CommentDTO;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.jms.*;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({
        QueueApplicationServiceImpl.class,
        ActiveMQConnectionFactory.class})
public class QueueApplicationServiceImplTest {


    private QueueApplicationServiceImpl queueApplicationServiceImpl;
    @Mock
    private ActiveMQConnectionFactory mockActiveMQConnectionFactory;
    @Mock
    private Connection mockConnection;
    @Mock
    private Session mockSession;
    @Mock
    private Queue mockQueue;
    @Mock
    private MessageProducer mockMessageProducer;
    @Mock
    private ObjectMessage mockObjectMessage;
    @Mock
    private CommentDTO mockCommentDTO;
    @Mock
    private JMSException mockJmsException;

    private static final String ANY_STRING = "anyString";


    @Before
    public void setUp() {
        queueApplicationServiceImpl = new QueueApplicationServiceImpl();

        PowerMockito.spy(ActiveMQConnectionFactory.class);
    }

    @Test
    public void sendCommentToQueueShouldSendCommentToQueue() throws Exception {
        PowerMockito.whenNew(ActiveMQConnectionFactory.class).withAnyArguments().thenReturn(mockActiveMQConnectionFactory);

        when(mockActiveMQConnectionFactory.createConnection()).thenReturn(mockConnection);
        doNothing().when(mockConnection).start();
        when(mockConnection.createSession(anyBoolean(), anyInt())).thenReturn(mockSession);
        when(mockSession.createQueue(anyString())).thenReturn(mockQueue);
        when(mockSession.createProducer(mockQueue)).thenReturn(mockMessageProducer);
        when(mockSession.createObjectMessage(any(CommentDTO.class))).thenReturn(mockObjectMessage);
        doNothing().when(mockMessageProducer).send(any(ObjectMessage.class));
        doNothing().when(mockSession).close();
        doNothing().when(mockConnection).close();

        queueApplicationServiceImpl.sendCommentToQueue(mockCommentDTO, ANY_STRING);

        verify(mockActiveMQConnectionFactory).createConnection();
        verify(mockConnection).createSession(anyBoolean(), anyInt());
        verify(mockSession).createQueue(anyString());
        verify(mockSession).createProducer(mockQueue);
        verify(mockSession).createObjectMessage(any(CommentDTO.class));
        verify(mockMessageProducer).send(any(ObjectMessage.class));
        verify(mockSession).close();
        verify(mockConnection).close();
    }

    @Test(expected = JMSException.class)
    public void sendCommentToQueueShouldThrowJMSException() throws JMSException {
        QueueApplicationServiceImpl spyQueueApplicationServiceImpl = spy(new QueueApplicationServiceImpl());
        doThrow(mockJmsException).when(spyQueueApplicationServiceImpl).sendCommentToQueue(any(CommentDTO.class),
                anyString());

        spyQueueApplicationServiceImpl.sendCommentToQueue(mockCommentDTO, ANY_STRING);

    }


}
