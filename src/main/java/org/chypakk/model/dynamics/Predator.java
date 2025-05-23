package org.chypakk.model.dynamics;

import org.chypakk.model.Cell;
import org.chypakk.model.Creature;
import org.chypakk.model.Entity;
import org.chypakk.model.SimulationMap;
import org.chypakk.service.PathFinderService;

import java.util.Queue;

public class Predator extends Creature {

    private final int attack;

    public Predator(int x, int y) {
        super(x, y, "🐺");

        this.speed = 1;
        this.hp = 20;
        this.attack = 3;
    }

    public Predator() {
        super(0, 0, "🐺");
        attack = 0;
    }

    @Override
    public void makeMove(Cell currentCell, SimulationMap map, PathFinderService pathService) {
        Queue<Cell> path = pathService.findPathToHerbivore(currentCell);
        if (path.isEmpty()) return;

        path.poll();

        for (int step = 0; step < speed; step++) {
            Cell herbivoreOnCell = pathService.isHerbivoreNear(currentCell);

            if (herbivoreOnCell != null) {
                Herbivore herbivore = (Herbivore) map.getEntity(herbivoreOnCell);
                herbivore.setHp(herbivore.getHp() - attack);

                if (!herbivore.isAlive()) {
                    map.setEntity(new Entity(herbivore.getX(), herbivore.getY(), ""));
                }

                break;
            }

            Cell movingTo = path.poll();

            map.setEntityToCell(movingTo, this);
            map.setEntityToCell(currentCell, new Entity(this.getX(), this.getY(), ""));
        }
    }


}
