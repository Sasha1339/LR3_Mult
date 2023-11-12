package ru.mpei;

import jade.core.Agent;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class NodeAgent extends Agent {

    @Override
    protected void setup() {
        AgentService.registerAgent(this, "Graph");
        AgentNear agentNear = null;
        try {
            JAXBContext context = JAXBContext.newInstance(AgentNear.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            agentNear = (AgentNear) unmarshaller.unmarshal(
                    new File("src/main/resources/"+this.getLocalName()+".xml")
            );
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        if (agentNear.isInit()){
            this.addBehaviour(new InitSearchBehaviour(agentNear));
            this.addBehaviour(new ParallelInitBehaviour());
        } else {
            this.addBehaviour(new AnalysisBehaviour(agentNear));
        }




    }
}
