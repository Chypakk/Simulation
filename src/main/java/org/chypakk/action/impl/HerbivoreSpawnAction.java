package org.chypakk.action.impl;

import org.chypakk.action.template.SpawnAction;
import org.chypakk.model.Entity;
import org.chypakk.model.SimulationMap;
import org.chypakk.model.dynamics.Herbivore;

public class HerbivoreSpawnAction extends SpawnAction {

    public HerbivoreSpawnAction(int capacity){
        super.spawnRate = capacity / 10;
    }

    @Override
    public void perform(SimulationMap map) {
        int rate = map.getCapacityOfEntity(new Herbivore().getType());

        while (rate < spawnRate){
            Entity emptyEntity = map.getRandomEmptyEntity();
            map.setEntity(spawnEntity(emptyEntity));

            rate++;
        }
    }

    @Override
    protected Entity spawnEntity(Entity emptyEntity) {
        return new Herbivore(emptyEntity.getX(), emptyEntity.getY());
    }
}
