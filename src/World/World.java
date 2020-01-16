package World;

import Droids.Droid;
import Droids.Heroes.HeavyDroid;
import Droids.Heroes.LightDroid;
import Droids.Heroes.MediumDroid;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.SplittableRandom;

public class World {

    private Droid myDroid;
    public static Scanner scan =  new Scanner(System.in);
    public static SplittableRandom rand = new SplittableRandom();
    public static final String WHITE = "\033[0;37m";
    public static final String RESET = "\033[0m";
    public static final String BLUE = "\033[0;36m";
    public static final String PURPLE = "\033[0;35m";
    public static final String YELLOW = "\033[0;33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    private int counterLvls = 1;

    public static int menu(String [] sample)
    {
        boolean correctNum = false;
        int choose;

        do {
            for (int i = 0; i < sample.length; i++) {
                System.out.println((i + 1) + " - " + sample[i]);
            }
            System.out.print("Choose: ");
            choose = scan.nextInt();

            if (choose > sample.length || choose < 1) {
                correctNum = true;
                System.out.println("Enter correct number!!!");
            }
            else
                correctNum = false;
            for (int i = 0; i < 50; ++i) System.out.print("=");
            System.out.println();
        }while(correctNum);

        return choose;
    }

    public static int menu(String [] sample, boolean[] avaible)
    {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_GREY = "\u001b[37;1m";
        final String ANSI_WHITE = "\u001b[30;1m";

        boolean uncorrectNum = false;
        int choose;

        do {
            for (int i = 0; i < sample.length; i++) {
                if(avaible[i])
                {
                    System.out.println(ANSI_WHITE + (i + 1) + " - " + sample[i] + ANSI_RESET);
                }else
                    System.out.println(ANSI_GREY + (i + 1) + " - " + sample[i] + ANSI_RESET);
            }
            System.out.print("Choose: ");
            choose = scan.nextInt();

            if (choose > sample.length || choose < 1) {
                uncorrectNum = true;
                System.out.println("Enter correct number!!!");
            }else if(!avaible[choose - 1]){
                uncorrectNum = true;
                System.out.println("This point is inaccessible! Choose another.");
            }else
                uncorrectNum = false;
            for (int i = 0; i < 50; ++i) System.out.print("=");
            System.out.println();
        }while(uncorrectNum);

        return choose;
    }

    private static boolean isOkay()
    {
        String[] next = {"OK","Return"};
        switch (menu(next)) {
            case 1:
                return true;
            case 2:
                return false;
        }
        return false;
    }

    public static void createWorld() {
        World world = new World();

        String[] sampleMainMenu = {"New game", "Continue game", "Exit"};
        boolean isReturn = false;
        do {
            switch (menu(sampleMainMenu)) {
                case 1:
                    isReturn = world.startNewGame();
                    break;
                case 2:
                    isReturn = !world.continueGame();
                    if(!isReturn)
                        isReturn = world.startGame();
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }while(isReturn);
    }


    private boolean startNewGame()
    {
        myDroid = chooseDroid();
        return startGame();
    }

    private boolean startGame()
    {
        System.out.println("Your Droid characteristic: ");
        myDroid.showChar();

        boolean isReturn = false;
        String[] levels = {"Level 1", "Level 2","Level 3","Level 4","Level 5","Save","Return"};
        boolean[] avaible = {true, false, false, false, false, true, true};

        for(int i = 0; i < levels.length; i++)
        {
            if(myDroid.howMuchDone() >= i)
                avaible[i] = true;
        }

        if(myDroid != null)
        {
            do{
                System.out.println("Choose level: ");
                int choise = menu(levels, avaible);
                if(choise != levels.length && choise != levels.length - 1){
                    Level lvl = new Level(choise, myDroid);
                    int reset = lvl.playLevel();

                    if(reset == 15)
                    {
                        winner();
                    }

                    if(reset == 100)
                    {
                        return true;
                    }

                    if(reset >= 10) {
                        counterLvls = reset - 9;
                        myDroid.setHowMuchDone(counterLvls - 1);
                        avaible[counterLvls - 1] = true;
                        myDroid = lvl.getMyDroid();
                        lvl = new Level(counterLvls, myDroid);
                        choise = counterLvls;
                        isReturn = true;
                    }
                    else if(reset == 0)
                    {
                        isReturn = true;
                    }
                }
                else if(choise == levels.length - 1)
                {
                    saving();
                    isReturn = true;
                }
                else
                    return true;
            }while(isReturn);
        }
        else
            return true;
        return false;

    }

    private Droid chooseDroid()
    {

        int choise = 0;
        String[] heroesDroid = {"Light Droid", "Medium Droid", "Heavy Droid", "Return"};
        boolean isReturn = false;

        do {
            switch (menu(heroesDroid)) {
                case 1:
                    System.out.println("You choose Light Droid, it hasn't lots of health, but has high damage and chance of shaking. Can fast kill enemies. The best weapon is laser swords");
                    choise = 1;
                    break;
                case 2:
                    System.out.println("You choose Medium Droid, it has medium health and damage. Can be like fast damager or late severe. The best weapon is guns. ");
                    choise = 2;
                    break;
                case 3:
                    System.out.println("You choose Heavy Droid, it has lots of health, but has low damage and heavy armor. Can fight for a long time. The best weapon is rocket weapon");
                    choise = 3;
                    break;
                case 4:
                    return null;
            }

            if (isOkay()) {
                System.out.print("Enter name of your droid: ");
                scan.nextLine();
                String droidName = scan.nextLine();
                switch (choise) {
                    case 1:
                        return new LightDroid(droidName);
                    case 2:
                        return  new MediumDroid(droidName);
                    case 3:
                        return new HeavyDroid(droidName);
                }
            }
            else
                isReturn = true;

        }while (isReturn);
        return null;
    }

    private static void winner()
    {
        System.out.println(BLUE + "My congratulations you kill all enemies! Now in world is whole piece! Our hero! But it's not for long. Be ready to new war)" + RESET);
    }

    private void saving()
    {
        System.out.print("Save as: ");
        scan.nextLine();
        String save = scan.nextLine();
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(save+".dat")))
        {
            oos.writeObject(myDroid);
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
    }

    private boolean continueGame()
    {
        boolean isReturn = false;
        do {
            System.out.print("Open: ");
            scan.nextLine();
            String open = scan.nextLine();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(open + ".dat"))) {
                myDroid = (Droid) ois.readObject();
                return true;
            } catch (Exception ex) {

                System.out.println(ex.getMessage());
                System.out.println("There aren't such save!");
                System.out.println("One more time?");
                isReturn = isOkay();
            }
        }while (isReturn);
        return false;
    }
}


