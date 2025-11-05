package com.counter;

import akka.actor.AbstractActor;
import akka.actor.AbstractActorWithStash;
import akka.actor.Props;

public class CounterActor extends AbstractActorWithStash {

	private int counter;

	public CounterActor() {
		this.counter = 0;
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder()
                .match(DataMessage.class, this::onMessage)
                .build();
	}

    private void onDecrementMessage(DecrementMessage decrementMessage) {
        --counter;
        System.out.println("Counter decremented to: " + counter);
    }

    private void onOtherMessage(OtherMessage otherMessage) {
        System.out.println("Other message received");
    }

    void onMessage(DataMessage msg) {
		if (msg.getCode() == 0) { //increment message
            counter++;
            System.out.println("Counter received: " + counter);
            unstash();
        }
        else if (msg.getCode() == 1) { //decrement message
            if (counter == 0) {
                    stash();
            }
            else {
                counter--;
                System.out.println("Counter received: " + counter);

//                if (counter > 0) {
//                    unstash();
//                }
            }

        }

	}

	static Props props() {
		return Props.create(CounterActor.class);
	}

}
