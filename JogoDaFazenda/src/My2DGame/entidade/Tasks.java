package My2DGame.entidade;

public class Tasks extends Entity {

    public String title;
    public int goal;
    public int actual;
    public int points;

    public Tasks(String title, int actual, int goal, int points){
        this.title = title;
        this.goal = goal;
        this.actual = actual;
        this.points = points;
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
    }
    
    public boolean isComplete() {
    	return actual > goal;
    }
}
