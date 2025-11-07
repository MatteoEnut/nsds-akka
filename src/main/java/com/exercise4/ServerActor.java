package com.exercise4;

import akka.actor.AbstractActor;
import akka.actor.AbstractActorWithStash;
import akka.actor.Props;

public class ServerActor extends AbstractActorWithStash {
    // dictionary for contacts
    //List<String, String> contacts = new HashMap<>();


    @Override
    public Receive createReceive() {
        return awake();
    }

    public Receive awake() {
        return receiveBuilder()
                .match(SleepMessage.class, this::onSleepMessage)
                .match(TextMessage.class, this::onText)
                .build();
    }

    public Receive asleep() {
        return receiveBuilder()
                .match(WakeupMessage.class, this::onWakeupMessage)
                .match(TextMessage.class, this::onTextSleep)
                .build();
    }

    private void onSleepMessage(SleepMessage sleepMessage) {
        System.out.println("Going to sleep");
        getContext().become(asleep());
    }

    private void onWakeupMessage(WakeupMessage wakeupMessage) {
        System.out.println("Waking up");
        getContext().become(awake());
        unstashAll();
    }

    private void onText(TextMessage textMessage) {
        System.out.println("Replying to text");
        sender().tell(textMessage, self());
    }

    private void onTextSleep(TextMessage textMessage) {
        System.out.println("Stashing text");
        stash();
    }

    static Props props() {
        return Props.create(ServerActor.class);
    }

}
