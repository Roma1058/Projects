package World;

import Droids.Droid;
import Droids.Enemies.*;
import Interfaces.Fighter;

public class Level {
    private static Droid myDroid;
    private static int lvl;
    private Enemy1[] enemies;
    private Droid[] bosses = new Droid[5];

    public Level(int lvl, Droid myDroid)
    {
        Level.lvl = lvl;
        Level.myDroid = myDroid;
        bosses[0] = new BossNemesis();
        bosses[1] = new BossPalpatine();
        bosses[2] = new BossBlackManta();
        bosses[3] = new BossLowering3000();
        bosses[4] = new BossLifestealer();
    }

    public int playLevel()
    {
        boolean isReturn = true;
        String[] abilities = {"Show my characteristic" , "Show my weapon", "Show my armor", "Attack enemies", "Change weapon", "Change armor", "Return"};
        do{
            switch (World.menu(abilities))
            {
                case 1:
                    myDroid.showChar();
                    break;
                case 2:
                    myDroid.getBackpack().showWeapon();
                    break;
                case 3:
                    myDroid.getBackpack().showArmour();
                    break;
                case 4:
                    int reset = attackEnemies();
                    if(reset == 0)
                        isReturn = true;
                    else if(reset >= 10)
                        return reset;
                    break;
                case 5:
                    myDroid.getBackpack().chooseElseWeapon();
                    break;
                case 6:
                    myDroid.getBackpack().chooseElseArmor();
                    break;
                case 7:
                    return 0;
            }
        }while(isReturn);
        return 1;
    }


    private int attackEnemies()
    {
        enemies = retEmemies();
        String[] enemyStr = new String[enemies.length + 2];
        for (int i = 0; i < enemies.length; i++) {
            enemyStr[i] = "Enemy " + (i+1) + " ^ " + enemies[i].getLvl() + " lvl";
        }
        enemyStr[enemies.length ] = "Boss";
        enemyStr[enemies.length + 1] = "Return";
        boolean[] enabledEn = {true, true, true, true, true, false, true};

        if(myDroid.getLvl() > bosses[lvl -1].getLvl())
            enabledEn[enabledEn.length - 2] = true;

        int choose = World.menu(enemyStr, enabledEn);

        if(choose == enabledEn.length)
            return 0;

        if(choose == enabledEn.length - 1)
            if(battle(bosses[lvl - 1]))
            {
                System.out.println(World.ANSI_RED + "Congratulations! You kill " + lvl + " boss - " + bosses[lvl - 1].getName() + "!!!" + World.RESET);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e);
                }

                System.out.println(World.YELLOW + "Now enable lvl " + (lvl + 1) + World.RESET);

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e);
                }

                bosses[lvl - 1].setDefault();
                loot(bosses[lvl - 1]);

                return (10 + bosses[lvl - 1].getLvl());
            }
            else
                return 100;

        if(choose < enabledEn.length - 1) {
            if (battle(enemies[choose - 1])) {

                    myDroid.addExp(enemies[choose - 1].getExp());
                    loot(enemies[choose - 1]);

                    enemies[choose - 1] = new Enemy1(enemies[choose - 1].getLvl());
                }
            }

        return 0;
    }

    private void loot(Fighter beaten)
    {
        boolean isReturn = false;
        boolean nothing = false;

        myDroid.setFull();
        beaten.getBackpack().getCurWeapon().shortChar();
        beaten.getBackpack().getCurArmor().shortChar();

        do{
            System.out.println("Do you want to take one?");
            String[] picking = {"Weapon", "Armor", "Nothing"};
            switch (World.menu(picking)) {
                case 1:
                    myDroid.addNew(beaten.getBackpack().getCurWeapon());
                    break;
                case 2:
                    myDroid.getBackpack().addNew(beaten.getBackpack().getCurArmor());
                    break;
                case 3:
                    nothing = true;
                    break;
            }
            if(!nothing) {
                System.out.println("One more?");
                String[] isRet = {"Yes", "No"};
                isReturn = World.menu(isRet) == 1;
            }
        }while(isReturn);
    }

    private Enemy1[] retEmemies()
    {
        Enemy1[] enemies = new Enemy1[5];
        for(int i = 0; i < enemies.length; i++)
        {
            if(i < 3)
                enemies[i] = new Enemy1(lvl);
            else
                enemies[i] = new Enemy1(World.rand.nextInt(1,5));
        }
        return enemies;
    }

    private boolean battle(Fighter enemy)
    {
        boolean isntNornal;

        do{
            if(myDroid.getHp(0) > 0)
            {
                if(enemy.getType().equals("Boss"))
                    enemy.activeSkill(myDroid);

                myDroid.battleChar(enemy);
                do {
                    isntNornal = false;
                    System.out.println("````Actions:`````");
                    switch (World.menu(myDroid.retActions())) {
                        case 1:
                            System.out.println("You:");
                            enemy.HPMinus(myDroid.getBackpack().getCurWeapon().hit(enemy));
                            if (enemy.getHp(0) < 0) {
                                System.out.println(World.ANSI_GREEN + "You kill enemy!!!" + World.RESET);
                                try {
                                    Thread.sleep(1500);
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                return true;
                            }
                            break;
                        case 2:
                            isntNornal = myDroid.useSkill();
                            break;
                        case 3:
                            isntNornal = myDroid.getBackpack().getCurWeapon().useSkill();
                            break;
                        default:

                            break;
                    }
                }while (isntNornal);

                System.out.println("Enemy:");
                myDroid.HPMinus(enemy.getBackpack().getCurWeapon().hit(myDroid));

                myDroid.oneTurn();
            }
        }while(myDroid.getHp(0) > 0);

        System.out.println(World.ANSI_RED + "YOU DIE!");
        System.out.println("You will reset to your savings" + World.RESET);

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }

    public static Droid getMyDroid() {
        return myDroid;
    }
}
