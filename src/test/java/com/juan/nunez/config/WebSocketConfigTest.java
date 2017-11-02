package com.juan.nunez.config;

import com.juan.nunez.application_service.ReviewerApplicationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.StompWebSocketEndpointRegistration;

import javax.jms.JMSException;

import static org.mockito.Mockito.*;

public class WebSocketConfigTest {

    @InjectMocks
    private WebSocketConfig webSocketConfig;
    @Mock
    private ReviewerApplicationService mockReviewerApplicationService;
    @Mock
    private MessageBrokerRegistry mockMessageBrokerRegistry;
    @Mock
    private StompEndpointRegistry mockStompEndpointRegistry;
    @Mock
    private StompWebSocketEndpointRegistration mockStompWebSocketEndpointRegistration;
    @Mock
    private JMSException mockJmsException;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void configureMessageBrokerShouldConfigureMessageBroker() throws JMSException {
        when(mockMessageBrokerRegistry.enableSimpleBroker(anyString())).thenReturn(null);
        when(mockMessageBrokerRegistry.setApplicationDestinationPrefixes(anyString())).thenReturn(null);
        doNothing().when(mockReviewerApplicationService).createReviewer(anyString());

        webSocketConfig.configureMessageBroker(mockMessageBrokerRegistry);

        verify(mockMessageBrokerRegistry).enableSimpleBroker(anyString());
        verify(mockMessageBrokerRegistry).setApplicationDestinationPrefixes(anyString());
        verify(mockReviewerApplicationService).createReviewer(anyString());
    }

    @Test
    public void registerStompEndpointsShouldConfigureEndPoint() {
        when(mockStompEndpointRegistry.addEndpoint(anyString())).thenReturn(mockStompWebSocketEndpointRegistration);
        when(mockStompWebSocketEndpointRegistration.withSockJS()).thenReturn(null);

        webSocketConfig.registerStompEndpoints(mockStompEndpointRegistry);

        verify(mockStompEndpointRegistry).addEndpoint(anyString());
        verify(mockStompWebSocketEndpointRegistration).withSockJS();
    }

}
