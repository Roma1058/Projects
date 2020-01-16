package Droids.Enemies;

import Backpack.Armor.Armor;
import Backpack.Weapons.Gun;
import Droids.Droid;
import Interfaces.Fighter;

public class BossLowering3000 extends Droid {

    public  BossLowering3000()
    {
        name = "Lowering3000";
        hp[1] = 600;
        hp[0] = hp[1];
        lvl = 4;
        exp = 0;
        chanceShaking = 25;
        type = "Boss";
        isEnemy = true;
        backpack.addNew(new Gun("legendary", 4));
        backpack.addNew(new Armor("legendary", 4));
    }

    @Override
    public void activeSkill(Fighter opponent)
    {
        opponent.getBackpack().getCurWeapon().lowLvl();
        opponent.getBackpack().getCurArmor().lowLvl();
    }
}
