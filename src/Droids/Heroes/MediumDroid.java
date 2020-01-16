package Droids.Heroes;

import Droids.Droid;
import Backpack.Weapons.Gun;

public class MediumDroid extends Droid {

    private static final int startShaking = 12;
    private static final int startHp = 450;

    public MediumDroid(String name)
    {
        super(name);
        hp[0] = startHp;
        hp[1] = hp[0];
        chanceShaking = 12;
        backpack.getWeapons().add(new Gun("usual", 1));
        type = "Medium";
        allTimeSkill = 5;
        timeUsingSkill = 2;
    }

    @Override
    public void upLvl()
    {
        if(super.isUpLvl())
        {
            hp[1] = startHp + (lvl * 140);
            hp[0] = hp[1];
            chanceShaking = (lvl * 3) + startShaking;
            backpack.upLvlWeapon(lvl, type);
        }
    }

    @Override
    public boolean useSkill()
    {
        if(timeSkill == 0) {
            System.out.println("Next 2 turns all damage will be redirected to armor!!!");
            backpack.getCurArmor().fullReceive();
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
        if(!super.isSkillContinue()) {
            backpack.getCurArmor().returnDefault();
        }
    }

    @Override
    public void setFull()
    {
        super.setFull();
        chanceShaking = (lvl * 6) + startShaking;
    }
}
