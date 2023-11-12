package ru.mpei.init.agent;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class WaitingResultsBehaviour extends Behaviour {

    private List<String> results;

    public WaitingResultsBehaviour(List<String> results) {
        this.results = results;
    }

    @Override
    public void action() {
        ACLMessage message = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
        if (message != null){
            results.add(message.getContent());
        } else {
            block();
        }

    }

    @Override
    public boolean done() {
        return false;
    }
}
