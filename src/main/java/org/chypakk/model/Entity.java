package org.chypakk.model;

public class Entity {
    protected String type;
    private int x;
    private int y;

    public Entity(int x, int y, String type){
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public Entity(Entity entity){
        this.x = entity.getX();
        this.y = entity.getY();
        this.type = entity.getType();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getType(){
        return type;
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCell(Cell cell) {
        this.x = cell.getX();
        this.y = cell.getY();
    }
}
