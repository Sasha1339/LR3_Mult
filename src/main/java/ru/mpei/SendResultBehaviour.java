package ru.mpei;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.List;

public class SendResultBehaviour extends OneShotBehaviour {

    String content;

    String nameInit;

    public SendResultBehaviour(String content, String nameInit) {
        this.content = content;
        this.nameInit = nameInit;
    }

    @Override
    public void action() {
        List<AID> aids = AgentService.findAgents(myAgent, "Graph");
        ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
        aids.stream()
                .filter(aid -> aid.getLocalName().equals(nameInit))
                .forEach(aid -> message.addReceiver(aid));
        message.setContent(content);
        myAgent.send(message);
    }
}
