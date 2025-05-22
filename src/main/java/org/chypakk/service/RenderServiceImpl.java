package org.chypakk.service;

import org.chypakk.model.Cell;
import org.chypakk.model.Entity;
import org.chypakk.model.SimulationMap;

public class RenderServiceImpl implements RenderService {
    @Override
    public void render(SimulationMap map) {
        Cell cell;
        Entity entity;
        System.out.println(System.lineSeparator());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                cell = new Cell(x, y);
                entity = map.getEntity(cell);
                System.out.print(entity.getType().isEmpty() ? " . " : " " + entity.getType() + " ");
            }
            System.out.println(System.lineSeparator());
        }

    }
}
