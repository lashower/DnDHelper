/**
 * 
 */
package com.lashower.dd.character;

import java.io.Serializable;

/**
 * @author lashower
 *
 */
public class Weapon implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer rollCount;
	private Integer maxDamage;
	private Double weight;
	private Integer grade;
	private Integer enchantment;
	/**
	 * @return the rollCount
	 */
	public Integer getRollCount() {
		return rollCount;
	}
	/**
	 * @param rollCount the rollCount to set
	 */
	public void setRollCount(Integer rollCount) {
		this.rollCount = rollCount;
	}
	/**
	 * @return the maxDamage
	 */
	public Integer getMaxDamage() {
		return maxDamage;
	}
	/**
	 * @param maxDamage the maxDamage to set
	 */
	public void setMaxDamage(Integer maxDamage) {
		this.maxDamage = maxDamage;
	}
	/**
	 * @return the weight
	 */
	public Double getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	/**
	 * @return the grade
	 */
	public Integer getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	/**
	 * @return the enchantment
	 */
	public Integer getEnchantment() {
		return enchantment;
	}
	/**
	 * @param enchantment the enchantment to set
	 */
	public void setEnchantment(Integer enchantment) {
		this.enchantment = enchantment;
	}

}