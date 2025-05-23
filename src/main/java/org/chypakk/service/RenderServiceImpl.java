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
        for (int y = 0; y < map.getWidth(); y++) {
            for (int x = 0; x < map.getHeight(); x++) {
                cell = new Cell(x, y);
                entity = map.getEntity(cell);
//                System.out.printf("\t[%d]\t%s\t[%d]\t", x, entity.getType().isEmpty() ? "." : entity.getType(), y);
                System.out.print(entity.getType().isEmpty() ? "\t.\t" : "\t" + entity.getType() + "\t");
            }
            System.out.println(System.lineSeparator());
        }

    }
}
