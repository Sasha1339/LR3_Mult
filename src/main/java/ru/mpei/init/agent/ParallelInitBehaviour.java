package ru.mpei.init.agent;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ParallelInitBehaviour extends ParallelBehaviour {


    private List<String> results =new ArrayList<>();

    private String pathResult;

    private int minWeight;
    public ParallelInitBehaviour(){
        super(WHEN_ANY);
    }
    private Behaviour receive, wakerBeh;

    @Override
    public void onStart() {
        receive = new WaitingResultsBehaviour(results);
        wakerBeh = new WakerInitBehaviour(myAgent, 10000);
        this.addSubBehaviour(receive);
        this.addSubBehaviour(wakerBeh);
    }

    @Override
    public int onEnd() {
        if (wakerBeh.done()) {
            for (int i = 0; i < results.size(); i++){
                log.info(results.get(i));
                String[] resul = results.get(i).split("!-!");
                if (resul[0].equals("fail")){
                    continue;
                }
                if (i == 0){
                    minWeight = Integer.parseInt(resul[resul.length-1]);
                    pathResult = results.get(i);
                } else {
                    if (Integer.parseInt(resul[resul.length-1]) < minWeight){
                        minWeight = Integer.parseInt(resul[resul.length-1]);
                        pathResult = results.get(i);
                    }
                }
            }
            String[] resul = pathResult.split("!-!");
            StringBuilder path = new StringBuilder();
            path.append(resul[0]);
            for (int i = 1; i < resul.length; i++){
                if (!(i == resul.length-1 || i == resul.length-2)){
                    path.append("-").append(resul[i]);
                }
            }
            log.warn("Found short path: "+path.toString()+", which have weight = "+minWeight);
        }
    return 0;
    }
}
