package com.exercise3;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.faultTolerance.counter.CounterSupervisorActor;

public class Contact {
    final ActorSystem sys = ActorSystem.create("System");
    final ActorRef server = sys.actorOf(ServerActor.props(), "server");
    final ActorRef client = sys.actorOf(ClientActor.props(), "client");



    // send the messages, i.e. GenerateSendMsg / GeneratePutMsg
}
