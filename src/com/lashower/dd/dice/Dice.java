package com.lashower.dd.dice;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import com.lashower.dd.Main;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author lashower
 *
 */
public class Dice implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Roll> history = new ArrayList<Roll>();
    private HashMap<String, Object> saveProps = new HashMap<String, Object>();
	private transient BooleanProperty handicap = new SimpleBooleanProperty();
	private transient IntegerProperty min = new SimpleIntegerProperty(1);
	private transient IntegerProperty max = new SimpleIntegerProperty();
	private transient StringProperty name = new SimpleStringProperty();
	private transient StringProperty type = new SimpleStringProperty();
	private transient IntegerProperty rollCount = new SimpleIntegerProperty(1);
	private transient IntegerProperty modifier = new SimpleIntegerProperty(0);
	private transient BooleanProperty locked = new SimpleBooleanProperty();
    private transient IntegerProperty lastTotal  = new SimpleIntegerProperty();
    
    private Random rand = new Random();
    private Long id;
	
	public Dice(String name,Integer max)
	{
		new Dice(name,max,0,false,false);
	}

	public Dice(String name, Integer max, Integer modifier, Boolean handicap, Boolean locked)
	{
		this.setName(name);
		this.setMax(max);
		this.setHandicap(handicap);
		this.setModifier(modifier);
		this.setLocked(locked);
		this.id = new Date().getTime();
	}

	/**
	 * Returns a pseudo-random number between min and max, inclusive.
	 * The difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min Minimum value
	 * @param max Maximum value.  Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(Integer)
	 */
	public Integer roll() {
		if(!locked.get())
		{
			Integer total = 0;
			for(int i = 0; i < getRollCount(); i++)
			{
			    // nextInt is normally exclusive of the top value,
			    // so add 1 to make it inclusive
			    Integer roll = (rand.nextInt((getMax() - getMin()) + 1) + getMin() + rand.nextInt((getMax() - getMin()) + 1) + getMin())/2;
			    if(getHandicap())
			    {
			    	Integer roll2 = (int) Math.ceil(Math.random()*getMax());
			    	roll = (roll > roll2) ? roll : roll2;
			    }
			    total += roll;
			}
			this.history.add(new Roll(total,getModifier(),getMax()));
			lastTotal.set(total + getModifier());
		    return total + getModifier();
		} else
		{
			return null;
		}
	}

	private Integer getMin() {
		return min.get();
	}

	public Boolean getHandicap()
	{
		return handicap.get();
	}

	public void setHandicap(Boolean handicap)
	{
		this.handicap.set(handicap);
	}

	public ArrayList<Roll> getHistory() {
		return history;
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public Integer getMax() {
		return max.get();
	}

	public void setMax(Integer max) {
		this.max.set(max);
	}

	public Integer getModifier() {
		return modifier.get();
	}

	public void setModifier(Integer modifier) {
		this.modifier.set(modifier);
	}

	public Integer getLastTotal() {
		return lastTotal.get();
	}

	public Long getId() {
		return id;
	}
	
	public void flipLocked()
	{
		this.locked.set(!getLocked());
	}

	public Boolean getLocked()
	{
		return locked.getValue();
	}
	
	public void setLocked(Boolean locked)
	{
		this.locked.set(locked);
	}
	
	//Propery Exports
//	public IntegerProperty getMinProperty() { return min; }
	public BooleanProperty getHandicapProperty() { return handicap; }
	public StringProperty getNameProperty() { return name; }
	public StringProperty getTypeProperty() { return type; }
	public IntegerProperty getMaxProperty() { return max; }
	public IntegerProperty getModifierProperty() { return modifier; }
	public IntegerProperty getLastTotalProperty() { return lastTotal; }
	public BooleanProperty getLockedProperty() { return locked;	}
	public IntegerProperty getRollCountProperty() {	return rollCount; }

	/**
	 * @return the rollCount
	 */
	public Integer getRollCount() {
		return rollCount.get();
	}

	/**
	 * @param rollCount the rollCount to set
	 */
	public void setRollCount(Integer rollCount) {
		this.rollCount.set(rollCount);
	}
	

	/**
	 * @return the type
	 */
	public String getType() {
		return type.getValue();
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type.set(type);
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException
	{
		saveProps = new HashMap<String, Object>();
		saveProps.put("min", this.getMin());
		saveProps.put("max", this.getMax());
		saveProps.put("name", this.getName());
		saveProps.put("rollCount", this.getRollCount());
		saveProps.put("modifier", this.getModifier());
		saveProps.put("locked", this.getLocked());
		saveProps.put("lastTotal", this.getLastTotal());
		saveProps.put("type", this.getType());
		out.defaultWriteObject();
	}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		in.defaultReadObject();
		this.min = new SimpleIntegerProperty((Integer) saveProps.get("min"));
		this.max = new SimpleIntegerProperty((Integer) saveProps.get("max"));
		this.name = new SimpleStringProperty((String) saveProps.get("name"));
		this.type = new SimpleStringProperty((String) saveProps.get("type"));
		this.rollCount = new SimpleIntegerProperty((Integer) saveProps.get("rollCount"));
		this.modifier = new SimpleIntegerProperty((Integer) saveProps.get("modifier"));
		this.locked = new SimpleBooleanProperty((Boolean) saveProps.get("locked"));
		this.lastTotal = new SimpleIntegerProperty((Integer) saveProps.get("lastTotal"));
		this.handicap = new SimpleBooleanProperty(Main.getCampaign().isHandicap());
	}
}