package com.juan.nunez.application_service.impl;


import com.juan.nunez.application_service.CommentApplicationService;
import com.juan.nunez.application_service.QueueApplicationService;
import com.juan.nunez.model.CommentDTO;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.jms.*;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({
        ReviewerApplicationServiceImpl.class,
        ActiveMQConnectionFactory.class})
public class ReviewerApplicationServiceImplTest {

    @InjectMocks
    private ReviewerApplicationServiceImpl reviewerApplicationServiceImpl;
    private ReviewerApplicationServiceImpl spyReviewerApplicationServiceImpl;
    @Mock
    private QueueApplicationService mockQueueApplicationService;
    @Mock
    private CommentApplicationService mockCommentApplicationService;
    @Mock
    private ActiveMQConnectionFactory mockActiveMQConnectionFactory;
    @Mock
    private Connection mockConnection;
    @Mock
    private Session mockSession;
    @Mock
    private MessageConsumer mockMessageConsumer;
    @Mock
    private Queue mockQueue;
    @Mock
    private JMSException mockJmsException;
    @Mock
    private CommentDTO mockCommentDTO;
    @Mock
    private ObjectMessage mockObjectMessage;

    private static final String ANY_STRING = "anyString";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        spyReviewerApplicationServiceImpl = spy(new ReviewerApplicationServiceImpl());
        PowerMockito.spy(ActiveMQConnectionFactory.class);
    }

    @Test
    public void createReviewerShouldCreateReviewer() throws Exception {
        PowerMockito.whenNew(ActiveMQConnectionFactory.class).withAnyArguments().thenReturn(mockActiveMQConnectionFactory);

        doNothing().when(mockActiveMQConnectionFactory).setTrustedPackages(anyList());
        when(mockActiveMQConnectionFactory.createConnection()).thenReturn(mockConnection);
        doNothing().when(mockConnection).start();
        doNothing().when(mockConnection).setExceptionListener(any(ExceptionListener.class));
        when(mockConnection.createSession(anyBoolean(), anyInt())).thenReturn(mockSession);
        when(mockSession.createQueue(anyString())).thenReturn(mockQueue);
        when(mockSession.createConsumer(mockQueue)).thenReturn(mockMessageConsumer);
        doNothing().when(mockMessageConsumer).setMessageListener(any(MessageListener.class));

        reviewerApplicationServiceImpl.createReviewer(ANY_STRING);

        verify(mockActiveMQConnectionFactory).setTrustedPackages(anyList());
        verify(mockActiveMQConnectionFactory).createConnection();
        verify(mockConnection).start();
        verify(mockConnection).setExceptionListener(any(ExceptionListener.class));
        verify(mockConnection).createSession(anyBoolean(), anyInt());
        verify(mockSession).createQueue(anyString());
        verify(mockSession).createConsumer(mockQueue);
        verify(mockMessageConsumer).setMessageListener(any(MessageListener.class));
    }

    @Test(expected = JMSException.class)
    public void createReviewerShouldThrowJMSException() throws JMSException {
        doThrow(mockJmsException).when(spyReviewerApplicationServiceImpl).createReviewer(anyString());

        spyReviewerApplicationServiceImpl.createReviewer(ANY_STRING);
    }

    @Test
    public void reviewCommentShould_SendCommentToAcceptedQueueAndSaveItOnDatabase() throws JMSException {
        doNothing().when(mockQueueApplicationService).sendCommentToQueue(any(CommentDTO.class), anyString());
        when(mockCommentApplicationService.saveComment(any(CommentDTO.class))).thenReturn(mockCommentDTO);

        reviewerApplicationServiceImpl.reviewComment(mockCommentDTO);

        verify(mockQueueApplicationService).sendCommentToQueue(any(CommentDTO.class), anyString());
        verify(mockCommentApplicationService).saveComment(any(CommentDTO.class));
    }

    @Test(expected = JMSException.class)
    public void reviewCommentShouldThrowJMSException() throws JMSException {
        ReviewerApplicationServiceImpl spyReviewerApplicationServiceImpl = spy(new ReviewerApplicationServiceImpl());
        doThrow(mockJmsException).when(spyReviewerApplicationServiceImpl).reviewComment(any(CommentDTO.class));

        spyReviewerApplicationServiceImpl.reviewComment(mockCommentDTO);
    }

    @Test
    public void onMessageShouldReviewComment() throws JMSException {
        when(mockObjectMessage.getObject()).thenReturn(mockCommentDTO);
        doNothing().when(spyReviewerApplicationServiceImpl).reviewComment(mockCommentDTO);

        spyReviewerApplicationServiceImpl.onMessage(mockObjectMessage);

        verify(spyReviewerApplicationServiceImpl).reviewComment(any(CommentDTO.class));
    }
}
