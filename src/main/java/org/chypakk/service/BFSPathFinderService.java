package org.chypakk.service;

import org.chypakk.model.Cell;
import org.chypakk.model.SimulationMap;
import org.chypakk.model.dynamics.Herbivore;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BFSPathFinderService implements PathFinderService {

    private final SimulationMap map;

    public BFSPathFinderService(SimulationMap map) {
        this.map = map;
    }

    @Override
    public Cell isHerbivoreNear(Cell cell) {
        return cellsAround(cell).stream()
                .filter(herb -> map.getEntity(herb) != null && map.getEntity(herb).getClass() == Herbivore.class)
                .findFirst().orElse(null);
    }

    private List<Cell> emptyCellsNear(Cell cell) {
        return cellsAround(cell).stream()
                .filter(entity -> map.getEntity(entity) != null && map.getEntity(entity).getType().isEmpty()).toList();
    }

    @Override
    public Queue<Cell> findPathToHerbivore(Cell start) {
        Map<Cell, Cell> parentMap = new HashMap<>(); // Для восстановления пути
        Queue<Cell> queue = new LinkedList<>();
        Set<Cell> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);
        Cell target = null;

        // BFS с использованием вашего метода emptyCellsNear()
        while (!queue.isEmpty() && target == null) {
            Cell current = queue.poll();

            // Получаем все доступные соседние клетки
            List<Cell> neighbors = emptyCellsNear(current);

            for (Cell neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    parentMap.put(neighbor, current);
                    visited.add(neighbor);
                    queue.add(neighbor);

                    // Проверяем, есть ли рядом с соседом цель
                    if (isHerbivoreNear(neighbor) != null) {
                        target = neighbor;
                        break;
                    }
                }
            }
        }

        // Восстанавливаем путь от цели к старту
        LinkedList<Cell> path = new LinkedList<>();
        if (target != null) {
            Cell current = target;
            while (current != null) {
                path.addFirst(current);
                current = parentMap.get(current);
            }
        }

        return new ArrayDeque<>(path);

//        Deque<Cell> path = new ArrayDeque<>();
//        Queue<Cell> toVisit = new ArrayDeque<>(emptyCellsNear(cell));
//
//        boolean found = false;
//
//        while (!toVisit.isEmpty()){
//            Cell visiting = toVisit.poll();
//            path.add(visiting);
//
//            if (isHerbivoreNear(visiting) != null){
//                found = true;
//                break;
//            }
//            if (!toVisit.addAll(emptyCellsNear(visiting).stream().filter(c -> !path.contains(c)).toList())) break;
//        }
//        if (!found){
//            path.addFirst(cell);
//        }
//        return path;
    }

    private Set<Cell> cellsAround(Cell cell) {
        return Stream.of(
                new Cell(cell.getX() - 1, cell.getY() + 1),
                new Cell(cell.getX(), cell.getY() + 1),
                new Cell(cell.getX() + 1, cell.getY() + 1),

                new Cell(cell.getX() - 1, cell.getY()),
                new Cell(cell.getX() + 1, cell.getY()),

                new Cell(cell.getX() - 1, cell.getY() - 1),
                new Cell(cell.getX(), cell.getY() - 1),
                new Cell(cell.getX() + 1, cell.getY() - 1)
        ).collect(Collectors.toCollection(HashSet::new));
    }
}
