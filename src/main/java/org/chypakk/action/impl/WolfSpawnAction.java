package org.chypakk.action.impl;

import org.chypakk.action.template.SpawnAction;
import org.chypakk.model.template.Entity;
import org.chypakk.model.SimulationMap;
import org.chypakk.model.dynamics.Wolf;

public class WolfSpawnAction extends SpawnAction {

    public WolfSpawnAction(int capacity){
        super.spawnRate = capacity / 20;
    }

    @Override
    public void perform(SimulationMap map) {
        int rate = map.getCapacityOfEntity(new Wolf().getType());

        while (rate < spawnRate){
            Entity emptyEntity = map.getRandomEmptyEntity();
            map.setEntity(spawnEntity(emptyEntity));

            rate++;
        }
    }

    @Override
    protected Entity spawnEntity(Entity emptyEntity) {
        return new Wolf(emptyEntity.getX(), emptyEntity.getY());
    }
}
