package My2DGame.entidade;

import My2DGame.main.GamePainel;

public class Tasks extends Entity {

    public String title;
    public int goal;
    public int actual;
    public int points;
    public boolean completed = false;
    GamePainel gp;

    public Tasks(String title, int actual, int goal, int points, GamePainel gp){
        this.title = title;
        this.goal = goal;
        this.actual = actual;
        this.points = points;
        this.gp = gp;
    }
    
    public String getTile() {
    	return title;
    }
    
    public int getGoal() {
    	return goal;
    }
    
    public int getActual() {
    	return actual;
    }

    
    public void setActual(int actual) {
    	this.actual = actual;
    	
    	if (isComplete()) {
    		if (!completed ) {
    			gp.points += points;
    		}
    		completed = true;
    	}
    }
    
    public boolean isComplete() {
    	return actual >= goal;
    }
}
