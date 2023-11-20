package ru.mpei.some.agent;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ReverseMessageBehaviour extends Behaviour {
    @Override
    public void action() {
        ACLMessage message = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
        if (message != null) {
            String[] content = message.getContent().split("!-!");
            for (int i = content.length-3; i > -1; i--){
                if (content[i].equals(myAgent.getLocalName())){
                    myAgent.addBehaviour(new SendResultBehaviour(message.getContent(), content[i-1]));
                }
            }
        } else {
            block();
        }

    }

    @Override
    public boolean done() {
        return false;
    }
}
