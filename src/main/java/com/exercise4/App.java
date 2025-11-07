package com.exercise4;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.counter.DataMessage;
import com.faultTolerance.counter.CounterActor;
import com.faultTolerance.counter.CounterSupervisorActor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

import static akka.pattern.Patterns.ask;
import static java.util.concurrent.TimeUnit.SECONDS;

public class App {

    private static final int numThreads = 10;

    public static void main(String[] args) {


        final ActorSystem sys = ActorSystem.create("System");
        final ActorRef server = sys.actorOf(ServerActor.props(), "server");
        final ActorRef client = sys.actorOf(ClientActor.props(), "client");
        final ExecutorService exec = Executors.newFixedThreadPool(numThreads);

        exec.submit(() -> client.tell(new GenrateMsg(0,server), ActorRef.noSender()));
        exec.submit(() -> client.tell(new GenrateMsg(1,server), ActorRef.noSender()));
        exec.submit(() -> client.tell(new GenrateMsg(0,server), ActorRef.noSender()));
        exec.submit(() -> client.tell(new GenrateMsg(0,server), ActorRef.noSender()));
        exec.submit(() -> client.tell(new GenrateMsg(0,server), ActorRef.noSender()));
        exec.submit(() -> client.tell(new GenrateMsg(2,server), ActorRef.noSender()));


    }
}
