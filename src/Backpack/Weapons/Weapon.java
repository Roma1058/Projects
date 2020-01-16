package Backpack.Weapons;

import Interfaces.Fighter;
import Interfaces.Thing;
import World.World;

import java.io.Serializable;

public abstract class Weapon implements Thing, Serializable {
    protected double damage;
    protected double chanceCrit;
    protected double sizeCrit;
    protected String type;
    protected String color;
    protected boolean enableSkill;
    protected int lvl;
    protected int numShoot;
    protected boolean firstUp;
    private boolean wasLowing;
    protected int allTimeSkill;
    protected int timeSkill;
    protected boolean isSkillUsed;
    protected int timeUsingSkill;

    public Weapon(String color, int lvl)
    {
        this.lvl = lvl;
        enableSkill = false;
        this.color = color;
        upLvl();
        firstUp = true;
        timeSkill = 0;
        isSkillUsed = false;
    }

    void upLvl()
    {
        switch (lvl)
        {
            case 1:
                break;
            case 2:
                damage += damage*0.2;
                break;
            case 3:
                damage += damage*0.31;
                break;
            case 4:
                damage += damage*0.42;
                break;
            case 5:
                damage += damage*0.5;
                break;
        }
    }

    void lowDamage()
    {
        switch (lvl)
        {
            case 1:
                break;
            case 2:
                damage -= damage*0.2;
                break;
            case 3:
                damage -= damage*0.31;
                break;
            case 4:
                damage -= damage*0.42;
                break;
            case 5:
                damage -= damage*0.5;
                break;
        }
    }

    public void upWeapon(int droidLvl, String droidType)
    { }

    public void showChar()
    {
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("Type: " + type);
        System.out.println("Level: " + lvl);
        showColor();
        System.out.println("Damage: " + damage);
        System.out.println("Chance of critical strike: " + chanceCrit + "%");
        System.out.println("Size of critical strike: " + sizeCrit + "%");
        if(enableSkill)
            showSkill();

    }

    public void shortChar()
    {
        System.out.println(type + " - " + lvl + " lvl" );
        showColor();
        System.out.println("-------------");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void showColor()
    {
        switch(color)
        {
            case "usual":
                System.out.println("Color: " + World.WHITE + "usual" +  World.RESET);
                break;
            case "rare":
                System.out.println("Color: " +  World.BLUE + "rare" +  World.RESET);
                break;
            case "epic":
                System.out.println("Color: " +  World.PURPLE + "epic" +  World.RESET);
                break;
            case "legendary":
                System.out.println("Color: " +  World.YELLOW + "legendary" +  World.RESET);
                break;
        }
    }

    void showSkill()
    { }

    public double getDamage() {
        return damage;
    }

    public boolean isEnableSkill()
    {
        return enableSkill;
    }

    public double hit(Fighter droid)
    {
        int countHits = 0;
        double realDamage;
        double allDamage = 0;
        do {
            realDamage = damage;

            if(!droid.isChaked()) {
                if (World.rand.nextInt(100) < chanceCrit) {
                    realDamage += damage * (sizeCrit / 100.0);
                    System.out.println( World.ANSI_RED + "Critical damage!");
                }
                System.out.println("Hit at " + realDamage + World.RESET);

                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    System.out.println(e);
                }

                allDamage += realDamage;
            }
            countHits++;
        }while(countHits < this.numShoot);
        System.out.println("-------------------------------------------");

        return allDamage;
    }

    public void upForBoss(){}

    public void setDefault(){
        if(wasLowing) {
            lvl++;
            upLvl();
            wasLowing = false;
        }
    }

    public void setFull()
    {
        timeSkill = 0;
        isSkillUsed = false;
    }

    public void lowLvl()
    {
        lvl--;
        lowDamage();
        wasLowing = true;
    }

    public boolean useSkill()
    {
        return true;
    }

    protected boolean isSkillContinue()
    {
        return timeSkill < timeUsingSkill && timeSkill != 0;
    }

    public void oneTurn()
    {
        if(isSkillUsed) {
            if (timeSkill != allTimeSkill && timeSkill != 0) {
                if(timeSkill == timeUsingSkill)
                    setDefault();
                timeSkill++;
            } else {
                timeSkill = 0;
                isSkillUsed = false;
            }
        }
    }
}
