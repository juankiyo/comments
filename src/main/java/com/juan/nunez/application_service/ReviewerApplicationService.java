package com.juan.nunez.application_service;

import javax.jms.JMSException;

public interface ReviewerApplicationService {

    void createReviewer(String queue) throws JMSException;
}
