package Droids.Enemies;
        import Backpack.Armor.Armor;
        import Droids.Droid;
        import Backpack.Weapons.Gun;
        import Backpack.Weapons.LaserSword;
        import Backpack.Weapons.RocketLauncher;
        import World.World;

public class Enemy1 extends Droid{

    public Enemy1 (int lvl)
    {
        super(lvl, "Enemy");
        hp[1] = lvl * 250;
        hp[0] = hp[1];
        exp = lvl * 60;
        chooseWeapon();
        chooseArmor();
        isEnemy = true;
        type = "Enemy";
        chanceShaking = 5 * lvl;
    }

    private String chColor()
    {
        int[] probWeap = new int[3];
        String color;

        probWeap[2] = 99 - (5 + ((lvl-1) * 5));
        probWeap[1] =  probWeap[2] - (15 +((lvl-1) * 6));
        probWeap[0] = probWeap[1] - (30 - (lvl - 1));


        int randNum = World.rand.nextInt(0,99);
        if(randNum <= probWeap[0])
            color = "usual";
        else if(randNum <= probWeap[1] && randNum > probWeap[0])
            color = "rare";
        else if(randNum <= probWeap[2] && randNum > probWeap[1])
            color = "epic";
        else if(randNum <= 99 && randNum > probWeap[2])
            color = "legendary";
        else
            color = "error";

        return color;
    }

    private void chooseWeapon()
    {
        switch (World.rand.nextInt(0,3))
        {
            case 0:
                backpack.getWeapons().add(new LaserSword(chColor(), this.lvl));
                break;
            case 1:
                backpack.getWeapons().add(new Gun(chColor(), this.lvl));
                break;
            case 2:
                backpack.getWeapons().add(new RocketLauncher(chColor(), this.lvl));
                break;
        }

    }

    private void chooseArmor()
    {
        backpack.getArmors().add(new Armor(chColor(), this.lvl));
    }

}
