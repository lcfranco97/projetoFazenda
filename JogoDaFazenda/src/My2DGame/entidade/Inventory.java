package My2DGame.entidade;

import java.util.ArrayList;

public class Inventory extends Entity {

    public ArrayList<Items> items = new ArrayList<Items>();

    public Inventory() {
    }
    
    public void addItem(Items item, int quantity) {
    	
    	int index = items.indexOf(item);
    	
    	if (index != -1) {
    		items.get(index).setQuantity(quantity);
    	} else {
    		item.setIndex(items.size());
    		items.add(item);
    	}
    }
    
    public int getQuantityOfItemName(String name) {
    	int quantity = 0;
    	 for (int i = 0; i < items.size(); i ++) {
         	if (items.get(i).name == name) {
         		quantity = items.get(i).quantity;
         	}
         }
    	return quantity;
    }
    
    public void addQuantityOfItemName(String name, String image, int quantity) {
    	boolean added = false;
    	for (int i = 0; i < items.size(); i ++) {
        	if (items.get(i).name == name) {
         		items.get(i).setQuantity(items.get(i).quantity + quantity);
         		added = true;
         	}
        }
    	 
    	if (!added) {
    		items.add(new Items(name, image, quantity));
    	}
    }
    
    public void addQuantityOfItemName(String name, String image, int quantity, Tasks task) {
    	boolean added = false;
    	for (int i = 0; i < items.size(); i ++) {
        	if (items.get(i).name == name) {
         		items.get(i).setQuantity(items.get(i).quantity + quantity);
         		added = true;
         	}
        }
    	 
    	if (!added) {
    		items.add(new Items(name, image, quantity, task));
    	}
    }
}
