package org.chypakk.action.impl;

import org.chypakk.action.template.SpawnAction;
import org.chypakk.model.Entity;
import org.chypakk.model.SimulationMap;
import org.chypakk.model.dynamics.Predator;

public class PredatorSpawnAction extends SpawnAction {

    public PredatorSpawnAction(int capacity){
        super.spawnRate = capacity / 20;
    }

    @Override
    public void perform(SimulationMap map) {
        int rate = map.getCapacityOfEntity(new Predator().getType());

        while (rate < spawnRate){
            Entity emptyEntity = map.getRandomEmptyEntity();
            map.setEntity(spawnEntity(emptyEntity));

            rate++;
        }
    }

    @Override
    protected Entity spawnEntity(Entity emptyEntity) {
        return new Predator(emptyEntity.getX(), emptyEntity.getY());
    }
}
