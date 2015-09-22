/**
 * 
 */
package com.lashower.dd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import com.lashower.dd.dice.Dice;
import com.lashower.dd.character.Character;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author lashower
 *
 */
public class Campaign implements Serializable {
	
	private static final long serialVersionUID = -7560788981736016527L;
	private transient ObservableList<Dice> diceList = FXCollections.observableArrayList();
	private Character character = new Character("","");
	private ArrayList<Dice> simpleList = new ArrayList<Dice>();
	private boolean handicap = false;
	

	public Campaign()
	{
		
	}

	public Campaign(String filePath)
	{
		System.out.println(filePath);
	}

	public Campaign(File file)
	{
		System.out.println(file.getPath());
	}
	
	public void save(String filePath)
	{
		System.out.println(filePath);
		save(new File(filePath));
	}
	
	public void save(File file)
	{
		simpleList = new ArrayList<Dice>();
		for(int i = 0; i < diceList.size();i++)
		{
			simpleList.add(diceList.get(i));
		}
		try{
			file.delete();
			FileOutputStream fout = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(this);
			oos.close();
			System.out.println("Done");
		} catch(Exception ex){
			ex.printStackTrace();
		}
		simpleList = null;
	}

	public void load(String filePath) {
		load(new File(filePath));
	}

	public Campaign load(File file)
	{
		System.out.println(file.getPath());
		Campaign c = null;
		try
		{
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			c = (Campaign) ois.readObject();
			ois.close();
			fis.close();
			c.diceList  = FXCollections.observableArrayList();
			while(!c.simpleList.isEmpty())
			{
				c.diceList.add(c.simpleList.remove(0));
			}
			c.simpleList = null;
		} catch(IOException ioe){
			ioe.printStackTrace();
		} catch(ClassNotFoundException ce){
			System.out.println("Class not found");
			ce.printStackTrace();
		}
		return c;
	}
	
	/**
	 * 
	 * @return
	 */
	public ObservableList<Dice> getDiceList() {
		return diceList;
	}
	
	/**
	 * 
	 * @param diceList
	 */
	public void setDiceList(ObservableList<Dice> diceList) {
		this.diceList = diceList;
	}
	
	/**
	 * @return the handicap
	 */
	public boolean isHandicap() {
		return handicap;
	}
	
	/**
	 * @param handicap the handicap to set
	 */
	public void setHandicap(boolean handicap) {
		this.handicap = handicap;
	}
	
	public void flipHandicap()
	{
		handicap = !handicap;
		Iterator<Dice> dit = diceList.iterator();
		while(dit.hasNext())
		{
			dit.next().setHandicap(handicap);
		}
    }

	/**
	 * @return the character
	 */
	public Character getCharacter() {
		return character;
	}

	/**
	 * @param character the character to set
	 */
	public void setCharacter(Character character) {
		this.character = character;
	}

}