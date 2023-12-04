package My2DGame.entidade;

import My2DGame.main.GamePainel;
import My2DGame.main.KeyHandler;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePainel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public JFrame window;
    Tasks task1;
    Tasks task2;
    Tasks task3;
    Tasks task4;
    Tasks task5;
    Tasks task6;
    int money;
    
    public Player(GamePainel gp, KeyHandler keyH, Tasks task5, Tasks task3, Tasks task4, Tasks task2, Tasks task6, Tasks task1){
        this.gp = gp;
        this.keyH = keyH;
        this.task2 = task2;
        this.task3 = task3;
        this.task4 = task4;
        this.task5 = task5;
        this.task6 = task6;
        this.task1= task1;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;


        setDefaultValues();
        getPlayerImage();
    }
    
    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/boy_right_2.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    public void setDefaultValues(){
        worldX = gp.tileSize * 11;
        worldY = gp.tileSize * 9;
        speed = 4;
        direction = "down";

    }
    public void update(){

        if (keyH.upPressed == true ||  keyH.rightPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true || keyH.lPressed == true || keyH.onepressed == true || keyH.twopressed == true){

            if (keyH.upPressed == true){
                direction = "up";
            }
            else if (keyH.downPressed == true) {
                direction = "down";
            }
            else if (keyH.leftPressed == true) {
                direction = "left";
            }
            else if (keyH.rightPressed == true) {
                direction = "right";
            }
            
            if (keyH.lPressed == true) {
            	int tileX = 0;
            	int tileY = 0;
            	
            	if (worldX > 0) {
            		tileX = worldX/45;
            	}
            	
            	if (worldY > 0) {
            		tileY = worldY/45;
            	}
            	
            	if (gp.tileM.mapTileNum[tileX][tileY] == 12) {
            		gp.inventory.addQuantityOfItemName("milk","res/inventory/leite.png", 1, task5);
            	} else if (gp.tileM.mapTileNum[tileX][tileY] == 13) {
            		gp.inventory.addQuantityOfItemName("la","res/inventory/la.png", 1);
            	} else if (gp.tileM.mapTileNum[tileX][tileY] == 14) {
            		gp.inventory.addQuantityOfItemName("egg","res/inventory/ovo.png", 1);
            	} else {
            		if (gp.tileM.mapTileNum[tileX][tileY] == 3) {
            			gp.drawTile(tileX, tileY, 15);
            			task3.setActual(task3.actual + 1);
                	} else if (gp.tileM.mapTileNum[tileX][tileY] == 15) {
                		gp.drawTile(tileX, tileY, 16);
                	} else if (gp.tileM.mapTileNum[tileX][tileY] == 16) {
                		gp.drawTile(tileX, tileY, 3);
                		gp.inventory.addQuantityOfItemName("corn","res/inventory/milho.png", 1, task4);
                	} else if (gp.tileM.mapTileNum[tileX][tileY] == 0){
                		gp.drawTile(tileX, tileY, 3);
                	}
            	}
            	
            	keyH.lPressed = false;
            	return;
            } 
            
            if (keyH.iPressed == true) {
            	if (gp.openInventory) {
            		gp.openInventory = false;
            	} else {
            		gp.openInventory = true;
            	}
            } 
            
            if (keyH.onepressed == true) {
            	gp.inventory.addQuantityOfItemName("seed", "res/inventory/semente.jpg", 1, task2);
            	gp.money -= 10;
            	keyH.onepressed = false;
            	task1.setActual(gp.money);
            	return;
            } 
            
            if (keyH.twopressed == true) {
            	task6.setActual(task6.actual + 1);
            	keyH.twopressed = false;
            	gp.money += 50;
            	task1.setActual(gp.money);
            	return;
            } 

            // Check tile collision
            collisionOn = false;
            gp.cChecker .checkTile(this);

            //if collision is false, player can move
            if(collisionOn == false){
                switch (direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12){
                if (spriteNum == 1){
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }

    }
    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch (direction){
            case "up":
                if (spriteNum == 1){
                    image = up1;
                }
                if (spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1){
                    image = down1;
                }
                if (spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1){
                    image = left1;
                }
                if (spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1){
                    image = right1;
                }
                if (spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }


}
