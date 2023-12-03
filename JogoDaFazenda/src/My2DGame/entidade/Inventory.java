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
    	System.out.print(quantity);
    	return quantity;
    }
    
    public void addQuantityOfItemName(String name, int quantity) {
    	 for (int i = 0; i < items.size(); i ++) {
         	if (items.get(i).name == name) {
         		items.get(i).setQuantity(quantity);
         	}
         }
    }
}
