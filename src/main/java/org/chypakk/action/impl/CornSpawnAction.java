package org.chypakk.action.impl;

import org.chypakk.action.template.SpawnAction;
import org.chypakk.model.template.Entity;
import org.chypakk.model.SimulationMap;
import org.chypakk.model.statics.Corn;

public class CornSpawnAction extends SpawnAction {

    public CornSpawnAction(int capacity){
        super.spawnRate = capacity / 15;
    }

    @Override
    public void perform(SimulationMap map) {
        int rate = map.getCapacityOfEntity(new Corn().getType());

        while (rate < spawnRate){
            Entity emptyEntity = map.getRandomEmptyEntity();
            map.setEntity(spawnEntity(emptyEntity));

            rate++;
        }
    }

    @Override
    protected Entity spawnEntity(Entity emptyEntity) {
        return new Corn(emptyEntity.getX(), emptyEntity.getY());
    }
}
