package My2DGame.main;

import My2DGame.entidade.Inventory;
import My2DGame.entidade.Items;
import My2DGame.entidade.Player;
import My2DGame.entidade.Tasks;
import tile.TileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePainel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1044178317137689817L;
	//CONFIGURAÇÕES DE SCREEN
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //MAPA SETTINGS
    public final int maxWorldCol = 40;
    public final int maxWorldRow = 30;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    
    public int time = 0;
    public int money = 200;
    public int points = 0;
    public boolean finished = false;
    public String playerName = "";
    
    public Inventory inventory = new Inventory();
    
    public Tasks task1 = new Tasks("Juntar 1000 de ouro.", money, 1000, 1000, this);
    public Tasks task2 = new Tasks("Comprar 10 sementes.", inventory.getQuantityOfItemName("seeds"), 10, 100, this);
    public Tasks task3 = new Tasks("Plantar 10 sementes.", inventory.getQuantityOfItemName("seeds"), 10, 100, this);
    public Tasks task4 = new Tasks("Coletar 10 milhos.", inventory.getQuantityOfItemName("corns"), 10, 500, this);
    public Tasks task5 = new Tasks("Coletar 10 leites.", inventory.getQuantityOfItemName("milks"), 10, 100, this);
    public Tasks task6 = new Tasks("Vender 20 itens.", inventory.getQuantityOfItemName("seeds"), 20, 500, this);  

    //FPS
    int FPS = 60;
    public boolean openInventory = true;
    public TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public JFrame window;
     
    public Player player = new Player(this,keyH, task5, task3, task4, task2, task6, task1);

    public GamePainel(JFrame window){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.window = window;
        
        inventory.addItem(new Items("Pá", "res/inventory/pa.png", 1), 1);
        inventory.addItem(new Items("Regador", "res/inventory/regador.png", 1), 1);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS; // 0.01666 segundos
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){

            update();

            repaint();
            
            time += 1;
                        
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e){
                e.printStackTrace();
            }
            
            if (task1.completed && task2.completed && task3.completed && task4.completed && task5.completed && task6.completed && !finished) {
            	Menu menu = new Menu();
            	
            	window.add(menu.createEndGame(window, true, playerName, points));
            	this.setVisible(false);
            	finished = true;
            }
            
            if (time > 20000 && !finished) {
            	Menu menu = new Menu();
            	
            	window.add(menu.createEndGame(window, false, playerName, points));
            	this.setVisible(false);
            	finished = true;
            }

        }

    }

    public void update(){

       player.update();

    }
    
    public String createString(){
    	int hours = time/10;
    	char[] array = String.valueOf(hours).toCharArray();
    	String timeZone = "";
    	
    	if (array.length > 3) {
    		timeZone = String.valueOf(array[0])  +  String.valueOf(array[1])+ ":" +  String.valueOf(array[2]) +  String.valueOf(array[3]);
    	} else if (array.length > 2) {
    		timeZone = "1"  +  String.valueOf(array[0])+ ":" +  String.valueOf(array[1]) +  String.valueOf(array[2]);
    	} else if (array.length > 1) {
    		timeZone = "10:" +  String.valueOf(array[0]) +  String.valueOf(array[1]);
    	} else if (array.length > 0) {
    		timeZone = "10:0" +  String.valueOf(array[0]);
    	}
    	
    	return timeZone;
     }
    
    public void drawTile(int col, int row, int num){
        try {
        	tileM.drawTile(col, row, num);
        }
        catch (Exception e){

        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        
        tileM.draw(g2);
        player.draw(g2);
        
        try {
        	g2.drawImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/inventory/backgroundmissions.png")), 580, 5, 190, 210, null);
        	} catch (IOException e){
                e.printStackTrace();
            }

       
        g2.drawString("Hora: " + createString(), 600, 20);
        g2.drawString("Dinheiro: " + String.valueOf(money), 600, 40);
        g2.drawString("Pontos: " + String.valueOf(points), 600, 60);
        
        g2.drawString(task1.getTile() + String.valueOf(task1.getActual()) + "/" +  String.valueOf(task1.getGoal()), 600, 100);
        g2.drawString(task2.getTile() + String.valueOf(task2.getActual()) + "/" +  String.valueOf(task2.getGoal()), 600, 120);
        g2.drawString(task3.getTile() + String.valueOf(task3.getActual()) + "/" +  String.valueOf(task3.getGoal()), 600, 140);
        g2.drawString(task4.getTile() + String.valueOf(task4.getActual()) + "/" +  String.valueOf(task4.getGoal()), 600, 160);
        g2.drawString(task5.getTile() + String.valueOf(task5.getActual()) + "/" +  String.valueOf(task5.getGoal()), 600, 180);
        g2.drawString(task6.getTile() + String.valueOf(task6.getActual()) + "/" +  String.valueOf(task6.getGoal()), 600, 200);
        
        if (openInventory) {
        
        for (int i = 0; i < inventory.items.size(); i ++) {
        	try {
        	g2.drawImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream(inventory.items.get(i).getImage())), 20 + 86 * i, 500, 64, 64, null);
        	} catch (IOException e){
                e.printStackTrace();
            }
        }
        }
        
        
        g2.dispose();

    }
}
