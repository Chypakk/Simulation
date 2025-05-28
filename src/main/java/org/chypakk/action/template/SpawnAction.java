package org.chypakk.action.template;

import org.chypakk.model.template.Entity;
import org.chypakk.model.SimulationMap;

public abstract class SpawnAction extends Action {

    protected int spawnRate;

    public abstract void perform(SimulationMap map);
    protected abstract Entity spawnEntity(Entity emptyEntity);
}
