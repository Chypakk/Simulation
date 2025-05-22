package org.chypakk;

import org.chypakk.model.SimulationMap;
import org.chypakk.service.RenderServiceImpl;

public class Main {
    public static void main(String[] args) {

        try {
            new Simulation(new SimulationMap(5,5), new RenderServiceImpl()).start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}