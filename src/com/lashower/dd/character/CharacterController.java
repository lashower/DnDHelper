package com.lashower.dd.character;

import java.net.URL;
import java.util.ResourceBundle;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import com.lashower.dd.Main;

public class CharacterController {
	
	private Character c = Main.getCampaign().getCharacter();
    private ScriptEngineManager mgr = new ScriptEngineManager();
    private ScriptEngine engine = mgr.getEngineByName("JavaScript");

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="charClass"
    private TextField charClass; // Value injected by FXMLLoader

    @FXML // fx:id="charLevel"
    private TextField charLevel; // Value injected by FXMLLoader

    @FXML // fx:id="playerName"
    private TextField playerName; // Value injected by FXMLLoader

    @FXML // fx:id="charName"
    private TextField charName; // Value injected by FXMLLoader

    @FXML // fx:id="charAlign"
    private TextField charAlign; // Value injected by FXMLLoader

    @FXML // fx:id="charDeity"
    private TextField charDeity; // Value injected by FXMLLoader

    @FXML // fx:id="currHP"
    private TextField currHP; // Value injected by FXMLLoader

    @FXML // fx:id="maxHP"
    private TextField maxHP; // Value injected by FXMLLoader

    @FXML // fx:id="AC"
    private TextField AC; // Value injected by FXMLLoader

    @FXML // fx:id="FORT"
    private TextField FORT; // Value injected by FXMLLoader

    @FXML // fx:id="REFLX"
    private TextField REFLX; // Value injected by FXMLLoader

    @FXML // fx:id="WILL"
    private TextField WILL; // Value injected by FXMLLoader

    @FXML // fx:id="Melee"
    private TextField Melee; // Value injected by FXMLLoader

    @FXML // fx:id="Ranged"
    private TextField Ranged; // Value injected by FXMLLoader

    @FXML // fx:id="Init"
    private TextField Init; // Value injected by FXMLLoader

    @FXML // fx:id="baseAttack"
    private TextField baseAttack; // Value injected by FXMLLoader

    @FXML // fx:id="str"
    private TextField str; // Value injected by FXMLLoader

    @FXML // fx:id="dex"
    private TextField dex; // Value injected by FXMLLoader

    @FXML // fx:id="con"
    private TextField con; // Value injected by FXMLLoader

    @FXML // fx:id="charInt"
    private TextField charInt; // Value injected by FXMLLoader

    @FXML // fx:id="wis"
    private TextField wis; // Value injected by FXMLLoader

    @FXML // fx:id="cha"
    private TextField cha; // Value injected by FXMLLoader

    @FXML // fx:id="strMod"
    private TextField strMod; // Value injected by FXMLLoader

    @FXML // fx:id="dexMod"
    private TextField dexMod; // Value injected by FXMLLoader

    @FXML // fx:id="conMod"
    private TextField conMod; // Value injected by FXMLLoader

    @FXML // fx:id="intMod"
    private TextField intMod; // Value injected by FXMLLoader

    @FXML // fx:id="wisMod"
    private TextField wisMod; // Value injected by FXMLLoader

    @FXML // fx:id="chaMod"
    private TextField chaMod; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert charClass != null : "fx:id=\"charClass\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert charLevel != null : "fx:id=\"charLevel\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert playerName != null : "fx:id=\"playerName\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert charName != null : "fx:id=\"charName\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert charAlign != null : "fx:id=\"charAlign\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert charDeity != null : "fx:id=\"charDeity\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert currHP != null : "fx:id=\"currHP\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert maxHP != null : "fx:id=\"maxHP\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert AC != null : "fx:id=\"AC\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert FORT != null : "fx:id=\"FORT\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert REFLX != null : "fx:id=\"REFLX\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert WILL != null : "fx:id=\"WILL\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert Melee != null : "fx:id=\"Melee\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert Ranged != null : "fx:id=\"Ranged\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert Init != null : "fx:id=\"Init\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert baseAttack != null : "fx:id=\"baseAttack\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert str != null : "fx:id=\"str\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert dex != null : "fx:id=\"dex\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert con != null : "fx:id=\"con\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert charInt != null : "fx:id=\"charInt\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert wis != null : "fx:id=\"wis\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert cha != null : "fx:id=\"cha\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert strMod != null : "fx:id=\"strMod\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert dexMod != null : "fx:id=\"dexMod\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert conMod != null : "fx:id=\"conMod\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert intMod != null : "fx:id=\"intMod\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert wisMod != null : "fx:id=\"wisMod\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        assert chaMod != null : "fx:id=\"chaMod\" was not injected: check your FXML file 'CharacterPane.fxml'.";
        

        charName.setText(c.getCharacterName());
        charName.textProperty().addListener((observable, oldValue, newValue) -> {
        	Main.getCampaign().getCharacter().setCharacterName(newValue);
        	Main.setName();
        });
        
        playerName.setText(c.getPlayerName());
        playerName.textProperty().addListener((observable, oldValue, newValue) -> {
        	Main.getCampaign().getCharacter().setPlayerName(newValue);
        	Main.setName();
        });

        charClass.setText(c.getCharacterClass());
        charClass.textProperty().addListener((observable, oldValue, newValue) -> {
        	Main.getCampaign().getCharacter().setCharacterClass(newValue);
        });

        charLevel.setText(c.getLevel().toString());
        charLevel.textProperty().addListener((observable, oldValue, newValue) -> {
        	Main.getCampaign().getCharacter().setLevel(Integer.parseInt(newValue));
        });

        charAlign.setText(c.getAlignment());
        charAlign.textProperty().addListener((observable, oldValue, newValue) -> {
        	Main.getCampaign().getCharacter().setAlignment(newValue);
        });

        charDeity.setText(c.getDeity());
        charDeity.textProperty().addListener((observable, oldValue, newValue) -> {
        	Main.getCampaign().getCharacter().setDeity(newValue);
        });

    	currHP.setText(Integer.toString(c.getCurrentHitPoints()));
        currHP.textProperty().addListener((observable, oldValue, newValue) -> {
        	try {
	        	if(!newValue.matches("(.*)[+\\-*/.](.*)"))
	        	{
	        		c.setCurrentHitPoints(Integer.parseInt(newValue));
	        	}
        	} catch(NumberFormatException e) {}
        });
        currHP.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if(ke.getCode() == KeyCode.ENTER)
                {
                    try {
                    	currHP.setText(engine.eval(currHP.getText()).toString());
                    } catch(Exception e) { }
                }
            }
        });

    	maxHP.setText(c.getMaxHitPoints().toString());
        maxHP.textProperty().addListener((observable, oldValue, newValue) -> {
        	try {
        		c.setMaxHitPoints(Integer.parseInt(newValue));
        	} catch(NumberFormatException e) {}
        });

        AC.setText(c.getArmorClass().toString());
        AC.textProperty().addListener((observable, oldValue, newValue) -> {
        	try {
        	c.setArmorClass(Integer.parseInt(newValue));
        	} catch(NumberFormatException e) {}

        });

        FORT.setText(c.getFortitude().toString());
        FORT.textProperty().addListener((observable, oldValue, newValue) -> {
        	try {
        	c.setFortitude(Integer.parseInt(newValue));
        	} catch(NumberFormatException e) {}
        });

        REFLX.setText(c.getReflex().toString());
        REFLX.textProperty().addListener((observable, oldValue, newValue) -> {
        	try {
        	c.setReflex(Integer.parseInt(newValue));
        	} catch(NumberFormatException e) {}
        });

        WILL.setText(c.getWill().toString());
        WILL.textProperty().addListener((observable, oldValue, newValue) -> {
        	try {
        	c.setWill(Integer.parseInt(newValue));
        	} catch(NumberFormatException e) {}
        });

        Melee.setText(c.getMeleeSavingThrow().toString());
        Melee.textProperty().addListener((observable, oldValue, newValue) -> {
        	try {
        	c.setMeleeSavingThrow(Integer.parseInt(newValue));
        	} catch(NumberFormatException e) {}
        });

        Ranged.setText(c.getRangedSavingThrow().toString());
        Ranged.textProperty().addListener((observable, oldValue, newValue) -> {
        	try {
        	c.setRangedSavingThrow(Integer.parseInt(newValue));
        	} catch(NumberFormatException e) {}
        });

        Init.setText(c.getInitiate().toString());
        Init.textProperty().addListener((observable, oldValue, newValue) -> {
        	try {
        	c.setInitiate(Integer.parseInt(newValue));
        	} catch(NumberFormatException e) {}
        });

        baseAttack.setText(c.getBaseAttack().toString());
        baseAttack.textProperty().addListener((observable, oldValue, newValue) -> {
        	try {
        	c.setBaseAttack(Integer.parseInt(newValue));
        	} catch(NumberFormatException e) {}
        });

        str.setText(c.getStrength().toString());
        str.textProperty().addListener((observable, oldValue, newValue) -> {
        	try {
        	c.setStrength(Integer.parseInt(newValue));
        	} catch(NumberFormatException e) {}
        });

        dex.setText(c.getDexterity().toString());
        dex.textProperty().addListener((observable, oldValue, newValue) -> {
        	try {
        	c.setDexterity(Integer.parseInt(newValue));
        	} catch(NumberFormatException e) {}
        });

        con.setText(c.getConstitution().toString());
        con.textProperty().addListener((observable, oldValue, newValue) -> {
        	try {
        	c.setConstitution(Integer.parseInt(newValue));
        	} catch(NumberFormatException e) {}
        });

        charInt.setText(c.getIntelligence().toString());
        charInt.textProperty().addListener((observable, oldValue, newValue) -> {
        	try {
        	c.setIntelligence(Integer.parseInt(newValue));
        	} catch(NumberFormatException e) {}
        });

        wis.setText(c.getWisdom().toString());
        wis.textProperty().addListener((observable, oldValue, newValue) -> {
        	try {
        	c.setWisdom(Integer.parseInt(newValue));
        	} catch(NumberFormatException e) {}
        });

        cha.setText(c.getCharisma().toString());
        cha.textProperty().addListener((observable, oldValue, newValue) -> {
        	try {
        	c.setCharisma(Integer.parseInt(newValue));
        	} catch(NumberFormatException e) {}
        });

//        strMod.setText(c.get);
//        strMod.textProperty().addListener((observable, oldValue, newValue) -> {
//        	c.set(newValue);
//        });
//
//        dexMod.setText(c.get);
//        dexMod.textProperty().addListener((observable, oldValue, newValue) -> {
//        	c.set(newValue);
//        });
//
//        conMod.setText(c.get);
//        conMod.textProperty().addListener((observable, oldValue, newValue) -> {
//        	c.set(newValue);
//        });
//
//        intMod.setText(c.get);
//        intMod.textProperty().addListener((observable, oldValue, newValue) -> {
//        	c.set(newValue);
//        });
//
//        wisMod.setText(c.get);
//        wisMod.textProperty().addListener((observable, oldValue, newValue) -> {
//        	c.set(newValue);
//        });
//
//        chaMod.setText(c.get);
//        chaMod.textProperty().addListener((observable, oldValue, newValue) -> {
//        	c.set(newValue);
//        });

    }
}