package com.gcp.sample.model;

import com.google.cloud.pubsub.Message;

/**
 * Created by lunar on 11/28/2016.
 */
public class PullRequest {
    public Message message;
    public String subscription;

    public PullRequest() {

    }
}
