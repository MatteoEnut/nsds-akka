package com.exercise4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import com.exercise3.ReplyGetMsg;
import com.faultTolerance.counter.CounterActor;

import java.util.concurrent.TimeoutException;

import static akka.pattern.Patterns.ask;
import static java.util.concurrent.TimeUnit.SECONDS;

public class ClientActor extends AbstractActor {


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(GenrateMsg.class, this::onGenrateMsg)
                .build();
    }

    private void onGenrateMsg(GenrateMsg genrateMsg) throws InterruptedException, TimeoutException {
        switch (genrateMsg.getId()){
            case 0:
                scala.concurrent.Future<Object> response = ask(genrateMsg.getServer(), new TextMessage(), 10000);
                System.out.println("Server response: " + response);
                break;
            case 1:
                genrateMsg.getServer().tell(new SleepMessage(),self());
                break;
            case 2:
                genrateMsg.getServer().tell(new WakeupMessage(),self());
                break;
        }
    }

    static Props props() {
        return Props.create(ClientActor.class);
    }

    // method to send message to the server
}
