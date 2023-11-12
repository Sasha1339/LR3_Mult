package ru.mpei;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.List;

public class SendInfoBehaviour extends OneShotBehaviour {

    String agentReceiver;

    String content;

    public SendInfoBehaviour(String agentReceiver, String content) {
        this.agentReceiver = agentReceiver;
        this.content = content;
    }

    @Override
    public void action() {
        List<AID> aids = AgentService.findAgents(myAgent, "Graph");
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        aids.stream()
                .filter(aid -> aid.getLocalName().equals(agentReceiver))
                .forEach(aid -> message.addReceiver(aid));
        message.setContent(content);
        myAgent.send(message);
    }
}
