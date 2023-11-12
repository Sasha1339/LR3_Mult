package ru.mpei.some.agent;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import ru.mpei.helpers.AgentNear;
import ru.mpei.helpers.InfoAgent;
import ru.mpei.some.agent.SendInfoBehaviour;
import ru.mpei.some.agent.SendResultBehaviour;

public class AnalysisBehaviour extends Behaviour {

    AgentNear agentNear;

    public AnalysisBehaviour(AgentNear agentNear) {
        this.agentNear = agentNear;
    }

    @Override
    public void action() {

        ACLMessage message = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
        if (message != null){
            String[] agents = message.getContent().split("!-!");
            if (agentNear.getInfoAgent().size() == 1){
                myAgent.addBehaviour(new SendResultBehaviour("fail!-!fail", agents[0]));
            } else {
                if (myAgent.getLocalName().equals(agents[agents.length-2])){
                    myAgent.addBehaviour(new SendResultBehaviour(message.getContent(), agents[0]));
                }else{
                    for(InfoAgent infoAgent: agentNear.getInfoAgent()){
                        if (!infoAgent.getLocalName().equals(message.getSender().getLocalName())){
                            StringBuilder content = new StringBuilder();
                            for(int i = 0; i < agents.length; i++){
                                if (i == 0) {
                                    content.append(agents[i]);
                                }else if(i == agents.length - 3) {
                                    content.append("!-!").append(agents[i]).append("!-!").append(infoAgent.getLocalName());
                                } else if (i == agents.length-1){
                                    int weight = Integer.parseInt(agents[i]);
                                    weight += infoAgent.getWeight();
                                    content.append("!-!").append(weight);
                                } else {
                                    content.append("!-!").append(agents[i]);
                                }
                            }
                            myAgent.addBehaviour(new SendInfoBehaviour(infoAgent.getLocalName(), content.toString()));
                        }
                    }

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
