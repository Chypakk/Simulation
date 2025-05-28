package org.chypakk.model.template;

import org.chypakk.model.Cell;
import org.chypakk.model.SimulationMap;
import org.chypakk.service.PathFinderService;

public abstract class Creature extends Entity {

    protected int hp;
    protected int speed;
    protected SimulationMap map;

    public Creature(int x, int y, String type) {
        super(x, y, type);
    }

    public abstract void makeMove(SimulationMap map, PathFinderService pathService);

    public boolean isAlive(){
        return hp <= 0;
    }

    public void setHp(int hp){
        this.hp = hp;
    }

    public int getHp(){
        return hp;
    }

    public abstract boolean canAttack();

    public Cell getCell() {
        return new Cell(getX(), getY());
    }
}
