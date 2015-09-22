package com.lashower.dd.character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javafx.scene.image.Image;

/**
 * @author lashower
 *
 */
public class Character implements Serializable {
	
	public Character(String name, String playerName)
	{
		setCharacterName(name);
		setPlayerName(playerName);
	}

	private static final long serialVersionUID = 1L;

	//Base Information
	private String playerName = "";
	private String characterName = "";
	private String characterClass = "";
	private String race = "";
	private Integer size = 0;
	private String gender = "";
	private String alignment = "";
	private String deity = "";

	//Level Information
	private Integer level = 1;
	
	//Optional Information
	private Integer vision = 0;
	private Calendar birthdate;
	private Integer height = 0;
	private Integer weight = 0;
	private Image image;
	private Integer space = 0;
	private Integer reach = 0;
	private Double age = 0.0;
	
	//Ability Scores
	private ModInt strength = new ModInt(0,0);
	private ModInt dexterity = new ModInt(0,0);
	private ModInt constitution = new ModInt(0,0);
	private ModInt intelligence = new ModInt(0,0);
	private ModInt wisdom = new ModInt(0,0);
	private ModInt charisma = new ModInt(0,0);
	private HashMap<String,ModInt> custom;
	
	//Derived Statistics
	private Integer armorClass = 0;
	private Integer fortitude = 0;
	private Integer reflex = 0;
	private Integer will = 0;
	private Integer meleeSavingThrow = 0;
	private Integer rangedSavingThrow = 0;
	private Integer initiate = 0;
	private Integer baseAttack = 0;
	
	//HP Stuff
	private Integer maxHitPoints = 0;
	private Integer currentHitPoints = 0;

	//Travel Info
	private Integer baseMove = 0;
	private Integer speed = 0;
	
	//Equipment
	private ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	private ArrayList<Armor> armor = new ArrayList<Armor>();
	
	//Other Lists
	private ArrayList<String> languages = new ArrayList<String>();
	private ArrayList<Skill> skills = new ArrayList<Skill>();
	private ArrayList<Ability> abilities = new ArrayList<Ability>();
	
	/**
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}
	/**
	 * @param playerName the playerName to set
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	/**
	 * @return the characterName
	 */
	public String getCharacterName() {
		return characterName;
	}
	/**
	 * @param characterName the characterName to set
	 */
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	/**
	 * @return the characterClass
	 */
	public String getCharacterClass() {
		return characterClass;
	}
	/**
	 * @param characterClass the characterClass to set
	 */
	public void setCharacterClass(String characterClass) {
		this.characterClass = characterClass;
	}
	/**
	 * @return the race
	 */
	public String getRace() {
		return race;
	}
	/**
	 * @param race the race to set
	 */
	public void setRace(String race) {
		this.race = race;
	}
	/**
	 * @return the size
	 */
	public Integer getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(Integer size) {
		this.size = size;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the alignment
	 */
	public String getAlignment() {
		return alignment;
	}
	/**
	 * @param alignment the alignment to set
	 */
	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}
	/**
	 * @return the deity
	 */
	public String getDeity() {
		return deity;
	}
	/**
	 * @param deity the deity to set
	 */
	public void setDeity(String deity) {
		this.deity = deity;
	}
	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
	 * @return the vision
	 */
	public Integer getVision() {
		return vision;
	}
	/**
	 * @param vision the vision to set
	 */
	public void setVision(Integer vision) {
		this.vision = vision;
	}
	/**
	 * @return the birthdate
	 */
	public Calendar getBirthdate() {
		return birthdate;
	}
	/**
	 * @param birthdate the birthdate to set
	 */
	public void setBirthdate(Calendar birthdate) {
		this.birthdate = birthdate;
	}
	/**
	 * @return the height
	 */
	public Integer getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}
	/**
	 * @return the weight
	 */
	public Integer getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}
	/**
	 * @return the space
	 */
	public Integer getSpace() {
		return space;
	}
	/**
	 * @param space the space to set
	 */
	public void setSpace(Integer space) {
		this.space = space;
	}
	/**
	 * @return the reach
	 */
	public Integer getReach() {
		return reach;
	}
	/**
	 * @param reach the reach to set
	 */
	public void setReach(Integer reach) {
		this.reach = reach;
	}
	/**
	 * @return the age
	 */
	public Double getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(Double age) {
		this.age = age;
	}
	/**
	 * @return the strength
	 */
	public Integer getStrength() {
		return strength.getValue();
	}
	/**
	 * @param strength the strength to set
	 */
	public void setStrength(Integer strength) {
		this.strength.setValue(strength);
	}
	
	/**
	 * @return the dexterity
	 */
	public Integer getDexterity() {
		return dexterity.getValue();
	}
	/**
	 * @param dexterity the dexterity to set
	 */
	public void setDexterity(Integer dexterity) {
		this.dexterity.setValue(dexterity);
	}
	/**
	 * @return the constitution
	 */
	public Integer getConstitution() {
		return constitution.getValue();
	}
	/**
	 * @param constitution the constitution to set
	 */
	public void setConstitution(Integer constitution) {
		this.constitution.setValue(constitution);
	}
	/**
	 * @return the intelligence
	 */
	public Integer getIntelligence() {
		return intelligence.getValue();
	}
	/**
	 * @param intelligence the intelligence to set
	 */
	public void setIntelligence(Integer intelligence) {
		this.intelligence.setValue(intelligence);
	}
	/**
	 * @return the wisdom
	 */
	public Integer getWisdom() {
		return wisdom.getValue();
	}
	
	/**
	 * @param wisdom the wisdom to set
	 */
	public void setWisdom(Integer wisdom) {
		this.wisdom.setValue(wisdom);
	}
	/**
	 * @return the charisma
	 */
	public Integer getCharisma() {
		return charisma.getValue();
	}
	/**
	 * @param charisma the charisma to set
	 */
	public void setCharisma(Integer charisma) {
		this.charisma.setValue(charisma);
	}
	/**
	 * @return the maxHitPoints
	 */
	public Integer getMaxHitPoints() {
		return maxHitPoints;
	}
	/**
	 * @param maxHitPoints the maxHitPoints to set
	 */
	public void setMaxHitPoints(Integer maxHitPoints) {
		this.maxHitPoints = maxHitPoints;
	}
	/**
	 * @return the currentHitPoints
	 */
	public Integer getCurrentHitPoints() {
		return currentHitPoints;
	}
	/**
	 * @param currentHitPoints the currentHitPoints to set
	 */
	public void setCurrentHitPoints(Integer currentHitPoints) {
		this.currentHitPoints = currentHitPoints;
	}
	/**
	 * @return the custom
	 */
	public HashMap<String,ModInt> getCustom() {
		return custom;
	}
	/**
	 * @param custom the custom to set
	 */
	public void setCustom(HashMap<String,ModInt> custom) {
		this.custom = custom;
	}
	/**
	 * @return the baseMove
	 */
	public Integer getBaseMove() {
		return baseMove;
	}
	/**
	 * @param baseMove the baseMove to set
	 */
	public void setBaseMove(Integer baseMove) {
		this.baseMove = baseMove;
	}
	/**
	 * @return the speed
	 */
	public Integer getSpeed() {
		return speed;
	}
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	/**
	 * @return the weapons
	 */
	public ArrayList<Weapon> getWeapons() {
		return weapons;
	}
	/**
	 * @param weapons the weapons to set
	 */
	public void setWeapons(ArrayList<Weapon> weapons) {
		this.weapons = weapons;
	}
	/**
	 * @return the armor
	 */
	public ArrayList<Armor> getArmor() {
		return armor;
	}
	/**
	 * @param armor the armor to set
	 */
	public void setArmor(ArrayList<Armor> armor) {
		this.armor = armor;
	}
	/**
	 * @return the languages
	 */
	public ArrayList<String> getLanguages() {
		return languages;
	}
	/**
	 * @param languages the languages to set
	 */
	public void setLanguages(ArrayList<String> languages) {
		this.languages = languages;
	}
	/**
	 * @return the skills
	 */
	public ArrayList<Skill> getSkills() {
		return skills;
	}
	/**
	 * @param skills the skills to set
	 */
	public void setSkills(ArrayList<Skill> skills) {
		this.skills = skills;
	}
	/**
	 * @return the abilities
	 */
	public ArrayList<Ability> getAbilities() {
		return abilities;
	}
	/**
	 * @param abilities the abilities to set
	 */
	public void setAbilities(ArrayList<Ability> abilities) {
		this.abilities = abilities;
	}
	/**
	 * @return the armorClass
	 */
	public Integer getArmorClass() {
		return armorClass;
	}
	/**
	 * @param armorClass the armorClass to set
	 */
	public void setArmorClass(Integer armorClass) {
		this.armorClass = armorClass;
	}
	/**
	 * @return the fortitude
	 */
	public Integer getFortitude() {
		return fortitude;
	}
	/**
	 * @param fortitude the fortitude to set
	 */
	public void setFortitude(Integer fortitude) {
		this.fortitude = fortitude;
	}
	/**
	 * @return the reflex
	 */
	public Integer getReflex() {
		return reflex;
	}
	/**
	 * @param reflex the reflex to set
	 */
	public void setReflex(Integer reflex) {
		this.reflex = reflex;
	}
	/**
	 * @return the will
	 */
	public Integer getWill() {
		return will;
	}
	/**
	 * @param will the will to set
	 */
	public void setWill(Integer will) {
		this.will = will;
	}
	/**
	 * @return the meleeSavingThrow
	 */
	public Integer getMeleeSavingThrow() {
		return meleeSavingThrow;
	}
	/**
	 * @param meleeSavingThrow the meleeSavingThrow to set
	 */
	public void setMeleeSavingThrow(Integer meleeSavingThrow) {
		this.meleeSavingThrow = meleeSavingThrow;
	}
	/**
	 * @return the rangedSavingThrow
	 */
	public Integer getRangedSavingThrow() {
		return rangedSavingThrow;
	}
	/**
	 * @param rangedSavingThrow the rangedSavingThrow to set
	 */
	public void setRangedSavingThrow(Integer rangedSavingThrow) {
		this.rangedSavingThrow = rangedSavingThrow;
	}
	/**
	 * @return the intiate
	 */
	public Integer getInitiate() {
		return initiate;
	}
	/**
	 * @param intiate the initiate to set
	 */
	public void setInitiate(Integer initiate) {
		this.initiate = initiate;
	}
	/**
	 * @return the baseAttack
	 */
	public Integer getBaseAttack() {
		return baseAttack;
	}
	/**
	 * @param baseAttack the baseAttack to set
	 */
	public void setBaseAttack(Integer baseAttack) {
		this.baseAttack = baseAttack;
	}

}