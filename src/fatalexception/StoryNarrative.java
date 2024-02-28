/**
 *
 * @author jeneanambersley
 * @coauthor jflum
 */

package fatalexception;

public class StoryNarrative {
    //attributes
    private String tempCharName;
    private String tempCharType;
    private String tempEnemyName;
    private String userPrologue;
    private String userEpilogue; //aka the good ending
    private String characterVictory;
    private String successfulEscape;
    private String unsuccessfulEscape; 
    private String successfulSlash;
    private String unsuccessfulSlash;
    private String successfulMagic;
    private String successfulOutsmart;
    private String unsuccessfulOutsmart;
    private String encounterEnemy;
    private String encounterFinalBoss;
    private String enemyMagic;
    private String enemySlash;
    private String successfulDodge;
    private String encounterStart;

    
    //default constuctor
    public StoryNarrative (PlayerCharacter player, EnemyCharacter enemy) {
        tempCharName = player.getCharacterName();
        tempCharType = player.getCharacterType();
        tempEnemyName = enemy.getCharacterName();
        
        //@jenean - last sentence of prologue below; prepend with your narrative
        //note the use of tempCharType and tempCharName for personalization
        userPrologue = "...and with no discernable landmark in sight, " 
                + tempCharType + " " + tempCharName + " sets off with "
                + "trepidation, wanting nothing more than to survive this "
                + "wretched land.";
        //@jenean - first sentence of epilogue below; append with your narrative
        userEpilogue = "Congratulations " 
                + tempCharType + " " + tempCharName + "! Infinite Loop, the"
                + "endless Tyrant has been defeated...";
        
        //@jenean - generic placeholder text, see EncounterEnemy class, encounter Result method, 
        //for Adam's detailed examples
        characterVictory = "Well done  " + tempCharType + "!";
        successfulEscape = "Escape successful!";
        unsuccessfulEscape = "Could not escape.";
        successfulSlash = "Slash was effective!";
        unsuccessfulSlash = "Slash missed.";
        successfulMagic = "Magic was effective!"; //don't need unsucc magic
        successfulOutsmart = "Enemy outsmarted!";
        unsuccessfulOutsmart = "Could not outsmart enemy.";  
        enemySlash = tempEnemyName + " used Slash";
        enemyMagic = tempEnemyName + " used Magic!";
        successfulDodge = tempEnemyName + " missed!";
        
        //@jenean - encounter start text based on regular or boss type
        if (tempCharName.equals("Infinite Loop")) {
            encounterStart = "There's something different about this area... "
                + "The endless tormentor, Infinite Loop, has revealed himself!";
        } else {
            encounterStart = tempCharType + " " + tempCharName
                + ", you've encountered a " + tempEnemyName + "!";
        }        
    } 

    //print methods 
    public void printUserPrologue() {
        System.out.println(userPrologue);
    }

    public void printUserEpilogue() {
        System.out.println(userEpilogue);
    }
    
    public void printEncounterStart() {
        System.out.println(encounterStart);
    }

    public void printEncounterAction(String actionType, boolean success) {
        switch (actionType) {
            case "escape":
                if (success) {
                    System.out.println(successfulEscape);
                } else {
                    System.out.println(unsuccessfulEscape);
                }
                break;
            case "slash":
                if (success) {
                    System.out.println(successfulSlash);
                } else {
                    System.out.println(unsuccessfulSlash);
                }
                break;
            case "magic":
                System.out.println(successfulSlash);
            case "outsmart":
                if (success) {
                    System.out.println(successfulOutsmart);
                } else {
                    System.out.println(unsuccessfulOutsmart);
                }
                break;
            case "enemy slash":
                System.out.println(enemySlash);
                break;
            case "enemy magic":
                System.out.println(enemyMagic);
                break;
            case "dodge":
                System.out.println(successfulDodge);
                break;
            default:
                System.out.println("\nERROR: unrecognized character action!");
                break;
        }
    }

    public void printCharacterVictory() {
        System.out.println(characterVictory);
    }
}

//@jenean - unused text from your last commit below:
//" it is ime to move forward...however...this area seems perilous. Oh no! You've encountered ";