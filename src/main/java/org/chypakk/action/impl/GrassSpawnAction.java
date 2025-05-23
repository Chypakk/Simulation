package org.chypakk.action.impl;

import org.chypakk.action.template.SpawnAction;
import org.chypakk.model.Entity;
import org.chypakk.model.SimulationMap;
import org.chypakk.model.statics.Grass;

public class GrassSpawnAction extends SpawnAction {

    public GrassSpawnAction(int capacity){
        super.spawnRate = capacity / 5;
    }

    @Override
    public void perform(SimulationMap map) {
        int rate = map.getCapacityOfEntity(new Grass(0,0).getType());

        while (rate < spawnRate){
            Entity emptyEntity = map.getRandomEmptyEntity();
            map.setEntity(spawnEntity(emptyEntity));

            rate++;
        }
    }

    @Override
    protected Entity spawnEntity(Entity emptyEntity) {
        return new Grass(emptyEntity.getX(), emptyEntity.getY());
    }
}
