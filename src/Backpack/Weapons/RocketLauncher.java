package Backpack.Weapons;

import World.World;

public class RocketLauncher extends Weapon {

    private double defSkill;

    public RocketLauncher(String color, int lvl)
    {
        super(color, lvl);
        type = "Rocket Launcher";
        switch(color)
        {
            case "usual":
                numShoot = 1;
                damage = 40;
                chanceCrit = 12;
                sizeCrit = 15;
                break;
            case "rare":
                numShoot = 1;
                damage = 45;
                chanceCrit = 18;
                sizeCrit = 19;
                break;
            case "epic":
                numShoot = 1;
                damage = 52;
                chanceCrit = 25;
                sizeCrit = 22;
                break;
            case "legendary":
                numShoot = 2;
                damage = 35;
                chanceCrit = 40;
                sizeCrit = 30;
                enableSkill = true;
                break;
        }
        super.upLvl();
    }

    private void setChar(String color)
    {
        switch(color)
        {
            case "usual":
                numShoot = 1;
                damage = 50;
                chanceCrit = 12;
                sizeCrit = 15;
                break;
            case "rare":
                numShoot = 1;
                damage = 57;
                chanceCrit = 18;
                sizeCrit = 19;
                break;
            case "epic":
                numShoot = 1;
                damage = 68;
                chanceCrit = 25;
                sizeCrit = 22;
                break;
            case "legendary":
                numShoot = 2;
                damage = 45;
                chanceCrit = 40;
                sizeCrit = 30;
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
            if (droidType.equals("Heavy")) {
                sizeCrit += lvl * 3;
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
    public void upForBoss()
    {
        chanceCrit = 60;
        sizeCrit = 45;
    }

    @Override
    public void setDefault()
    {
        super.setDefault();
        setChar(color);

        if(!super.isSkillContinue()) {
            super.setDefault();
            chanceCrit -= defSkill;
        }
    }

    @Override
    void showSkill()
    {
        System.out.println(World.YELLOW + "Add 40% to chance critical damage for a turn" + World.RESET);
    }

    @Override
    public boolean useSkill()
    {
        if(enableSkill) {
            if (timeSkill == 0) {
                System.out.println("Next hit you have upper chance critical damage for 40%!!!");
                defSkill = 40;
                chanceCrit += defSkill;
                timeSkill++;
                isSkillUsed = true;
            } else {
                System.out.println("You can't use weapon skill");
                System.out.println((allTimeSkill - timeSkill + 1) + " turns to it");
            }
        }
        return true;
    }
}

