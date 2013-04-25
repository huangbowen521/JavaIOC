package thoughtworks.com.util.model;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 4/26/13
 * Time: 12:55 AM
 */
public class Warrior {

    @Inject
    public Warrior(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    private Weapon weapon;
}
