package org.chypakk.model.statics;

import org.chypakk.model.template.Food;

public class Corn extends Food {
    public Corn(int x, int y) {
        super(x, y, "🌽", 2);
    }
    public Corn() {
        super(0, 0, "🌽", 2);
    }
}
