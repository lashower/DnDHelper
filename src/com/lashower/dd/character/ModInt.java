/**
 * 
 */
package com.lashower.dd.character;

import java.io.Serializable;

/**
 * @author lashower
 *
 */
public class ModInt implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer value;
	private Integer mod;

	public ModInt(Integer value)
	{
		new ModInt(value,0);
	}

	public ModInt(Integer value, Integer mod)
	{
		this.setValue(value);
		this.setMod(mod);
	}

	/**
	 * @return the value
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Integer value) {
		this.value = value;
	}

	/**
	 * @return the mod
	 */
	public Integer getMod() {
		return mod;
	}

	/**
	 * @param mod the mod to set
	 */
	public void setMod(Integer mod) {
		this.mod = mod;
	}

}
