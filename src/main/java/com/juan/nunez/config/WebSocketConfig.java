package com.juan.nunez.config;

import com.juan.nunez.application_service.ReviewerApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import javax.jms.JMSException;

import static com.juan.nunez.constants.CommentConstants.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Autowired
    protected ReviewerApplicationService reviewerApplicationService;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(BROKER_CHANNEL);
        config.setApplicationDestinationPrefixes(APPLICATION_PREFIX_COMMENTS);
        try {
            reviewerApplicationService.createReviewer(QUEUE_FOR_REVIEW);
        } catch (JMSException jmsEx) {
            System.out.println(EXCEPTION_CREATE_REVIEWER + jmsEx);
            jmsEx.printStackTrace();
        }

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(BACKEND_POINT_ADD_COMMENT).withSockJS();
    }



}
