package tile;

import My2DGame.main.GamePainel;

import javax.imageio.ImageIO;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePainel gp;
    public Tile[] tile;
     public int mapTileNum[][];

    public TileManager(GamePainel gp){
        this.gp = gp;

        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap();
    }
    
    public void getTileImage(){
        try{

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/tiles/water.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/tiles/sand.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/tiles/tree.png"));
            tile[5].collision = true;

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/maps/casa.png"));
            tile[6].collision = true;
            
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/maps/comercio.png"));
            tile[7].collision = false;
            
            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/tiles/grass.png"));
            tile[8].collision = true;
            
            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/maps/galinha.png"));
            tile[9].collision = true;
            
            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/maps/ovelha.png"));
            tile[10].collision = true;
            
            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/maps/vaca.png"));
            tile[11].collision = true;
            
            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/tiles/sand.png"));
            
            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/tiles/sand.png"));
            
            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/tiles/sand.png"));
            
            tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/tiles/seed.png"));

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/tiles/plant.png"));


        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(){
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("res/maps/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow){

                String line = br.readLine();

                while (col < gp.maxWorldCol){

                    String numbers[] = line.split(" ");
                    

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch (Exception e){

        }
    }
    
    public void drawTile(int col, int row, int num){
        try {
        	mapTileNum[col][row] = num;        
        }
        catch (Exception e){

        }
    }
    
    public void draw(Graphics2D g2){

       int worldCol = 0;
       int worldRow = 0;


       while(worldCol < gp.maxWorldCol  && worldRow < gp.maxWorldRow){

           int tileNum = mapTileNum[worldCol][worldRow];

           int worldX = worldCol * gp.tileSize;
           int worldY = worldRow * gp.tileSize;
           int screenX = worldX - gp.player.worldX + gp.player.screenX;
           int screenY = worldY - gp.player.worldY + gp.player.screenY;


           if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

               g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

           }
           
           g2.drawImage(tile[6].image, 800 - gp.player.worldX + gp.player.screenX,400 - gp.player.worldY + gp.player.screenY, 250, 250, null);
           
           g2.drawImage(tile[7].image, 1100 - gp.player.worldX + gp.player.screenX,400 - gp.player.worldY + gp.player.screenY, 250, 250, null);
           
           g2.drawImage(tile[9].image, 1000 - gp.player.worldX + gp.player.screenX,1000 - gp.player.worldY + gp.player.screenY, 48, 48, null);
           g2.drawImage(tile[9].image, 950 - gp.player.worldX + gp.player.screenX,1050 - gp.player.worldY + gp.player.screenY, 48, 48, null);
           g2.drawImage(tile[9].image, 1050 - gp.player.worldX + gp.player.screenX,1020 - gp.player.worldY + gp.player.screenY, 48, 48, null);
           g2.drawImage(tile[9].image, 1020 - gp.player.worldX + gp.player.screenX,1100 - gp.player.worldY + gp.player.screenY, 48, 48, null);
           g2.drawImage(tile[10].image, 1170 - gp.player.worldX + gp.player.screenX,1000 - gp.player.worldY + gp.player.screenY, 64, 64, null);
           g2.drawImage(tile[10].image, 1170 - gp.player.worldX + gp.player.screenX,1080 - gp.player.worldY + gp.player.screenY, 64, 64, null);
           g2.drawImage(tile[11].image, 1300 - gp.player.worldX + gp.player.screenX,950 - gp.player.worldY + gp.player.screenY, 96, 96, null);
           g2.drawImage(tile[11].image, 1300 - gp.player.worldX + gp.player.screenX,1050 - gp.player.worldY + gp.player.screenY, 96, 96, null);
           

           worldCol++;


           if(worldCol == gp.maxWorldCol){
               worldCol = 0;
               worldRow++;

           }

       }     

    }


}
