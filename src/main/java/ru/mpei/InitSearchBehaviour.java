package ru.mpei;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.List;

public class InitSearchBehaviour extends OneShotBehaviour {

    AgentNear agentNear;

    public InitSearchBehaviour(AgentNear agentNear) {
        this.agentNear = agentNear;
    }

    @Override
    public void action() {
        List<AID> aids = AgentService.findAgents(myAgent, "Graph");
        for(InfoAgent infoAgent: agentNear.getInfoAgent()){
            ACLMessage message = new ACLMessage(ACLMessage.INFORM);
            message.setContent(
                    myAgent.getLocalName()+"!-!"+infoAgent.getLocalName()+"!-!"
                            +agentNear.getSearch()+"!-!"+infoAgent.getWeight()
                    );
            aids.stream()
                    .filter(aid -> aid.getLocalName().equals(infoAgent.getLocalName()))
                    .forEach(aid -> message.addReceiver(aid));
            myAgent.send(message);
        }
    }
}
