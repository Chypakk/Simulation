package org.chypakk;

import org.chypakk.model.SimulationMap;
import org.chypakk.service.BFSPathFinderService;
import org.chypakk.service.RenderServiceImpl;

public class Main {
    public static void main(String[] args) {

        try {
            SimulationMap map = new SimulationMap(7,7);
            Simulation simulation = new Simulation(map, new RenderServiceImpl(), new BFSPathFinderService(map));
            simulation.start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}