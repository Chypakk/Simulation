package org.chypakk.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SimulationMap {
    private final Map<Cell, Entity> simulationMap = new HashMap<>();

    private final int width;
    private final int height;

    public SimulationMap(int width, int height) {
        this.width = width;
        this.height = height;

        for (int y = 0; y < width; y++) {
            for (int x = 0; x < height; x++) {
                simulationMap.put(new Cell(x, y), new Entity(x, y, ""));
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Entity getEntity(Cell cell){
        return simulationMap.get(cell);
    }

    public void setEntity(Entity newEntity){
        simulationMap.replace(new Cell(newEntity.getX(), newEntity.getY()), newEntity);
    }

    public void setEntityToCell(Cell cell, Entity entity){
        entity.setCell(cell);
        simulationMap.replace(cell, entity);
    }

    public Entity getRandomEmptyEntity(){
        Random random = new Random();
        while (true){
            Entity entity = getEntity(new Cell(
                    random.nextInt(width),
                    random.nextInt(height)
            ));
            if (entity.getType().isEmpty()) return entity;
        }
    }

    public int getCapacityOfEntity(String type){
        int count = 0;
        for (var entity : simulationMap.values()){
            if (entity.getType().equals(type)) count++;
        }
        return count;
    }

    public int getCapacity(){
        return width * height;
    }
}
