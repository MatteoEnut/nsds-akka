package com.exercise4;

import akka.actor.ActorRef;

public class GenrateMsg {
    ActorRef server;
    int id;

    public GenrateMsg(int id, ActorRef server) {
        this.id = id;
        this.server = server;
    }

    public int getId() {
        return id;
    }

    public ActorRef getServer() {
        return server;
    }
}
