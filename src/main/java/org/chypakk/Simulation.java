package org.chypakk;

import org.chypakk.action.impl.*;
import org.chypakk.action.template.Action;
import org.chypakk.model.Cell;
import org.chypakk.model.Creature;
import org.chypakk.model.Entity;
import org.chypakk.model.SimulationMap;
import org.chypakk.service.PathFinderService;
import org.chypakk.service.RenderService;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final SimulationMap map;
    private final RenderService renderService;
    private final List<Action> actionList;
    private final PathFinderService pathFinderService;

    public Simulation(SimulationMap map, RenderService renderService, PathFinderService pathFinderService) {
        this.map = map;
        this.renderService = renderService;
        this.pathFinderService = pathFinderService;
        actionList = new ArrayList<>();
    }

    public void start() throws InterruptedException {
        initAction(map.getCapacity());

        for (Action action : actionList) {
            action.perform(map);
        }

        renderService.render(map);
        Thread.sleep(3000);
    }

    public void nextTurn() throws InterruptedException {
        Entity entity;
        Cell cell;
        List<Creature> creatures = new ArrayList<>();

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                cell = new Cell(x,y);
                entity = map.getEntity(cell);

                if (entity instanceof Creature) {
//                    ((Creature) entity).makeMove(cell, map, pathFinderService);
                    creatures.add((Creature) entity);
                }
            }
        }

        for (var creature : creatures){
            creature.makeMove(creature.getCell(), map, pathFinderService);
        }

        renderService.render(map);
        Thread.sleep(2000);
    }

    public void pauseSimulation() {

    }

    private void initAction(int capacity) {
        actionList.add(new GrassSpawnAction(capacity));
        actionList.add(new TreeSpawnAction(capacity));
        actionList.add(new RockSpawnAction(capacity));
        actionList.add(new HerbivoreSpawnAction(capacity));
        actionList.add(new PredatorSpawnAction(capacity));
    }
}
