package org.chypakk.model.template;

public class Food extends Entity {

    private final int hpHealed;

    public Food(int x, int y, String type, int hpHealed) {
        super(x, y, type);
        this.hpHealed = hpHealed;
    }

    public int getHpHealed() {
        return hpHealed;
    }
}
