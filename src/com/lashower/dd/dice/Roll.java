/**
 * 
 */
package com.lashower.dd.dice;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author lashower
 *
 */
public class Roll implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Calendar date;
	private Integer max;
	private Integer rollValue;
	private Integer modifier;

	public Roll(int i, int mod, Integer max)
	{
		this.date  = new GregorianCalendar();
		this.rollValue = i;
		this.modifier = mod;
		this.max = max;
	}

	public Roll(Calendar d, int i, Integer max)
	{
		this.date = d;
		this.rollValue = i;
		this.max = max;
	}

	public Calendar getDate() {
		return date;
	}

	public Integer getRollValue() {
		return rollValue;
	}

	public Integer getModifier() {
		return modifier;
	}

	public void setModifier(Integer modifier) {
		this.modifier = modifier;
	}

	public Integer getMax() {
		// TODO Auto-generated method stub
		return max;
	}

}