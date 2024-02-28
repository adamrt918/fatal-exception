/**
 * @author jflum
 */

package fatalexception;

public class PlayerCharacter {
    //attributes
    protected String characterType;
    protected String characterName;
    protected int statHitPoints;
    protected int statMagicPoints;
    protected int statStrength;
    protected int statDefense;
    protected int statIntelligence;
    protected int statMagicDefense;
    protected int statSpeed;

    //parameterized constructor
    public PlayerCharacter (String characterType, String characterName) {
        this.characterType = characterType;
        this.characterName = characterName;
        
        switch(characterType) {
            case "Warrior":
                statHitPoints = 40;
                statMagicPoints = 5;
                statStrength = 20;
                statDefense = 25;
                statIntelligence = 5;
                statMagicDefense = 5;
                statSpeed = 10;  
                break;
                
            case "Mage":
                statHitPoints = 20;
                statMagicPoints = 30;
                statStrength = 5;
                statDefense = 10;
                statIntelligence = 20;
                statMagicDefense = 20;
                statSpeed = 5;  
                break;
                
            case "Thief":
                statHitPoints = 30;
                statMagicPoints = 10;
                statStrength = 15;
                statDefense = 15;
                statIntelligence = 10;
                statMagicDefense = 10;
                statSpeed = 20;  
                break;
                
            case "Debug Mode":
                statHitPoints = 100;
                statMagicPoints = 100;
                statStrength = 100;
                statDefense = 100;
                statIntelligence = 100;
                statMagicDefense = 100;
                statSpeed = 100;  
                break;
                
            default:
                System.out.println(
                    "\n--> INPUT ERROR: Invalid character type <--\n");
                statHitPoints = 1; //value of 1 to avoid instant death
                statMagicPoints = 0;
                statStrength = 0;
                statDefense = 0;
                statIntelligence = 0;
                statMagicDefense = 0;
                statSpeed = 0; 
                break;
        }
    }
    
    //default constructor
    public PlayerCharacter () {
        characterType = "Rogue";
        characterName = "Error"; //Zelda II reference, wow :)
        statHitPoints = 1;
        statMagicPoints = 1;
        statStrength = 1;
        statDefense = 1;
        statIntelligence = 1;
        statMagicDefense = 1;
        statSpeed = 1;        
    }
    
    //accessor methods
    public String getCharacterType() {
        return characterType;
    }
    
    public String getCharacterName() {
        return characterName;
    }
    
    public int getHitPoints() {
        return statHitPoints;
    }
    
    public int getMagicPoints() {
        return statMagicPoints;
    }
    
    public int getStrength() {
        return statStrength;
    }
    
    public int getDefense() {
        return statDefense;
    }
    
    public int getIntelligence() {
        return statIntelligence;
    }
    
    public int getMagicDefense() {
        return statMagicDefense;
    }
    
    public int getSpeed() {
        return statSpeed;
    }
    
    //mutator methods
    public void addHitPoints(int amountToAdd) {
        statHitPoints += amountToAdd;
    }
    
    public void subHitPoints(int amountToSub) {
        statHitPoints -= amountToSub;
    }
    
    public void addMagicPoints(int amountToAdd) {
        statMagicPoints += amountToAdd;
    }
    
    public void subMagicPoints(int amountToSub) {
        statMagicPoints -= amountToSub;
    }
    
    public void addDefense(int amountToAdd) {
        statDefense += amountToAdd;
    }
    
    public void addStrength(int amountToAdd) {
        statStrength += amountToAdd;
    }
    
    public void addIntelligence(int amountToAdd) {
        statIntelligence += amountToAdd;
    }
    
    public void addMagicDefense(int amountToAdd) {
        statMagicDefense += amountToAdd;
    }
    
    public void addSpeed(int amountToAdd) {
        statSpeed += amountToAdd;
    }

    //print method
    public void printCurrentStats() {
        System.out.println(characterType + " " + characterName + "'s status:");
        System.out.println(" HP:  " + statHitPoints);
        System.out.println(" MP:  " + statMagicPoints);
        System.out.println(" STR: " + statStrength);
        System.out.println(" DEF: " + statDefense);
        System.out.println(" INT: " + statIntelligence);
        System.out.println(" MDF: " + statMagicDefense);
        System.out.println(" SPD: " + statSpeed);
    }
}
