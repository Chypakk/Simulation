package org.chypakk.model.dynamics;

import org.chypakk.model.Cell;
import org.chypakk.model.Creature;
import org.chypakk.model.SimulationMap;
import org.chypakk.service.PathFinderService;

public class Herbivore extends Creature {
    public Herbivore(int x, int y) {
        super(x, y, "🐑");
        this.speed = 2;
        this.hp = 10;
    }

    public Herbivore(){
        super(0,0, "🐑");
    }

    @Override
    public void makeMove(Cell currentCell, SimulationMap map, PathFinderService pathService) {

    }
}
