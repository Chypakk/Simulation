package org.chypakk.service;

import org.chypakk.model.Cell;

import java.util.Queue;

public interface PathFinderService {

    public Cell isHerbivoreNear(Cell cell);
    public Cell isFoodNear(Cell cell);
    public Queue<Cell> findPathToFood(Cell cell);
    public Queue<Cell> findPathToHerbivore(Cell cell);
}
