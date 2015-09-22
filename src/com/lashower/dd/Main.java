package com.lashower.dd;
	
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import com.lashower.dd.dice.Dice;
import com.lashower.dd.dice.DiceController;


public class Main extends Application {

	private static Campaign camp = new Campaign();
	
	protected static SimpleDateFormat dateFormat = new SimpleDateFormat("E k:mm:ss.S");
	
	private static AudioClip roll;

	private static URL fxml;
	private static Stage s;
	private static File saveFile; 

    public void start(Stage stage) throws Exception 
    {
    	s = stage;
    	fxml = getClass().getResource("D&DHelper.fxml");
		Parent root = FXMLLoader.load(fxml);
		Scene scene = new Scene(root);
		stage.setTitle("D&D Helper");
		stage.setScene(scene);
		stage.show();
		roll = new AudioClip(getClass().getResource("roll.wav").toString());
    }
    
	public static void main(String[] args)
	{
		launch(args);
	}
	
	public static HashMap<Integer,Integer> roll()
	{
		HashMap<Integer, Integer> calcs = new HashMap<Integer, Integer>(); 
		Integer total = 0;
		Integer max = Integer.MIN_VALUE;
		Integer min = Integer.MAX_VALUE;
		Integer rollCount;
		Integer r;
		Iterator<Dice> dit = camp.getDiceList().iterator();
		for(rollCount = 0; dit.hasNext(); rollCount++)
		{
			r = dit.next().roll();
			if(r != null)
			{
				total += r;
				if(max < r)
				{
					max = r;
				}
				if(min > r)
				{
					min = r;
				}
			}
		}
		if(camp.getDiceList().size() > 0)
		{
			roll.play();
		}
		if(rollCount > 0)
		{
			calcs.put(DiceController.MAXCOLUM, max);
			calcs.put(DiceController.MINCOLUM, min);
			calcs.put(DiceController.AVGCOLUMN, total/rollCount);
			calcs.put(DiceController.TOTALCOLUMN, total);
		}
		return calcs;
	}
	
	public static void load() throws IOException
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Load Campaign");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Campaign files (*.cmpgn)", "*.cmpgn");
		fileChooser.getExtensionFilters().add(extFilter);
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		File file = fileChooser.showOpenDialog(s);
		if(file != null)
		{
			saveFile = file;
			camp = camp.load(file);
			s.close();
			Parent root = FXMLLoader.load(fxml);
			Scene scene = new Scene(root);
			s.setTitle("D&D Helper");
			s.setScene(scene);
			s.show();
			setName();
		}
	}
	
	public static void save(Boolean newFile)
	{
		if(newFile || saveFile == null)
		{
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save Campaign");
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Campaign files (*.cmpgn)", "*.cmpgn");
			fileChooser.getExtensionFilters().add(extFilter);
			fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
			fileChooser.setInitialFileName(camp.getCharacter().getPlayerName() + " - " + camp.getCharacter().getCharacterName());
			saveFile = fileChooser.showSaveDialog(s);
		}
		if(saveFile != null)
		{
			camp.save(saveFile);
		}
	}
	
	public static void setName()
	{
		s.setTitle("D&D Helper: " + (camp.getCharacter().getPlayerName().isEmpty() ? "" : camp.getCharacter().getPlayerName() + " - ") + camp.getCharacter().getCharacterName());
	}

	/**
	 * @return the d
	 */
	public static Campaign getCampaign() {
		return camp;
	}
}