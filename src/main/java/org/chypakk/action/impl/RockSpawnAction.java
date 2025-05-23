package org.chypakk.action.impl;

import org.chypakk.action.template.SpawnAction;
import org.chypakk.model.Entity;
import org.chypakk.model.SimulationMap;
import org.chypakk.model.statics.Rock;

public class RockSpawnAction extends SpawnAction {

    public RockSpawnAction(int capacity) {
        super.spawnRate = capacity / 15;
    }

    @Override
    public void perform(SimulationMap map) {
        int rate = map.getCapacityOfEntity(new Rock(0, 0).getType());

        while (rate < spawnRate) {
            Entity emptyEntity = map.getRandomEmptyEntity();
            map.setEntity(spawnEntity(emptyEntity));

            rate++;
        }
    }

    @Override
    protected Entity spawnEntity(Entity emptyEntity) {
        return new Rock(emptyEntity.getX(), emptyEntity.getY());
    }
}
