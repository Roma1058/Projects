package Droids.Heroes;

import Droids.Droid;
import Backpack.Weapons.LaserSword;

public class LightDroid extends Droid {

    private static final int startShaking = 20;
    private static final int startHp = 350;

    public LightDroid(String name)
    {
        super(name);
        hp[1] = startHp;
        hp[0] = hp[1];
        chanceShaking = 20;
        backpack.getWeapons().add(new LaserSword("usual", 1));
        type = "Light";
        allTimeSkill = 4;
        timeUsingSkill = 1;
    }

    @Override
    public boolean useSkill()
    {
        if(timeSkill == 0) {
            System.out.println("Next hit to you will be miss!!!");
            temporarySkill = chanceShaking;
            chanceShaking = 100;
            isSkillUsed = true;
            timeSkill++;
        }
        else {
            System.out.println("You can't use skill");
            System.out.println((allTimeSkill - timeSkill + 1) + " turns to it");
        }
        return true;
    }

    @Override
    public void setDefault()
    {
        if(!super.isSkillContinue())
        {
            chanceShaking = (int)temporarySkill;
        }
    }

    @Override
    public void upLvl()
    {
        if(super.isUpLvl())
        {
            hp[1] = startHp + (lvl * 100);
            hp[0] = hp[1];
            chanceShaking = (lvl * 6) + startShaking;
            backpack.upLvlWeapon(lvl, type);
        }
    }

    @Override
    public void setFull()
    {
        super.setFull();
        chanceShaking = (lvl * 6) + startShaking;
    }



}
