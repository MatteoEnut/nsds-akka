package com.exercise3;

import akka.actor.AbstractActor;
import akka.actor.Props;
import com.faultTolerance.counter.CounterActor;

public class ClientActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ReplyGetMsg.class, this::onReplyGetMsg)
                .match(GenerateGetMsg.class, this::onGenerateGetMsg)
                .match(GenerateGetMsg.class, this::onGenerateGetMsg)

                .build();
    }

    private void onGenerateGetMsg(GenerateGetMsg generateGetMsg) {
    }

    private void onReplyGetMsg(ReplyGetMsg replyGetMsg) {
        System.out.println("Email received: " +  replyGetMsg.getEmail());
    }

    // method to send message to the server

    static Props props() {
        return Props.create(ClientActor.class);
    }
}
