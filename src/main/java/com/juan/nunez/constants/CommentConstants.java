package com.juan.nunez.constants;

public final class CommentConstants {


    public static final String QUEUE_FOR_REVIEW = "FOR_REVIEW";
    public static final String QUEUE_ACCEPTED = "ACCEPTED";

    public static final String TRUSTED_PACKAGES = "com.juan.nunez.model";
    public static final String CONNECTION_FACTORY = "vm://localhost";

    public static final String BACKEND_POINT_ADD_COMMENT = "/add";
    public static final String BROKER_CHANNEL = "/channel";
    public static final String SUBSCRIPTION_ALL_COMMENTS = BROKER_CHANNEL + "/allcomments";
    public static final String APPLICATION_PREFIX_COMMENTS = "/comments";

    public static final String REQUEST_MAPPING_HOME = "/home";
    public static final String MODEL_ATTRIBUTE_COMMENTS_LIST = "commentsList";
    public static final String MODEL_VIEW_PAGE = "page";

    public static final String EXCEPTION_SEND_COMMENT_TO_QUEUE = "Exception when calling method QueueApplicationService.sendCommentToQueue: ";
    public static final String EXCEPTION_CREATE_REVIEWER = "Exception when calling method ReviewerApplicationService.createReviewer: ";
    public static final String EXCEPTION_ON_MESSAGE = "Exception when calling method ReviewerApplicationService.onMessage: ";


}
