package Droids.Enemies;

import Backpack.Armor.Armor;
import Backpack.Weapons.LaserSword;
import Droids.Droid;
import Interfaces.Fighter;

public class BossPalpatine extends Droid {

    public BossPalpatine()
    {
        name = "Palpatine";
        hp[1] = 250;
        hp[0] = hp[1];
        lvl = 2;
        exp = 0;
        chanceShaking = 35;
        type = "Boss";
        isEnemy = true;
        backpack.addNew(new LaserSword("legendary", 2));
        backpack.addNew(new Armor("legendary", 2));
    }

    @Override
    public void activeSkill(Fighter opponent)
    {
        opponent.nullShaking(type);
    }

}
