package My2DGame.entidade;

public class Items extends Entity {

    public String name;
    public String image;
    public int quantity;
    public int index;
    public Tasks task;
    
    public Items(String name, String image, int quantity){
        this.name = name;
        this.image = image;
        this.quantity = quantity;
    }

    public Items(String name, String image, int quantity, Tasks task){
        this.name = name;
        this.image = image;
        this.quantity = quantity;
        
        if (task != null) {
    		task.setActual(quantity);
    	}
        
        this.task = task;        
    }
    
    public void setIndex(int index) {
    	this.index = index;
    }
    
    public String getName() {
    	return name;
    }
    
    public String getImage() {
    	return image;
    }
    
    public int getActual() {
    	return quantity;
    }

    
    public void setQuantity(int quantity) {
    	this.quantity = quantity;
    	if (task != null) {
    		task.setActual(quantity);
    	}
    }
}
