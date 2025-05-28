package org.chypakk.action.impl;

import org.chypakk.action.template.SpawnAction;
import org.chypakk.model.template.Entity;
import org.chypakk.model.SimulationMap;
import org.chypakk.model.dynamics.Sheep;

public class SheepSpawnAction extends SpawnAction {

    public SheepSpawnAction(int capacity){
        super.spawnRate = capacity / 10;
    }

    @Override
    public void perform(SimulationMap map) {
        int rate = map.getCapacityOfEntity(new Sheep().getType());

        while (rate < spawnRate){
            Entity emptyEntity = map.getRandomEmptyEntity();
            map.setEntity(spawnEntity(emptyEntity));

            rate++;
        }
    }

    @Override
    protected Entity spawnEntity(Entity emptyEntity) {
        return new Sheep(emptyEntity.getX(), emptyEntity.getY());
    }
}
