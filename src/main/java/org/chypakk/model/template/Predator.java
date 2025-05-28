package org.chypakk.model.template;

public abstract class Predator extends Creature{

    protected int attack;

    public Predator(int x, int y, String type) {
        super(x, y, type);
    }

    @Override
    public boolean canAttack() {
        return true;
    }

    public int getAttack() {
        return attack;
    }
}
