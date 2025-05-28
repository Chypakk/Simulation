package org.chypakk.model.dynamics;

import org.chypakk.model.*;
import org.chypakk.model.template.Entity;
import org.chypakk.model.template.Food;
import org.chypakk.model.template.Herbivore;
import org.chypakk.service.PathFinderService;

import java.util.Queue;

public class Sheep extends Herbivore {
    public Sheep(int x, int y) {
        super(x, y, "🐑");
        this.speed = 2;
        this.hp = 10;
    }

    public Sheep() {
        super(0, 0, "🐑");
    }

    @Override
    public void makeMove(SimulationMap map, PathFinderService pathService) {
        Queue<Cell> path = pathService.findPathToFood(getCell());
        if (path.isEmpty()) return;

        path.poll();
        Cell currentCell;
        for (int step = 0; step < speed; step++) {
            currentCell = getCell();
            Cell foodOnCell = pathService.isFoodNear(currentCell);

            if (foodOnCell != null) {
                Food grass = (Food) map.getEntity(foodOnCell);
                this.setHp(hp + grass.getHpHealed());

                map.setEntity(new Entity(grass.getX(), grass.getY(), ""));

                if (hp > 15){
                    map.setEntity(new Boar(this));
                }

                break;
            }

            Cell movingTo = path.poll();

            map.setEntityToCell(movingTo, this);
            map.setEntityToCell(currentCell, new Entity(this.getX(), this.getY(), ""));
        }
    }

    @Override
    public boolean canAttack() {
        return false;
    }
}
