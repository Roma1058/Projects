package Droids.Enemies;

import Backpack.Armor.Armor;
import Backpack.Weapons.RocketLauncher;
import Droids.Droid;
import Interfaces.Fighter;

public class BossBlackManta extends Droid {
    public  BossBlackManta()
    {
        name = "Black Manta";
        hp[1] = 500;
        hp[0] = hp[1];
        lvl = 3;
        exp = 0;
        chanceShaking = 25;
        type = "Boss";
        isEnemy = true;
        backpack.addNew(new RocketLauncher("legendary", 3));
        backpack.addNew(new Armor("legendary", 3));
    }

    @Override
    public void activeSkill(Fighter opponent)
    {
        backpack.getCurWeapon().upForBoss();
    }

    @Override
    public void setDefault()
    {
        backpack.getCurWeapon().setDefault();
    }
}
