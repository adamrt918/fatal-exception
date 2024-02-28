/**
 * @author jflum
 */

package fatalexception;

public class EnemyCharacter extends PlayerCharacter {
    //attributes
    int awardOnDefeatHP;
    int awardOnDefeatMP;
    int awardOnDefeatSTR;
    int awardOnDefeatDEF;
    int awardOnDefeatINT;
    int awardOnDefeatMDF;
    int awardOnDefeatSPD;
    
    //parameterized constructor
    public EnemyCharacter (String characterType, int characterLevel) {
        String enemyNameSuffix;
        
        switch (characterLevel) {
            case 1:
                enemyNameSuffix = "";
                break;
            case 2:
                enemyNameSuffix = " Sergeant";
                break;
            case 3:
                enemyNameSuffix = " Captain";
                break;
            default:
                enemyNameSuffix = "";
                break;
        }
                
        switch (characterType) {
            case "STR":
                characterName = "Knight" + enemyNameSuffix;
                statHitPoints = 20 * characterLevel;
                statMagicPoints = 0 * characterLevel;
                statStrength = 20 * characterLevel;
                statDefense = 10 * characterLevel;
                statIntelligence = 3 * characterLevel;
                statMagicDefense = 0 * characterLevel;
                statSpeed = 10 * characterLevel; 
                awardOnDefeatHP = 10;
                awardOnDefeatMP = 5;
                awardOnDefeatSTR = 4;
                awardOnDefeatDEF = 4;
                awardOnDefeatINT = 1;
                awardOnDefeatMDF = 1;
                awardOnDefeatSPD = 3;
                break;
                
            case "INT":
                characterName = "Demon" + enemyNameSuffix;
                statHitPoints = 10 * characterLevel;
                statMagicPoints = 20 * characterLevel;
                statStrength = 5 * characterLevel;
                statDefense = 5 * characterLevel;
                statIntelligence = 20 * characterLevel;
                statMagicDefense = 5 * characterLevel;
                statSpeed = 5 * characterLevel; 
                awardOnDefeatHP = 5;
                awardOnDefeatMP = 15;
                awardOnDefeatSTR = 1;
                awardOnDefeatDEF = 2;
                awardOnDefeatINT = 5;
                awardOnDefeatMDF = 3;
                awardOnDefeatSPD = 2;
                break;
                
            case "DEF": 
                characterName = "Skeleton" + enemyNameSuffix;
                statHitPoints = 25 * characterLevel;
                statMagicPoints = 5 * characterLevel;
                statStrength = 10 * characterLevel;
                statDefense = 20 * characterLevel;
                statIntelligence = 5 * characterLevel;
                statMagicDefense = 5 * characterLevel;
                statSpeed = 2 * characterLevel; 
                awardOnDefeatHP = 10;
                awardOnDefeatMP = 10;
                awardOnDefeatSTR = 2;
                awardOnDefeatDEF = 4;
                awardOnDefeatINT = 2;
                awardOnDefeatMDF = 2;
                awardOnDefeatSPD = 1;
                break;
                
            case "MDF":
                characterName = "Undead" + enemyNameSuffix;
                statHitPoints = 15 * characterLevel;
                statMagicPoints = 15 * characterLevel;
                statStrength = 5 * characterLevel;
                statDefense = 0 * characterLevel;
                statIntelligence = 10 * characterLevel;
                statMagicDefense = 20 * characterLevel;
                statSpeed = 5 * characterLevel; 
                awardOnDefeatHP = 5;
                awardOnDefeatMP = 20;
                awardOnDefeatSTR = 3;
                awardOnDefeatDEF = 2;
                awardOnDefeatINT = 3;
                awardOnDefeatMDF = 5;
                awardOnDefeatSPD = 10;
                break;
            
            case "BOSS":
                characterName = "Infinite Loop";
                statHitPoints = 70;
                statMagicPoints = 100;
                statStrength = 50;
                statDefense = 80;
                statIntelligence = 100;
                statMagicDefense = 150;
                statSpeed = 40; 
                //no awards on defeat
                break;
                
            default:
                characterName = "???";
                statHitPoints = 1;
                statMagicPoints = 1;
                statStrength = 1;
                statDefense = 1;
                statIntelligence = 1;
                statMagicDefense = 1;
                statSpeed = 1; 
                awardOnDefeatHP = 1;
                awardOnDefeatMP = 1;
                awardOnDefeatSTR = 1;
                awardOnDefeatDEF = 1;
                awardOnDefeatINT = 1;
                awardOnDefeatMDF = 1;
                awardOnDefeatSPD = 1;
                break;
        }
    }
    
    //methods
    public void awardPlayer(PlayerCharacter player) {
        player.addHitPoints(awardOnDefeatHP);
        player.addMagicPoints(awardOnDefeatMP);
        player.addStrength(awardOnDefeatSTR);
        player.addDefense(awardOnDefeatDEF);
        player.addIntelligence(awardOnDefeatINT);
        player.addMagicDefense(awardOnDefeatMDF);
        player.addSpeed(awardOnDefeatSPD);
    }
    
    public void awardPlayerVerbose(PlayerCharacter player) {        
        if (awardOnDefeatHP != 0) {
            System.out.println(" HP  +" + awardOnDefeatHP);
            player.addHitPoints(awardOnDefeatHP);
        }
        if (awardOnDefeatMP != 0) {
            System.out.println(" MP  +" + awardOnDefeatMP);
            player.addMagicPoints(awardOnDefeatMP);
        }
        if (awardOnDefeatSTR != 0) {
            System.out.println(" STR +" + awardOnDefeatHP);
            player.addStrength(awardOnDefeatSTR);
        }
        if (awardOnDefeatDEF != 0) {
            System.out.println(" DEF +" + awardOnDefeatDEF);
            player.addDefense(awardOnDefeatDEF);
        }
        if (awardOnDefeatINT != 0) {
            System.out.println(" INT +" + awardOnDefeatINT);
            player.addIntelligence(awardOnDefeatINT);
        }
        if (awardOnDefeatMDF != 0) {
            System.out.println(" MDF +" + awardOnDefeatMDF);
            player.addMagicDefense(awardOnDefeatMDF);
        }
        if (awardOnDefeatSPD != 0) {
            System.out.println(" SPD +" + awardOnDefeatSPD);
            player.addSpeed(awardOnDefeatSPD);
        }
    }
    
    public int getTotalAwardOnDefeat() {
        int encounterTotalExp = (awardOnDefeatHP + 
                             awardOnDefeatMP + 
                             awardOnDefeatSTR + 
                             awardOnDefeatDEF + 
                             awardOnDefeatINT +
                             awardOnDefeatMDF + 
                             awardOnDefeatSPD);
        
        return encounterTotalExp;
    }
    
    @Override
    public void printCurrentStats() {
        System.out.println(characterName + "'s status:");
        System.out.println(" HP:  " + statHitPoints);
        System.out.println(" MP:  " + statMagicPoints);
        System.out.println(" STR: " + statStrength);
        System.out.println(" DEF: " + statDefense);
        System.out.println(" INT: " + statIntelligence);
        System.out.println(" MDF: " + statMagicDefense);
        System.out.println(" SPD: " + statSpeed);
    }
}
