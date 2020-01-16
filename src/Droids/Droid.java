package Droids;

import Backpack.Armor.Armor;
import Backpack.Backpack;
import Backpack.Weapons.Weapon;
import Interfaces.Fighter;
import World.World;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Droid implements Fighter, Serializable {

    protected double[] hp = new double[2];
    protected int lvl = 1;
    protected int exp = 0;
    protected String name;
    protected int chanceShaking;
    protected int timeSkill = 0;
    protected String type;
    protected Backpack backpack = new Backpack();
    protected double temporarySkill;
    protected boolean isSkillUsed = false;
    protected int allTimeSkill;
    protected int timeUsingSkill;
    private final int[] levelsExp = {200, 800, 2300, 4000, 9000};
    protected boolean isEnemy = false;
    private int lvlsDone;

    protected Droid(String name)
    {
        this.name = name;
        backpack.addNew(new Armor("usual", lvl));
        isSkillUsed = false;
        lvlsDone = 0;
    }
    protected Droid(int lvl, String name)
    {
        this.name = name;
        this.lvl = lvl;
        backpack.addNew(new Armor("usual", lvl));
        isSkillUsed = false;
        lvlsDone = 0;
    }

    public int howMuchDone()
    {
        return lvlsDone;
    }

    public void setHowMuchDone(int lvl)
    {
        lvlsDone = lvl;
    }

    protected Droid(){}

    public void showChar()
    {
        System.out.println("-----------------------------------------------");
        System.out.println("Name: " + name);
        System.out.println("Type: " + type + " droid");
        System.out.println("Health: " + hp[0] + "/" + hp[1]);
        System.out.println("Armor: " + backpack.getArmors().get(backpack.getChoosedAmo()).amoPreview());
        System.out.println("Level: " + lvl);
        System.out.println("Experience to new level: " + exp + "/" + levelsExp[lvl - 1]);
        //System.out.println("Armor: " + amo. + "/" + amo.max);
        System.out.println("Chance of Shaking: " + chanceShaking + "%");
        System.out.println("-----------------------------------------------");

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void skill() {}

    private void upWeaponLvl()
    {
        backpack.getWeapons().get(backpack.getChoosedWeapon()).upWeapon(lvl, type);
    }

    public void addNew(Weapon weap)
    {
        backpack.addNew(weap);
        upWeaponLvl();
    }

    protected boolean isUpLvl()
    {
        final String RESET = "\033[0m";
        final String YELLOW = "\033[0;33m";

        if(exp > levelsExp[lvl - 1]) {
            lvl++;
            System.out.println(YELLOW + "You up LEVEL. Your lvl is " + lvl + RESET);
            try {
                Thread.sleep(1500);
            } catch (Exception e) {
                System.out.println(e);
            }
            return true;
        }
        return false;
    }

    public void upLvl() {}

    public void HPMinus(double hit)
    {
        double pureDamage = backpack.getArmors().get(backpack.getChoosedAmo()).protect(hit);
        hp[0] -= pureDamage;
    }

    public void steelHp(double quantity)
    {
        hp[0] -= quantity;
    }

    public int getLvl() {
        return lvl;
    }

    public double getHp(int i) {
        return hp[i];
    }

    public String getName() {
        return name;
    }

    public void battleChar(Fighter enemy)
    {
        System.out.println("Name: " + name + "\t\t\t\tName Enemy: " + enemy.getName());
        System.out.println("Type: " + type + "\t\t\t\t\tType Enemy: Enemy");
        System.out.println("Hp: " + hp[0] + "/" + hp[1] + "\t\t\t\tHp Enemy: " + enemy.getHp(0) + "/" + enemy.getHp(1));
        System.out.println("Armor: " + backpack.getArmors().get(backpack.getChoosedAmo()).amoPreview() + "\t\t\t\tArmor Enemy: " + enemy.getBackpack().getArmors().get(enemy.getBackpack().getChoosedAmo()).amoPreview());
        System.out.println("Damage: " + backpack.getWeapons().get(backpack.getChoosedWeapon()).getDamage() + "\t\t\t\tDamage Enemy: " + enemy.getBackpack().getWeapons().get(enemy.getBackpack().getChoosedWeapon()).getDamage());
    }

    public String[] retActions()
    {
        List<String> actions = new ArrayList<String>();
        actions.add("Attack");
        actions.add("Use skill");
        if(backpack.getWeapons().get(backpack.getChoosedWeapon()).isEnableSkill())
            actions.add("Use Weapon Skill");

        String[] strActions = new String[actions.size()];
        for(int i = 0; i < strActions.length; i++)
        {
            strActions[i] = actions.get(i);
        }

        return strActions;
    }

    public String getType() {
        return type;
    }

    public boolean isEnemy()
    {
        return isEnemy;
    }

    public boolean isChaked()
    {
        if(World.rand.nextInt(100) < chanceShaking)
        {
            System.out.println(World.ANSI_BLUE + "Miss!!!" + World.RESET);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
            }
            return true;
        }

        return false;
    }

    public void setFull()
    {
        hp[0] = hp[1];
        timeSkill = 0;
        isSkillUsed = false;
        backpack.getCurArmor().returnDefault();
        backpack.getCurWeapon().setFull();
        backpack.getCurWeapon().setDefault();
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public boolean isAlive()
    {
        return (hp[0] > 0);
    }

    public boolean useSkill()
    {
        return true;
    }

    public void setDefault()
    {    }

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
        backpack.getCurWeapon().oneTurn();
    }

    protected boolean isSkillContinue()
    {
        return timeSkill < timeUsingSkill && timeSkill != 0;
    }

    public void addExp(int exp)
    {
        this.exp += exp;
        upLvl();
    }

    public int getExp() {
        return exp;
    }

    public void nullShaking(String typeOpponent)
    {
        if(typeOpponent.equals("Boss"))
        {
            chanceShaking = -1;
        }
    }
    public void activeSkill(Fighter opponent){}
}



