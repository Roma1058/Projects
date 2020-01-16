package Backpack;

import Backpack.Armor.Armor;
import Backpack.Weapons.Weapon;
import World.World;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Backpack implements Serializable {
    protected List<Armor> armors = new ArrayList<>();
    protected List<Weapon> weapons = new ArrayList<>();
    protected int choosedWeapon = 0;
    protected int choosedAmo = 0;

    public List<Armor> getArmors() {
        return armors;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public Weapon getCurWeapon()
    {
        return weapons.get(choosedWeapon);
    }

    public Armor getCurArmor()
    {
        return armors.get(choosedAmo);
    }

    public int getChoosedWeapon() {
        return choosedWeapon;
    }

    public int getChoosedAmo() {
        return choosedAmo;
    }

    public void chooseElseWeapon()
    {
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_RESET = "\u001B[0m";

        System.out.println("You have:");
        String[] weapon = new String[weapons.size() + 1];

        for(int i = 0; i < weapons.size(); i++)
        {
            weapons.get(i).shortChar();
            weapon[i] = "Weapon " + (i+1);
        }

        weapon[weapons.size()] = "Return";

        int choose = World.menu(weapon);
        if(choose != weapons.size() + 1) {
            choosedWeapon = choose - 1;
            System.out.println(ANSI_YELLOW + "Successfully!!!" + ANSI_RESET);

            try {
                Thread.sleep(1500);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void chooseElseArmor()
    {
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_RESET = "\u001B[0m";

        System.out.println("You have:");
        String[] amos = new String[armors.size() + 1];

        for(int i = 0; i < armors.size(); i++)
        {
            armors.get(i).shortChar();
            amos[i] = "Armor " + (i+1);
        }

        amos[armors.size()] = "Return";

        int choose = World.menu(amos);
        if(choose != armors.size() + 1) {
            choosedAmo = choose - 1;
            System.out.println(ANSI_YELLOW + "Successfully!!!" + ANSI_RESET);

            try {
                Thread.sleep(1500);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void addNew(Weapon weap)
    {
        weapons.add(weap);
        choosedWeapon = weapons.size() - 1;
    }

    public void addNew(Armor amo)
    {
        armors.add(amo);
        choosedAmo = armors.size() - 1;
        armors.get(choosedAmo).setFull();
    }

    public void upLvlWeapon(int lvl, String type)
    {
        weapons.get(choosedWeapon).upWeapon(lvl, type);
    }

    public void showWeapon()
    {
       weapons.get(choosedWeapon).showChar();
    }

    public  void showArmour()
    {
        armors.get(choosedAmo).showChar();
    }
}
