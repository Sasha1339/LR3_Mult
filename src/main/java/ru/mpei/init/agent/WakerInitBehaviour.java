package ru.mpei.init.agent;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;

public class WakerInitBehaviour extends WakerBehaviour {

    public WakerInitBehaviour(Agent a, long timeout) {
        super(a, timeout);
    }

    @Override
    protected void onWake() {

    }
}
