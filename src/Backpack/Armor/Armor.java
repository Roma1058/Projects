package Backpack.Armor;

import java.io.Serializable;

public class Armor implements Serializable {
    private int[] quantity = new int[2];
    private int lvl;
    private double takeDamage;
    private String color;
    private boolean enableSkill = false;
    private int timeToSkill = 0;
    private double defaultTaking;
    private boolean wasLowing;

    public Armor(String color, int lvl)
    {
        this.lvl = lvl;
        this.color = color;
        quantity[0] = 120 + ((lvl - 1) * 50);
        quantity[1] = quantity[0];
        setTakingDamage(color);

    }

    private void setTakingDamage(String color)
    {
        switch(color)
        {
            case "usual":
                takeDamage = lvl * 5;
                break;
            case "rare":
                takeDamage = lvl * 6;
                break;
            case "epic":
                takeDamage = lvl * 8;
                break;
            case "legendary":
                takeDamage = lvl * 10;
                enableSkill = true;
                timeToSkill = 4;
                break;
        }
    }

    public void showChar()
    {
        System.out.println("****************************");
        System.out.println("Quantity of armour: " + quantity[0] + "/" + quantity[1]);
        System.out.println("Quantity of taking damage: " + takeDamage + "%");
        System.out.println("Level of armor: " + lvl);
        showColor();
        if(color.equals("legendary"))
            showSkill();

        System.out.println("****************************");

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void showColor()
    {
        final String WHITE = "\033[0;37m";
        final String RESET = "\033[0m";
        final String BLUE = "\033[0;36m";
        final String PURPLE = "\033[0;35m";
        final String YELLOW = "\033[0;33m";

        switch(color)
        {
            case "usual":
                System.out.println("Color: " + WHITE + "usual" + RESET);
                break;
            case "rare":
                System.out.println("Color: " + BLUE + "rare" + RESET);
                break;
            case "epic":
                System.out.println("Color: " + PURPLE + "epic" + RESET);
                break;
            case "legendary":
                System.out.println("Color: " + YELLOW + "legendary" + RESET);
                break;
        }
    }

    void skill()
    {
        double ignore = lvl*30;
    }

    public String amoPreview()
    {
        return (quantity[0] + "/" + quantity[1]);
    }

    private void showSkill()
    {}

    public boolean isEnableSkill()
    {
        return  enableSkill;
    }

    public void shortChar()
    {
        System.out.println("Armor:");
        System.out.println("Quantity: " + quantity[1] + " - " + lvl + " lvl");
        showColor();
        System.out.println("-------------");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setFull()
    {
        quantity[0] = quantity[1];
    }

    public double getTakeDamage() {
        return takeDamage;
    }

    public double protect(double allDamage)
    {
        double getForArmor = 0;
        double pureDamage = 0;

        getForArmor = allDamage * (takeDamage / 100);

        if(getForArmor < quantity[0]) {
            pureDamage = allDamage - getForArmor;
            quantity[0] -= getForArmor;
        }
        else {
            pureDamage = allDamage - quantity[0];
            quantity[0] = 0;
        }

        return pureDamage;
    }

    public void fullReceive()
    {
        defaultTaking = takeDamage;
        takeDamage = 100;
    }

    public void returnDefault()
    {
        if(wasLowing) {
            lvl++;
            setTakingDamage(color);
            wasLowing = false;
        }
    }

    public void zeroReceive()
    {
        defaultTaking = takeDamage;
        takeDamage = 0;
    }

    public void lowLvl()
    {
        lvl--;
        setTakingDamage(color);
        wasLowing = true;
    }
}
