package Droids.Enemies;

import Backpack.Armor.Armor;
import Backpack.Weapons.Gun;
import Droids.Droid;
import Interfaces.Fighter;

public class BossNemesis extends Droid {

    public  BossNemesis()
    {
        name = "Nemesis";
        hp[1] = 250;
        hp[0] = hp[1];
        lvl = 1;
        exp = 0;
        chanceShaking = 20;
        type = "Boss";
        isEnemy = true;
        backpack.addNew(new Gun("legendary", 1));
        backpack.addNew(new Armor("legendary", 1));
    }

    @Override
    public void activeSkill(Fighter opponent)
    {
        opponent.getBackpack().getCurArmor().zeroReceive();
    }
}
