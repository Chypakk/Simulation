package org.chypakk.model.dynamics;

import org.chypakk.model.*;
import org.chypakk.model.template.Entity;
import org.chypakk.model.template.Food;
import org.chypakk.model.template.Herbivore;
import org.chypakk.service.PathFinderService;

import java.util.Queue;

public class Boar extends Herbivore {

    private final int attack;

    public Boar(Sheep sheep) {
        super(sheep.getX(), sheep.getY(), "🐗");
        hp = sheep.getHp();
        speed = 2;
        attack = 1;
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
                Food food = (Food) map.getEntity(foodOnCell);
                this.setHp(hp + food.getHpHealed());

                map.setEntity(new Entity(food.getX(), food.getY(), ""));

                break;
            }

            Cell movingTo = path.poll();

            map.setEntityToCell(movingTo, this);
            map.setEntityToCell(currentCell, new Entity(this.getX(), this.getY(), ""));
        }
    }

    @Override
    public boolean canAttack() {
        return true;
    }

    public int getAttack() {
        return attack;
    }
}
