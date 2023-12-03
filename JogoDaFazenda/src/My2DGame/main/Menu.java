package My2DGame.main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Menu extends JPanel {

	private static final long serialVersionUID = 3321900743769328127L;
	//CONFIGURAÇÕES DE SCREEN
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //FPS
    int FPS = 60;

    public JPanel createMainMenu(JFrame window, JPanel jpanel, JPanel score) {
    	JPanel panel = new JPanel(null);
    	try {
        
        panel.setPreferredSize(new Dimension(screenWidth, screenHeight));
        panel.setLayout(null);
        panel.setVisible(true);

        JLabel background = new JLabel();
        ImageIcon icon = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/menu/background.jpg")));
        Image image = icon.getImage();

        Image resized = image.getScaledInstance(screenWidth, screenHeight,
                Image.SCALE_SMOOTH);
        
        background.setIcon(new ImageIcon(resized));
        background.setBounds(0,0, screenWidth, screenHeight);
        panel.add(background);
        
        JButton playButton = new JButton("Jogar");
        playButton.setBounds(100, 20, 150, 40);
        
        panel.add(playButton);
        
        playButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {            	
            	panel.setVisible(false);
            	window.add(jpanel);
            }
        });
        
        ImageIcon playIcon = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/menu/btplay.jpg")));
        Image playImage = playIcon.getImage();

        Image playResized = playImage.getScaledInstance(150, 40,
                Image.SCALE_SMOOTH);
        
        playButton.setIcon(new ImageIcon(playResized));
        panel.setComponentZOrder(playButton, 0);
        
        
        JButton scoreButton = new JButton("Placar");
        scoreButton.setBounds(100, 65, 150, 40);
        panel.add(scoreButton);
        
        scoreButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {            	
            	panel.setVisible(false);
            	window.add(score);
            }
        });
        
        ImageIcon scoreIcon = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/menu/btscore.jpg")));
        Image scoreImage = scoreIcon.getImage();

        Image scoreResized = scoreImage.getScaledInstance(150, 40,
                Image.SCALE_SMOOTH);
        
        scoreButton.setIcon(new ImageIcon(scoreResized));
        panel.setComponentZOrder(scoreButton, 0);
        
        JButton exitButton = new JButton("Sair");
        exitButton.setBounds(100, 110, 150, 40);
        panel.add(exitButton);
        
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {            	
            	System.exit(0);
            }
        });
        
        ImageIcon exitIcon = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/menu/btexit.jpg")));
        Image exitImage = exitIcon.getImage();

        Image exitResized = exitImage.getScaledInstance(150, 40,
                Image.SCALE_SMOOTH);
        
        exitButton.setIcon(new ImageIcon(exitResized));
        panel.setComponentZOrder(scoreButton, 0);
        
        return panel;
    	}
    
    catch (IOException e){
        e.printStackTrace();
    }
		return panel;
    
    }
    
    public JPanel createRegisterScreen(JFrame window, JPanel jpanel) {
        JPanel panel = new JPanel();
        try {
        panel.setPreferredSize(new Dimension(screenWidth, screenHeight));
        panel.setLayout(null);
        panel.setVisible(true);
        
        JLabel background = new JLabel();
        ImageIcon icon = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/menu/background.jpg")));
        Image image = icon.getImage();

        Image resized = image.getScaledInstance(screenWidth, screenHeight,
                Image.SCALE_SMOOTH);
        
        background.setIcon(new ImageIcon(resized));
        background.setBounds(0,0, screenWidth, screenHeight);
        panel.add(background);
        
        JLabel nameLabel = new JLabel("Nome do Jogador");
        nameLabel.setBounds(100, 20, 150, 40);
        panel.add(nameLabel);
        panel.setComponentZOrder(nameLabel, 0);
        
        JTextField name = new JTextField();
        name.setBounds(100, 65, 150, 40);
        panel.add(name);
        
        JLabel farmLabel = new JLabel("Nome da Fazenda");
        farmLabel.setBounds(100, 120, 150, 40);
        panel.add(farmLabel);
        panel.setComponentZOrder(farmLabel, 0);
        
        JTextField farm = new JTextField();
        farm.setBounds(100, 165, 150, 40);
        panel.add(farm);
        
        JPanel grid = new JPanel(new GridLayout(1,2));
        
        JButton backButton = new JButton("Voltar");
        backButton.setBounds(50, 500, 150, 40);
        grid.add(backButton);
        
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {            	
            	panel.setVisible(false);
            	window.add(createMainMenu(window, createRegisterScreen(window, jpanel), createScoreScreen(window, jpanel)));
            }
        });
        
        
        ImageIcon exitIcon = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/menu/btexit.jpg")));
        Image exitImage = exitIcon.getImage();

        Image exitResized = exitImage.getScaledInstance(150, 40,
                Image.SCALE_SMOOTH);
        
        backButton.setIcon(new ImageIcon(exitResized));
        panel.setComponentZOrder(backButton, 0);

        JButton playButton = new JButton("Jogar");
        playButton.setBounds(250, 500, 150, 40);
        grid.add(playButton);
        
        playButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter("score.txt", true));
					
					writer.write(name.getText());
					writer.newLine();
	            	writer.close();
	            	
	            	panel.setVisible(false);
	            	window.add(jpanel);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        
        ImageIcon playIcon = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/menu/btplay.jpg")));
        Image playImage = playIcon.getImage();

        Image playResized = playImage.getScaledInstance(150, 40,
                Image.SCALE_SMOOTH);
        
        playButton.setIcon(new ImageIcon(playResized));
        panel.setComponentZOrder(playButton, 0);
        
        panel.add(grid);
        } catch (IOException e){
            e.printStackTrace();
        }
        
        return panel;
    }
    
    public JPanel createScoreScreen(JFrame window, JPanel jpanel) {
        JPanel panel = new JPanel();
        try {
        panel.setPreferredSize(new Dimension(screenWidth, screenHeight));
        panel.setLayout(null);
        panel.setVisible(true);
        
        JLabel background = new JLabel();
        ImageIcon icon = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/menu/background.jpg")));
        Image image = icon.getImage();

        Image resized = image.getScaledInstance(screenWidth, screenHeight,
                Image.SCALE_SMOOTH);
        
        background.setIcon(new ImageIcon(resized));
        background.setBounds(0,0, screenWidth, screenHeight);
        panel.add(background);
        
        
        try {
			BufferedReader reader = new BufferedReader(new FileReader("score.txt"));
			
			JPanel scrollPannel = new JPanel();
	        JScrollPane scroll = new JScrollPane(scrollPannel);
	        scrollPannel.setBounds(50, 50, 400, 400);
	        scrollPannel.setLayout(new GridLayout(20, 1));
	        scrollPannel.setAutoscrolls(true);
			
	        while (reader.ready()) { 
	        	JLabel text = new JLabel(reader.readLine());
	        	
	        	scrollPannel.add(text);
	        }
	        
	        reader.close();
	        panel.setComponentZOrder(scrollPannel, 0);
	        panel.add(scroll);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        JButton backButton = new JButton("Voltar");
        backButton.setBounds(250, 500, 150, 40);
        panel.add(backButton);
        
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {            	
            	panel.setVisible(false);
            	window.add(createMainMenu(window, createRegisterScreen(window, jpanel), createScoreScreen(window, jpanel)));
            }
        });
        
        ImageIcon exitIcon = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/menu/btexit.jpg")));
        Image exitImage = exitIcon.getImage();

        Image exitResized = exitImage.getScaledInstance(150, 40,
                Image.SCALE_SMOOTH);
        
        backButton.setIcon(new ImageIcon(exitResized));
        panel.setComponentZOrder(backButton, 0);
        
        } catch (IOException e){
            e.printStackTrace();
        }
        
        return panel;
    }

}
