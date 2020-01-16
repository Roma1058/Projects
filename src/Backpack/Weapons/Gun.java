package Backpack.Weapons;

import World.World;

public class Gun extends Weapon {

    public Gun(String color, int lvl)
    {
        super(color, lvl);
        type = "Gun";
        switch(color)
        {
            case "usual":
                numShoot = 2;
                damage = 17;
                chanceCrit = 7;
                sizeCrit = 10;
                break;
            case "rare":
                numShoot = 2;
                damage = 25;
                chanceCrit = 10;
                sizeCrit = 13;
                break;
            case "epic":
                numShoot = 3;
                damage = 22;
                chanceCrit = 10;
                sizeCrit = 15;
                break;
            case "legendary":
                numShoot = 4;
                damage = 21;
                chanceCrit = 20;
                sizeCrit = 10;
                allTimeSkill = 4;
                timeUsingSkill = 1;
                enableSkill = true;
                break;
        }
    }

    @Override
    public void upWeapon(int droidLvl, String droidType)
    {
        if(firstUp) {
            if (droidType.equals("Medium")) {
                if (droidLvl == 3 || droidLvl == 5) {
                    numShoot++;
                }
                firstUp = false;
            }
        }
    }

    @Override
    public void showChar()
    {
        super.showChar();
        System.out.println("Number of shoots: " + numShoot);
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
        System.out.println(World.YELLOW + "Add 1 shoot for a turn" + World.RESET);
    }

    @Override
    public boolean useSkill()
    {
        if(enableSkill) {
            if (timeSkill == 0) {
                System.out.println("Next hit you have one more bullet!!!");
                numShoot++;
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
            numShoot--;
        }
    }
}
