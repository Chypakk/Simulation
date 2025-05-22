package org.chypakk;

import org.chypakk.action.Action;
import org.chypakk.action.GrassSpawnAction;
import org.chypakk.model.SimulationMap;
import org.chypakk.service.RenderService;

public class Simulation {
    private final SimulationMap map;
    private final RenderService renderService;

    public Simulation(SimulationMap map, RenderService renderService){
        this.map = map;
        this.renderService = renderService;
    }

    public void start() throws InterruptedException {
        Action action = new GrassSpawnAction(map.getCapacity());
        action.perform(map);
        renderService.render(map);
    }
}
