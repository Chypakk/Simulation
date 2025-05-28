package org.chypakk;

import org.chypakk.action.impl.*;
import org.chypakk.action.template.Action;
import org.chypakk.action.template.SpawnAction;
import org.chypakk.model.Cell;
import org.chypakk.model.template.Creature;
import org.chypakk.model.template.Entity;
import org.chypakk.model.SimulationMap;
import org.chypakk.model.template.Herbivore;
import org.chypakk.model.template.Predator;
import org.chypakk.service.PathFinderService;
import org.chypakk.service.RenderService;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private boolean running;
    private int countStep;

    private final SimulationMap map;

    private final RenderService renderService;
    private final PathFinderService pathFinderService;

    private final List<Action> actionList;
    private final List<SpawnAction> foodSpawnActionList;

    public Simulation(SimulationMap map, RenderService renderService, PathFinderService pathFinderService) {
        this.map = map;
        this.renderService = renderService;
        this.pathFinderService = pathFinderService;
        actionList = new ArrayList<>();
        foodSpawnActionList = new ArrayList<>();
    }

    public void start() throws InterruptedException {
        running = true;
        countStep = 0;

        initAction(map.getCapacity());

        for (Action action : actionList) {
            action.perform(map);
        }

        renderService.render(map);
        Thread.sleep(3000);

        while (running){
            nextTurn();
        }

        stopSimulation();
    }

    public void nextTurn() throws InterruptedException {
        Entity entity;
        Cell cell;
        List<Creature> creatures = new ArrayList<>();
        countStep++;

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                cell = new Cell(x,y);
                entity = map.getEntity(cell);

                if (entity instanceof Creature) {
                    creatures.add((Creature) entity);
                }
            }
        }

        for (var creature : creatures){
            creature.makeMove(map, pathFinderService);
        }

        for (var foodSpawn : foodSpawnActionList){
            foodSpawn.perform(map);
        }

        renderService.render(map);
        Thread.sleep(2000);

        if (endGame(creatures)) running = false;
    }

    private boolean endGame(List<Creature> creatures) {
        int predators = 0, herbivore = 0;
        for (var creature : creatures){
            if (creature instanceof Predator) predators++;
            if (creature instanceof Herbivore) herbivore++;
        }

        return predators == 0 || herbivore == 0;
    }

    public void stopSimulation() {
        System.out.println("Конец");
        System.out.println("Кол-во шагов - " + countStep);
    }

    private void initAction(int capacity) {

        foodSpawnActionList.add(new GrassSpawnAction(capacity));
        foodSpawnActionList.add(new CornSpawnAction(capacity));

        actionList.add(new GrassSpawnAction(capacity));
        actionList.add(new TreeSpawnAction(capacity));
        actionList.add(new RockSpawnAction(capacity));
        actionList.add(new SheepSpawnAction(capacity));
        actionList.add(new WolfSpawnAction(capacity));
    }
}
