package com.exercise3;

import akka.actor.AbstractActor;

public class ClientActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ReplyGetMsg.class, this::onReplyGetMsg)
                .build();
    }

    private void onReplyGetMsg(ReplyGetMsg replyGetMsg) {
        System.out.println("Email received: " +  replyGetMsg.getEmail());
    }

    // method to send message to the server
}
