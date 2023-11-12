package ru.mpei;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;

import java.util.List;
import java.util.Map;

public class JadeStarter {

    public static void main(String[] args) {
        Map<String, Class<?>> agents = new java.util.HashMap<>(Map.of(
                "Agent1", NodeAgent.class,
                "Agent2", NodeAgent.class,
                "Agent3", NodeAgent.class,
                "Agent4", NodeAgent.class,
                "Agent5", NodeAgent.class,
                "Agent6", NodeAgent.class,
                "Agent7", NodeAgent.class,
                "Agent8", NodeAgent.class,
                "Agent9", NodeAgent.class,
                "Agent10", NodeAgent.class
        ));
        agents.put("Agent11", NodeAgent.class);
        agents.put("Agent12", NodeAgent.class);

        Properties props = new ExtendedProperties();
        props.setProperty("gui", "true");
        props.setProperty("agents", addAgents(agents));
        props.setProperty("services", addServices(List.of("jade.core.messaging.TopicManagementService")));
        ProfileImpl p = new ProfileImpl(props);

        Runtime.instance().setCloseVM(true);
        Runtime.instance().createMainContainer(p);
    }

    private static String addAgents(Map<String, Class<?>> createAgents){
        String outString = "";
        for (Map.Entry<String, Class<?>> entry : createAgents.entrySet()) {
            outString += entry.getKey()+":"+entry.getValue().getName()+";";
        }
        System.out.println(outString);
        return outString;
    }

    private static String addServices(List<String> services) {
        String outString ="";
        for (String service : services) {
            outString+=service+";";
        }
        return outString;
    }

}
