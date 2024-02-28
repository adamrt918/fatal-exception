package fatalexception;

import static fatalexception.FatalException.clearScreen;
import static fatalexception.FatalException.pressEnterToContinue;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Adam Thiemann
 */
public class EncounterEnemy {
    private int outputResult;
    private EnemyCharacter enemy;
    private Random rand;
    private GameStatistics endStats;
    private final int BOSS_CHANCE = 7;
    private static int uMoves;
    
    //Constructor to get an action for enemy random number without statistic modifications
    public EncounterEnemy() {
        outputResult = -1;
        enemy = null;
        rand = null;
        endStats = new GameStatistics();
        uMoves = 0;
    }
    
    //Return the GameStatistics
    public GameStatistics getEndStats() {
        return endStats;
    }
    
    
    public EnemyCharacter encounterType(PlayerCharacter player) {
        rand = new Random();
        //There will be an encounter no matter what
        endStats.addTotalEncounters();
        
        //After 10 moves the player encounters the boss.
        if (uMoves > 10) {
            enemy = new EnemyCharacter("BOSS", rand.nextInt(3) + 1);
            return enemy;
        }
        
    //***************************BOSS ENCOUNTER SECTION***************************************
        //Check for boss encounter since moves are <= 10
        //Boss is encountered when player rolls a 10.
        int chance = BOSS_CHANCE * uMoves; 
        //If player speed is higher than original chance, 
        if (player.getSpeed() > chance) {
            chance /= 15;
        }
        else
            chance /= 10;
        //increment moves after first move so there is a 0% chance on the first turn.
        uMoves++;
        endStats.addStepsTaken();
                
        //Run the random number "chance" times. If any equals 10 then boss appears.
        for (int i = 0; i < chance ; i++) {
            outputResult = rand.nextInt(10) + 1; //Random number from 1 to 10
            if (outputResult == 10) {
                enemy = new EnemyCharacter("BOSS", rand.nextInt(3) + 1);
                System.out.println("As you venture through the hostile area,\n"
                        + "a sharp hissing sound comes from the nearby brush.\n"
                        + "The Infinite Loop hurls itself at you and you are\n"
                        + "barely able to avoid his attack. Rouse yourself, \n"
                        + player.getCharacterName() + ", for you may not survive"
                        + " this encounter.");
                return enemy;
            }  
        }
        
    /***************************REGULAR ENCOUNTER SECTION***************************************
    Since boss has not been encountered, take player speed into account 
    for difficulty of enemy they will encounter. Base is 125 so even if the 
    player maxes their range there is an encounter chance for difficult enemies.
    */
        
        outputResult = rand.nextInt(100) + 1;
        
        //This will determine what enemy is most difficult
        int[] defStats = {player.getDefense(), player.getMagicDefense(), player.getIntelligence()};
        int[] atkStats = {player.getStrength(), player.getMagicPoints(), player.getSpeed()};
        int [] combinedVal = new int[3];
        int max, min, idxMax, idxMin, tMax, tMin, iMax, iMin;
        int mult = 0;
        
        
        //Determine the player's best stats
        tMax = defStats[0];
        tMin = defStats[0];
        iMax = 0;
        iMin = 0;
        for (int i = 0; i < 3; i++) {
            combinedVal[i] = defStats[i] + atkStats[i];
            if (tMax < defStats[i]){
                tMax = defStats[i];
                iMax = i;
            }
            if (tMax < atkStats[i]) {
                tMax = atkStats[i];
                iMax = i;
            }
            if (tMin > defStats[i]){
                tMin = defStats[i];
                iMin = i;
            }
            if (tMin > atkStats[i]) {
                tMin = atkStats[i];
                iMin = i;
            }
        }
        
        max = combinedVal[0];
        min = combinedVal[0];
        idxMax = 0;
        idxMin = 0;
        for (int i = 0; i < 3; i++) {
            if (max < combinedVal[i]) {
                max = combinedVal[i];
                idxMax = i;
            }
            if (min > combinedVal[i]) {
                min = combinedVal[i];
                idxMin = i;
            }
        }
        //Player speed beats the roll, easy encounter.
        //Enemy selected by player's best stat and up to 1x multiplier
        if (player.getSpeed() / 2 > outputResult) {
            System.out.println(player.getCharacterName() + ", this enemy will fall by your hand. Continue on, " + player.getCharacterType() + ".\n");
            mult = 1;
            switch (idxMax) {
                case 0:
                    if (iMax == 0)
                        enemy = new EnemyCharacter("STR", mult);
                    else
                        enemy = new EnemyCharacter("DEF", mult);
                    break;
                case 1:
                    enemy = new EnemyCharacter("MDF", mult);
                    break;
                case 2:
                    enemy = new EnemyCharacter("INT", mult);
                    break;
                default:
                    enemy = new EnemyCharacter("", mult);
            }
        }
        //Player's speed doesn't beat the roll - 50, hard encounter
        //Enemy selected by using player's worst stat and up to 3x Multiplier
        else if (player.getSpeed() / 2 < (outputResult - 50)) {
            System.out.println(player.getCharacterName() + ", prepare to fight for your life. Godspeed...\n");
            mult = rand.nextInt(3) + 1;
            switch (idxMin) {
                case 0:
                    enemy = new EnemyCharacter("MDF", mult);
                    break;
                case 1:
                    enemy = new EnemyCharacter("INT", mult);                
                    break;
                case 2:
                    if (iMin == 0)
                        enemy = new EnemyCharacter("STR", mult);
                    else
                        enemy = new EnemyCharacter("DEF", mult);
                    break;
                default:
                    enemy = new EnemyCharacter("", mult);
            }
        }
        //Player speed / 2 didn't beat roll, but less than 50 off. Medium encounter
        //Enemy selected using player's best stat and up to 2x multiplier.
        else {
            System.out.println(player.getCharacterName() + ", this fight will not be easy. Have faith, " + player.getCharacterType() + "\n");
            mult = rand.nextInt(2) + 1;
            switch (idxMax) {
                case 0:
                    if (iMax == 0 && defStats[iMax] > atkStats[iMax])
                        enemy = new EnemyCharacter("STR", mult);
                    else
                        enemy = new EnemyCharacter("DEF", mult);
                    break;
                case 1:
                    enemy = new EnemyCharacter("MDF", mult);
                    break;
                case 2:
                    enemy = new EnemyCharacter("INT", mult);
                    break;
                default:
                    enemy = new EnemyCharacter("", mult);
            }
        }
        return enemy;
    }
    
    //Gets user action, may be in Jeneans class later
    public String getAction() {
        String action;
        System.out.println("\nWhat say you? (/? for menu)");
        Scanner scnr = new Scanner(System.in);
        action = scnr.nextLine();
        System.out.println();
        endStats.addActionsTaken();
        action = action.toLowerCase();
        return action;
    }
    
    public void displayActionMenu() {
        System.out.println("ACTION MENU");
        System.out.println("Slash -- Your enemy shall fall by thine sword.\n\tHeavily armored foes need not be afraid.\n");
        System.out.println("Fireball -- Conjure a fireball to smite your foe.\n\tMagic is sharper than any double edged weapon, able to penetrate\n\tthe enemy's defenses.\n");
        System.out.println("Escape -- You must live to fight another day, but your honor is besmirched,\n\tgallant hero. The maidens will laugh at you.\n");
        System.out.println("Outsmart -- A game of wits, outsmart your enemy and you will be rewarded.\n\tLose, and you may regret ever entering this place.\n");
    }
    
    //Returns a bool that will determine the text output for the finished encounter.
    public boolean encounterResult(PlayerCharacter player, EnemyCharacter enemy) {
        boolean win = false;  
        boolean didAttack;
        boolean goodResult = false;
        String uAction;
        rand = new Random();
        int temp;
        Scanner scnr = new Scanner(System.in);
        
        //User does an action, this action determines what type of stat will be used.
        while (win == false) {            
            player.printCurrentStats();
            System.out.println();
            enemy.printCurrentStats();
            didAttack = false;
            
            //***************************************PLAYER ATTACK SECTION***********************************************
            while (didAttack == false) {
                uAction = getAction();
                //Print the encounter from Jenean
                switch (uAction) {
                    case "slash":
                        outputResult = ((player.getStrength() / 3) * (rand.nextInt(2) + 1));
                        outputResult -= (enemy.getDefense() / 5);
                        if (outputResult <= 0) { 
                            goodResult = false;
                            outputResult = 0;
                            
                            System.out.println("The " + enemy.getCharacterName() + "'s armor deflected your blow.");
                            didAttack = true;
                        }
                        else {
                            goodResult = true;
                            enemy.subHitPoints(outputResult);
                            
                            System.out.println(player.getCharacterName() + " fought the enemy in close combat. The " + enemy.getCharacterName() + " was not fast enough\nand you were able to slice through their defense for " + outputResult + " damage.\n");
                            didAttack = true;
                        }
                        break;
                    case "fireball":
                        //Execute only if player has enough MP
                        if (player.getMagicPoints() > 5) {

                            //Take total MP and multiply by some number 1 - 3. 1 is Worst for player
                            temp = (rand.nextInt(3) + 1);
                            outputResult = player.getMagicPoints() * temp;
                            //Subtract the enemy's defense divided by 2
                            outputResult -= (enemy.getMagicDefense() / 3);
                            if (outputResult <= 0) {
                                System.out.println("Fire engulfs your foe and you begin to walk away, until you catch a glimpse of something moving.\nYour foe emerges unscathed.");
                                outputResult = 0;
                            }

                            //Only take away the MP used if the enemy doesn't have enough health
                            if(enemy.getHitPoints() <= outputResult) {
                                goodResult = true;
                                player.subMagicPoints(enemy.getHitPoints());
                                enemy.subHitPoints(enemy.getHitPoints());
                                
                                System.out.println("As the " + enemy.getCharacterName() + " screeches, you focus your energy within. You are unphased\nas a fiery ball emerges from between your fingers.\nThe fanatical hate in the eyes of your foe is unsettling as he burns alive.\n");
                                win = true;
                            }
                            //Take away as much as possible if enemy has enough health.
                            else {
                                goodResult = true;
                                player.subMagicPoints(outputResult / temp);                
                                enemy.subHitPoints(outputResult);
                                
                                System.out.println("The " + enemy.getCharacterName() + " howls in agony as he burns from your fiery magic that goes\nbefore you. You feel unsettled, the wind picks up, and the slightest spectral\nwisp of shadow surrounds your foe.\nHe emerges from the fire, having taken " + outputResult + " damage, but still able to fight.");
                            }
                            didAttack = true;
                        }
                        else {
                            System.out.println("You attempt to focus your energies, but the piercing cry of your foe's battle cry disrupts your focus.\nNot enough MP.");
                            didAttack = false;
                        }
                        break;                
                    case "escape":
                        didAttack = true;
                        outputResult = rand.nextInt(10) + 1;
                        outputResult -= enemy.getSpeed() / 10;
                        outputResult += player.getSpeed() / 7;
                        if (enemy.getCharacterName().equals("Infinite Loop")) {
                            if (outputResult > 8) {
                                endStats.addEnemiesEscaped();
                                win = false;
                                goodResult = true;
                                didAttack = false;
                                
                                System.out.println("The " + enemy.getCharacterName() + " prepares a crushing blow for your skull.");
                                System.out.println("As his weapon comes down upon you, you spring between his legs and bound\ninto a ditch.");
                                System.out.println("The foliage obscures you as " + enemy.getCharacterName() + " scours the area for his puny supper.");
                                System.out.println("You are lucky, " + player.getCharacterName() + ". You are able to rest and recover for a short time.");
                                PlayerCharacter ogStats = new PlayerCharacter(player.getCharacterType(), player.getCharacterName());
                                if (player.getHitPoints() < ogStats.getHitPoints() / 2) {
                                    System.out.println("You were restored to " + (ogStats.getHitPoints() / 2) + " HP.");
                                    player.addHitPoints(ogStats.getHitPoints() / 2 - player.getHitPoints());
                                }
                                else {
                                    System.out.println("You gained " + (ogStats.getHitPoints() / 4) + " HP from your successful escape.");
                                    player.addHitPoints(ogStats.getHitPoints() / 4);
                                }                               
                            }
                            else {
                                goodResult = false;
                                win = false;
                                System.out.println("The " + enemy.getCharacterName() + " delivers a crushing blow to your armor.");
                                System.out.println("You are not fast enough, " + player.getCharacterName() + ". I would not try that again if I were you.");
                                System.out.println("Stand up, " + player.getCharacterName() + "! " + enemy.getCharacterName() + " is preparing to finish you!");
                            }
                        }
                        else {
                            //Only escape if output is greater than 7. 8,9 or 10 are successful roles for the player. 
                            if (outputResult > 7) {
                                win = true;
                                goodResult = true;
                                endStats.addEnemiesEscaped();
                                
                                System.out.println("As your enemy squares up to block your path, you begin to move towards him\nwith a barbaric shout.");
                                System.out.println("The " + enemy.getCharacterName() + " reveals a ghastly smile and prepares to parry your\nill fated attack.");
                                System.out.println("As you come within striking distance you spring out of the way of the enemy's\ncounter-blow, and his weapon becomes lodged in a fallen log.");
                                System.out.println("You narrowly escape with your life, but your gamble paid off.\nContinue on your journey, " + player.getCharacterName() + ".");
                                player.addSpeed(5);
                                System.out.println("\nYou gained experience!");
                                System.out.println(" SPD +5");
                                
                                //Add HP to player if successful escape. Either restore to standard or add 1/4 of standard
                                PlayerCharacter ogStats = new PlayerCharacter(player.getCharacterType(), player.getCharacterName());
                                if (player.getHitPoints() < ogStats.getHitPoints()) {
                                    System.out.println(" HP  +" + (ogStats.getHitPoints() - player.getHitPoints()) + " (fully restored)");
                                    player.addHitPoints(ogStats.getHitPoints() - player.getHitPoints());
                                }
                                else {
                                    System.out.println(" HP  +" + (ogStats.getHitPoints() / 4));
                                    player.addHitPoints(ogStats.getHitPoints() / 4);
                                }                               
                            }
                            //Player does not escape
                            else {
                                goodResult = false;
                                System.out.println("As your enemy squares up to block your path, you begin to move towards him\nwith a barbaric shout.");
                                System.out.println("The " + enemy.getCharacterName() + " reveals a ghastly smile and prepares to parry your\nill fated attack.");
                                System.out.println("As you come within striking distance you trip over your own feet, and his\nweapon glances off of your armor to your extremities.");
                                System.out.println("The enemy prepares a killing blow, you have lost the initiative.");
                            }
                        }
                        break;
                    case "outsmart":
                        outputResult = (player.getIntelligence() + 1) * (rand.nextInt(2) + 1);
                        int enemyRoll = (enemy.getIntelligence() * (rand.nextInt(3) + 1));
                        
                        if (enemy.getCharacterType().equals("Infinite Loop")) {
                            System.out.println("You cannot outsmart the " + enemy.getCharacterName());
                            didAttack = false;
                        }
                        else {
                            //If the player loses the role, the enemy takes MP.                            
                            if (enemyRoll > outputResult) {
                                System.out.println("You challenge the enemy to a game of riddles. The " + enemy.getCharacterName() + " smugly accepts.");
                                System.out.println("You begin to riddle, speaking towards your distant foe, \n\"I had many locks that don't need a key--\"");
                                System.out.println("Your foe inches closer, but you trust his word and continue, ");
                                System.out.println("\"I was far too many for you to count me\nI was never to be shortened, for a vow had been said");
                                System.out.println("I was a symbol of strength that flowed from my head\nBut in a moment of weakness, my secret was out");
                                System.out.println("I lay all in pieces when she gave the shout\nWhat am I?\"");
                                pressEnterToContinue(true, false, false, scnr);
                                System.out.println("Your foe seems to be pondering the answer at a safe distance, you wait for him\nto answer...");
                                System.out.println("The shadowy apparition seems to grunt out an answer, \"You are.... lost.\"");
                                System.out.println("Out of the darkness springs the " + enemy.getCharacterName() + ", with a surprise blow he\ndraws blood before you can retreat.");
                                System.out.println("He conjured a weak apparition to take his form, and used the darkness to\nmaneuver within striking distance while you were focused on the riddle.\n");
                                
                                goodResult = false;
                                System.out.println("The " + enemy.getCharacterName() + " has outsmarted you and taken " + (enemyRoll - outputResult) + " MP from you.");
                                //Take MP. If the player doesn't have enough, take HP
                                player.subMagicPoints(enemyRoll - outputResult);
                                if (player.getMagicPoints() < 0) {
                                    player.subHitPoints(player.getMagicPoints() * -1);
                                    System.out.println("Since you did not have enough MP, " + (player.getMagicPoints() * -1) + " HP was taken.");
                                    
                                    //Restore MP to 0 since getMP is negative
                                    player.subMagicPoints(player.getMagicPoints());
                                }
                                didAttack = true;
                            }
                            //Player wins the role, takes award stats from enemy and wins.
                            else {
                                goodResult = true;
                                
                                System.out.println("You challenge the enemy to a game of riddles. The " + enemy.getCharacterName() + " smugly accepts.");
                                System.out.println("You ponder, but then begin to riddle: \n" +
                                    "\"I kept him steady and others away\n" +
                                    "I kept them safe and showed the way\n" +
                                    "Once thrown down upon the ground\n" +
                                    "I came alive with a hissing sound\"\n" +
                                    "The shadow of your foe begins to inch closer, but you continue,\n" +
                                    "\"I hit the rock as he was told\n" +
                                    "And that was when the water flowed\n" +
                                    "What am I?\"");
                                    pressEnterToContinue(true, false, false, scnr);
                                System.out.println("The apparation hisses but replies, \"You are... a flaming sword\"");
                                System.out.println("\"Wrong!\" you reply.\nThe apparition departs with a ghostly scream, and your confidence is\ngreatly boosted.\n");
                                win = true;
                                endStats.addEnemiesOutsmarted();
                                System.out.println("You gained experience!");
                                enemy.awardPlayerVerbose(player);
                                return win;
                            }
                        }
                        break;
                    case "/?":
                        displayActionMenu();
                        break;
                    default:
                        System.out.println("I'm sorry, my leige, I do not understand.");
                        win = false;
                        didAttack = false;
                        goodResult = false;
                        break;
                }
            }
            
            //*******************ENEMY ATTACK SECTION*****************************************************
            if (enemy.getHitPoints() > 0 && win == false) {
                //Enemy always attempts to use magic first
                if (enemy.getMagicPoints() > 14) {
                    outputResult = (enemy.getMagicPoints() / 3) * (rand.nextInt(2) + 1); //Can never be as much as the player
                    temp = outputResult;
                    outputResult -= (player.getMagicDefense() / 2);
                    if (outputResult < 0)
                        outputResult = 0;
                    player.subHitPoints(outputResult);
                    enemy.subMagicPoints(temp);
                    if (player.getHitPoints() < 0)
                        player.subHitPoints(player.getHitPoints());
                    
                    System.out.println("The " + enemy.getCharacterName() + 
                            " conjured a spectral arrow, you cannot dodge, the arrow\ntracks your movement. This is powerful magic.\n\nIt pierces your armor for " +
                            outputResult + " damage.\nYou have " + player.getHitPoints() + " HP remaining.");
                            pressEnterToContinue(true, false, false, scnr);
                            clearScreen();
                }
                //Use strength if magic is unavailable
                else {
                    //Roll for speed to see if player can dodge
                    
                    System.out.println("The " + enemy.getCharacterName() + " swiftly moves towards you. You parry the first few blows,\nbut your foe does not relent.");
                    outputResult = rand.nextInt(player.getSpeed());
                    if (outputResult >= (player.getSpeed() - (player.getSpeed() / 5))) {
                        
                        System.out.println("He begins to tire and you see an opportunity to distance yourself.");
                        System.out.println("You were able to dodge the rest of the attack, but only temporarily.\nPrepare for another bout.");
                        pressEnterToContinue(true, false, false, scnr);
                        clearScreen();
                    }
                    else {
                        outputResult = (rand.nextInt(3) + 1) * (enemy.getStrength() / 5);
                        outputResult -= (player.getDefense() / 8);
                        if (outputResult < 0)
                            outputResult = 0;
                        player.subHitPoints(outputResult);
                        if (player.getHitPoints() < 0)
                            player.subHitPoints(player.getHitPoints());
                        System.out.println("The " + enemy.getCharacterName() + " passed your guard and opens a wound beneath your armor.\n\nYou take " + outputResult +
                                " damage.\n" + player.getHitPoints() + 
                                " HP remaining.");
                                pressEnterToContinue(true, false, false, scnr);
                                clearScreen();
                    }
                }
            }
            //If the player is dead, stop the loop immediately
            if (player.getHitPoints() <= 0) {
                //Keep HP from being less than 0
                player.addHitPoints(player.getHitPoints() * -1);
                win = false;
                return win;
            }
            //Check for win
            else if (enemy.getHitPoints() <= 0 && !enemy.getCharacterName().equals("Infinite Loop")) {
                
                win = true;
		System.out.println("You gained experience!");
                enemy.awardPlayerVerbose(player);
                endStats.addEnemiesKilled();
                endStats.addTotalExp(enemy.getTotalAwardOnDefeat());
			}
            else if (enemy.getHitPoints() <= 0 && enemy.getCharacterName().equals("Infinite Loop")) {
                win = true;
                endStats.addEnemiesKilled();
            }
        }
        return win;
    }
}
