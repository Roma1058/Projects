package Droids.Enemies;

import Backpack.Armor.Armor;
import Backpack.Weapons.LaserSword;
import Droids.Droid;
import Interfaces.Fighter;
import World.World;

public class BossLifestealer extends Droid {

    public BossLifestealer()
    {
        name = "Lifestealer";
        hp[1] = 400;
        hp[0] = hp[1];
        lvl = 1;
        exp = 0;
        chanceShaking = 20;
        type = "Boss";
        isEnemy = true;
        backpack.addNew(new LaserSword("legendary", 5));
        backpack.addNew(new Armor("legendary", 5));
    }

    @Override
    public void activeSkill(Fighter opponent)
    {
        double steel = opponent.getHp(0) * 0.05;
        hp[0] += steel;
        opponent.steelHp(steel);
        System.out.println(World.ANSI_RED + "Steel " + steel + " hp" + World.RESET);
    }
}
