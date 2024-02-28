/**
 * @author jflum
 */

package fatalexception;

import java.util.Scanner;
import java.util.Arrays;
import java.io.*;



public class FatalException {

    public static void clearScreen() {
        
        final int LINES_TO_CLEAR = 25; //set to terminal screen height
        
        for (int i = 0; i < LINES_TO_CLEAR; i++) {
                System.out.println("\n");
        }
    }
    
    public static void printTextFile(String fileName, boolean pressEnterToCont,
            Scanner scnr) throws FileNotFoundException, IOException {
        
        final String RESOURCE_PATH = "resources/"; //set to resource directory
        String fileLine;
        
        try {
            BufferedReader bReader = 
                new BufferedReader(new FileReader(RESOURCE_PATH + fileName));
            
            while ((fileLine = bReader.readLine()) != null) {
                System.out.println(fileLine);
            }
            bReader.close();
        } catch (FileNotFoundException noFile) {
            System.out.println(
                "\n--> PRINT ERROR: \"" + fileName + "\" not found in " + 
                "\"" + RESOURCE_PATH + "\" <--\n");
        }
        
        if (pressEnterToCont) {
        pressEnterToContinue(false, true, false, scnr);
        }
    }
    
    public static void pressEnterToContinue(
            boolean lineBefore, boolean centered, boolean chomp, Scanner scnr) {
        
        if (lineBefore) {
            System.out.println();
        }
        
        //chomp rest of line if previous input is non-nextLine()
        if (chomp) {
            scnr.nextLine();
        }
        
        if (centered) {
        System.out.println(
                "                             Press ENTER to continue");
        scnr.nextLine();
        } else {
        System.out.println("Press ENTER to continue");
        scnr.nextLine();    
        }
    }
    
    public static String mainMenu(Scanner scnr) {
        String menuSelection;
        boolean validSelection = false;
      
        do {
            System.out.println("MAIN MENU:");
            System.out.println("(1) - New Quest");
            System.out.println("(2) - Readme");
            System.out.println("(3) - Credits");
            System.out.println("(4) - Quit");
            System.out.println("\n" + "Choose an option:");

            menuSelection = scnr.nextLine();
            if (Arrays.asList("1", "2", "3", "4").contains(menuSelection)) {
                validSelection = true;
            } else {
                System.out.println("\nERROR: Invalid menu selection!\n");
            }
        } while (validSelection == false);
        
        return menuSelection;
    }
    
    public static String characterTypeMenu(Scanner scnr) {
        String menuSelection;
        String characterType = "";
        boolean validSelection = false;
        
        do {
            System.out.println("\nSELECT YOUR CLASS, HERO:");
            System.out.println("(1) - Warrior: " +
                "Excessive strength and heavy armor");
            System.out.println("(2) - Mage: " +
                "   Powerful magic and high intelligence");
            System.out.println("(3) - Thief: " +
                "  Exceptionally fast and versatile");
            //System.out.println("(4) - *** Debug Mode: " +
            //   "Level 100 stats in every category ***");

            System.out.println("\n" + "Choose an option:");

            menuSelection = scnr.nextLine();
            switch (menuSelection) {
                case "1":
                    characterType = "Warrior";
                    validSelection = true;
                    break;
                case "2":
                    characterType = "Mage";
                    validSelection = true;
                    break;
                case "3":
                    characterType = "Thief";
                    validSelection = true;
                    break;
                case "4":
                    characterType = "Debug Mode";
                    validSelection = true;
                    break;
                default:
                    System.out.println("\nERROR: Invalid class selection!");
                    break;
            }
        } while (validSelection == false);
        
        return characterType;
    }
    
    public static String characterNameMenu(String playerType, Scanner scnr) {
        String characterName;
        boolean validEntry = false;
        
        playerType = playerType.toUpperCase();
      
        do {
            System.out.println("\nWHAT IS YOUR NAME, " + playerType + "?");
            characterName = scnr.nextLine();
            if ((characterName.length() > 0) && (!characterName.equals(" "))
                    && characterName.charAt(0) != ' ') {
                validEntry = true;
            }
            else {
                System.out.println("\nERROR: Invalid name entry!");
            }
        } while (validEntry == false);

        return characterName;
    }
    
    public static String retryMenu (Scanner scnr) {
        String menuSelection;
        boolean validSelection = false;
      
        do {
            System.out.println("PLAY AGAIN?");
            System.out.println("(1) - Retry with the same character");
            System.out.println("(2) - Retry with a new character");
            System.out.println("(3) - Go back to title screen");
            System.out.println("(4) - Quit");
            System.out.println("\n" + "Choose an option:");

            menuSelection = scnr.nextLine();
            if (Arrays.asList("1", "2", "3", "4").contains(menuSelection)) {
                validSelection = true;
            } else {
                System.out.println("\nERROR: Invalid menu selection!\n");
            }
        } while (validSelection == false);
        
        return menuSelection;
    }
    
//******************************MAIN STARTS HERE********************************
    
    public static void main(String[] args) 
            throws FileNotFoundException, IOException {
        
        Scanner scnr = new Scanner(System.in);
        String userMenuSelection;
        PlayerCharacter player = null;
        EnemyCharacter enemy;
        StoryNarrative story = null;
        EncounterEnemy encounter;
        GameStatistics stats = null;
        String tempPlayerType;
        String tempPlayerName;
        boolean gameRunning = true; //cradle to grave loop
        boolean mainMenuExit = false; //used to exit main menu
        boolean characterMenuExit = false; //used to exit char init. loop
        boolean retryMenuExit = false; // used to exit retry loop
        boolean gameOver = false; //used to exit game loop on win or loss
        boolean win = false; //Used to determine fight winner
              
        clearScreen();
        printTextFile("titlecard.txt", true, scnr);
        
        do {            
            //main menu
            if (mainMenuExit == false) {
                userMenuSelection = mainMenu(scnr);
                switch (userMenuSelection) {
                    case "1":
                        characterMenuExit = false;
                        gameOver = false;
                        retryMenuExit = false;
                        stats = new GameStatistics();
                        break;
                    case "2":
                        printTextFile("readme.txt", true, scnr);
                        characterMenuExit = true;
                        gameOver = true;
                        retryMenuExit = true;
                        break;
                    case "3":
                        printTextFile("credits.txt", true, scnr);
                        characterMenuExit = true;
                        gameOver = true;
                        retryMenuExit = true;
                        break;
                    case "4":
                        characterMenuExit = true;
                        gameOver = true;
                        retryMenuExit = true;
                        gameRunning = false;
                        break;
                }                
            }

            //character selection menu
            if (characterMenuExit == false) {
                tempPlayerType = characterTypeMenu(scnr);
                tempPlayerName = characterNameMenu(tempPlayerType, scnr);
                player = new PlayerCharacter(tempPlayerType, tempPlayerName);
                //player.printCurrentStats();
                characterMenuExit = true; //for subsequent game modifiers, retry
                System.out.println("\nSo it is...");
                pressEnterToContinue(true, false, false, scnr);
            }

            //primary game loop
            if (gameOver == false) {
                
                //prologue
                clearScreen();
                //could put a graphic here to set the mood
                System.out.println("\n...and with no discernable landmark in sight, " 
                + player.getCharacterType() + " " + player.getCharacterName()
                + " sets off with\ntrepidation, wanting nothing more than to "
                + "survive this wretched land.");
                
                encounter = new EncounterEnemy();
               
                do {
                    pressEnterToContinue(true, false, false, scnr);
                    clearScreen();
                    //Call the encounter class to determine enemy
                    enemy = encounter.encounterType(player);
                    //Display text for the enemy encounter
                    //Do the whole encounter and check for game over.
                    win = encounter.encounterResult(player, enemy);
                    if (win == true && 
                            enemy.getCharacterName().equals("Infinite Loop")) {
                        gameOver = true;
                        System.out.println("CONGRATULATIONS " 
                            + player.getCharacterType() + " " 
                            + player.getCharacterName()
                            + "!\nThe endless tyrant Infinite Loop "
                            + "has been defeated, but with his last breath\n"
                            + "you hear him faintly utter \"recursion...\"\n"
                                    + "~ THE END? ~");
                    }
                    else if (win) {
                        //System.out.println("\nYOU ARE VICTORIOUS!");
                    }
                    else {
                        gameOver = true;
                        System.out.println("\nYOU ARE DEAD.");
                        //negative outcome goes to Jenean, game is over
                    }
                } while (gameOver == false);

                //pause before results
                pressEnterToContinue(true, false, false, scnr);

                //results screen
                System.out.println(player.getCharacterType() + " " + 
                        player.getCharacterName() + "'s results:");
                stats.printGameStats(encounter.getEndStats());
                pressEnterToContinue(true, false, false, scnr);

                //retry menu
                if (retryMenuExit == false) {
                    userMenuSelection = retryMenu(scnr);
                    switch (userMenuSelection) {
                        case "1":
                            gameOver = false;
                            mainMenuExit = true;
                            characterMenuExit = true;
                            player = new 
                                PlayerCharacter(player.getCharacterType(), 
                                player.getCharacterName());
                            break;
                        case "2":
                            gameOver = false;
                            mainMenuExit = true;
                            characterMenuExit = false;
                            clearScreen();
                            break;
                        case "3":
                            gameOver = false;
                            mainMenuExit = false;
                            characterMenuExit = false;
                            retryMenuExit = true;
                            clearScreen();
                            printTextFile("titlecard.txt", true, scnr);
                            break;
                        case "4":
                            gameRunning = false;
                            retryMenuExit = true;
                            break;
                    }                
                } 
            }
        } while (gameRunning == true);
    
        printTextFile("thankyou.txt", false, scnr);
    }
}
