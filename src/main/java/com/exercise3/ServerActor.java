package com.exercise3;

import akka.actor.AbstractActor;
import akka.actor.Actor;
import akka.actor.Props;
import com.faultTolerance.counter.CounterActor;

import java.util.HashMap;
import java.util.Map;

public class ServerActor extends AbstractActor {
    // dictionary for contacts
    Map<String, String> contacts = new HashMap<>();


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(PutMsg.class, this::onPutMsg)
                .match(GetMsg.class, this::onGetMsg)
                .build();
    }

    private void onGetMsg(GetMsg getMsg) {
        String email = contacts.get(getMsg.getName());
        sender().tell(new ReplyGetMsg(email), self());
    }

    private void onPutMsg(PutMsg putMsg) {
        contacts.put(putMsg.getName(), putMsg.getEmail());
    }

    static Props props() {
        return Props.create(ServerActor.class);
    }
}
