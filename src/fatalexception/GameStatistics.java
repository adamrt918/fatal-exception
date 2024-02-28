package fatalexception;

/**
 *
 * @author Adam Thiemann
 */
public class GameStatistics {
    private int stepsTaken;
    private int enemiesKilled;
    private int enemiesEscaped;
    private int enemiesOutsmarted;
    private int totalEncounters;
    private int actionsTaken;
    private int totalExperience;
    
    public GameStatistics() {
        stepsTaken = 0;
        enemiesKilled = 0;
        enemiesEscaped = 0;
        enemiesOutsmarted = 0;
        totalEncounters = 0;
        actionsTaken = 0;
        totalExperience = 0;
    }
    
    public GameStatistics(GameStatistics stats) {
        stepsTaken = stats.getStepsTaken();
        enemiesKilled = stats.getEnemiesKilled();
        enemiesEscaped = stats.getEnemiesEscaped();
        enemiesOutsmarted = stats.getEnemiesOutsmarted();
        totalEncounters = stats.getTotalEncounters();
        actionsTaken = stats.getActionsTaken();
        totalExperience = stats.getTotalExp();
    }

    //Getter methods
    public int getStepsTaken() {
        return stepsTaken;
    }

    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    public int getEnemiesEscaped() {
        return enemiesEscaped;
    }

    public int getEnemiesOutsmarted() {
        return enemiesOutsmarted;
    }

    public int getTotalEncounters() {
        return totalEncounters;
    }

    public int getActionsTaken() {
        return actionsTaken;
    }

    public int getTotalExp() {
        return totalExperience;
    }
    
    //Increment Methods
    public void addStepsTaken() {
        
        //assuming every move is 100m, I think this is my 100m pace count * 2
        stepsTaken += 132;
    }
    
    public void addEnemiesKilled() {
        enemiesKilled++;
    }
    
    public void addEnemiesEscaped() {
        enemiesEscaped++;
    }
    
    public void addEnemiesOutsmarted() {
        enemiesOutsmarted++;
    }
    
    public void addTotalEncounters() {
        totalEncounters++;
    }
    
    public void addActionsTaken() {
        actionsTaken++;
    }
    
    //Needs a parameter because the exp is variable
    public void addTotalExp(int exp) {
        totalExperience += exp;
    }
    
    public void printGameStats(GameStatistics obj) {
        
        System.out.println(" Total steps taken:   " + obj.getStepsTaken());
        System.out.println(" Enemies slain:       " + obj.getEnemiesKilled());
        System.out.println(" Enemies escaped:     " + obj.getEnemiesEscaped());
        System.out.println(" Enemies outsmarted:  " + obj.getEnemiesOutsmarted());
        System.out.println(" Total encounters:    " + obj.getTotalEncounters());
        System.out.println(" Total actions taken: " + obj.getActionsTaken());
        System.out.println(" Total experience:    " + obj.getTotalExp());
    }
}
