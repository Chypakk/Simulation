package org.chypakk.model.dynamics;

import org.chypakk.model.Cell;
import org.chypakk.model.template.Creature;
import org.chypakk.model.template.Entity;
import org.chypakk.model.SimulationMap;
import org.chypakk.model.template.Predator;
import org.chypakk.service.PathFinderService;

import java.util.Queue;

public class Wolf extends Predator {

    public Wolf(int x, int y) {
        super(x, y, "🐺");

        this.speed = 1;
        this.hp = 20;
        this.attack = 3;
    }

    public Wolf() {
        super(0, 0, "🐺");
    }

    @Override
    public void makeMove(SimulationMap map, PathFinderService pathService) {
        Queue<Cell> path = pathService.findPathToHerbivore(getCell());
        if (path.isEmpty()) return;

        path.poll();
        Cell currentCell;
        for (int step = 0; step < speed; step++) {
            currentCell = getCell();
            Cell herbivoreOnCell = pathService.isHerbivoreNear(currentCell);

            if (herbivoreOnCell != null) {
                Creature creature = (Creature) map.getEntity(herbivoreOnCell);
                creature.setHp(creature.getHp() - attack);

                if (creature.canAttack()) this.hp -= ((Boar)creature).getAttack();

                if (creature.isAlive()) {
                    map.setEntity(new Entity(creature.getX(), creature.getY(), ""));
                }
                if (this.isAlive()){
                    map.setEntity(new Entity(this.getX(), this.getY(), ""));
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
        return true;
    }


}
