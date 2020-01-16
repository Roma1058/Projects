package Droids.Heroes;

import Droids.Droid;
import Backpack.Weapons.RocketLauncher;

public class HeavyDroid extends Droid {

    private static final int startShaking = 7;
    private static final int startHp = 575;

    public HeavyDroid(String name)
    {
        super(name);
        hp[0] = startHp;
        hp[1] = hp[0];
        chanceShaking = 7;
        backpack.getWeapons().add(new RocketLauncher("usual", 1));
        type = "Heavy";
        allTimeSkill = 6;
        timeUsingSkill = 2;
    }

    @Override
    public boolean useSkill()
    {
        if(timeSkill == 0) {
            System.out.println("Next 2 turns hits to you will be reduced for 50%!!!");
            temporarySkill = 0.5;
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
    public void upLvl()
    {
        if(super.isUpLvl())
        {
            hp[1] = startHp + (lvl * 190);
            hp[0] = hp[1];
            chanceShaking = (lvl * 2) + startShaking;
            backpack.upLvlWeapon(lvl, type);
        }
    }

    @Override
    public void HPMinus(double hit)
    {
        if(super.isSkillUsed)
        {
            hit = hit * temporarySkill;
        }
        double pureDamage = backpack.getArmors().get(backpack.getChoosedAmo()).protect(hit);
        hp[0] -= pureDamage;
    }

    @Override
    public void setDefault()
    {
        if(!super.isSkillContinue()) {
            temporarySkill = 1;
        }
    }

    @Override
    public void setFull()
    {
        super.setFull();
        chanceShaking = (lvl * 6) + startShaking;
    }

}

