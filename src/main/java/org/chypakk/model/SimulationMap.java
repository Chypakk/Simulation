package org.chypakk.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class SimulationMap {
    private Map<Cell, Entity> simulationMap = new HashMap<>();

    private int width;
    private int height;

    public SimulationMap(int width, int height) {
        this.width = width;
        this.height = height;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
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

    public Entity getRandomEmptyCell(){
        Random random = new Random();
        while (true){
            Entity entity = getEntity(new Cell(
                    random.nextInt(width-1),
                    random.nextInt(height-1)
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
