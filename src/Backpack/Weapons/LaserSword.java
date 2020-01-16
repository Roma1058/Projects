package Backpack.Weapons;

import World.World;

public class LaserSword extends Weapon{

    private double defSkill;

    public LaserSword(String color, int lvl)
    {
        super(color, lvl);
        numShoot = 1;
        type = "Laser Sword";
        switch(color)
        {
            case "usual":
                damage = 45;
                chanceCrit = 10;
                sizeCrit = 15;
                break;
            case "rare":
                damage = 52;
                chanceCrit = 12;
                sizeCrit = 20;
                break;
            case "epic":
                damage = 60;
                chanceCrit = 17;
                sizeCrit = 24;
                break;
            case "legendary":
                damage = 80;
                chanceCrit = 25;
                sizeCrit = 40;
                timeUsingSkill = 1;
                allTimeSkill = 4;
                enableSkill = true;
                break;
        }
    }

    @Override
    public void upWeapon(int droidLvl,  String droidType)
    {
        if(firstUp) {
            if (droidType.equals("Light")) {
                damage += lvl * 7;
                firstUp = false;
            }
        }
    }

    @Override
    public void showChar()
    {
        super.showChar();
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    void showSkill()
    {
        System.out.println(World.YELLOW + "Add 35% of damage for a turn" + World.RESET);
    }

    @Override
    public boolean useSkill()
    {
        if(enableSkill) {
            if (timeSkill == 0) {
                System.out.println("Next hit you have upper damage for 35%!!!");
                defSkill = damage * 0.35;
                damage += defSkill;
                timeSkill++;
                isSkillUsed = true;
            } else {
                System.out.println("You can't use weapon skill");
                System.out.println((allTimeSkill - timeSkill + 1) + " turns to it");
            }
        }
        return true;
    }

    @Override
    public void setDefault()
    {
        if(!super.isSkillContinue()) {
            super.setDefault();
            damage -= defSkill;
        }
    }

}
