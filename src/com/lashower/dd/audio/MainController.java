/**
 * 
 */
package com.lashower.dd.audio;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
 * @author lashower
 *
 */
public class MainController extends AnchorPane implements Initializable {

    @FXML // fx:id="saveMenu"
    private MenuItem saveMenu; // Value injected by FXMLLoader

    @FXML // fx:id="saveAsMenu"
    private MenuItem saveAsMenu; // Value injected by FXMLLoader

    @FXML // fx:id="loadMenu"
    private MenuItem loadMenu; // Value injected by FXMLLoader
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setupMenus();
	}

	private void setupMenus() {
        assert loadMenu != null : "fx:id=\"loadMenu\" was not injected: check your FXML file 'D&DHelper.fxml'.";
        assert saveMenu != null : "fx:id=\"saveMenu\" was not injected: check your FXML file 'D&DHelper.fxml'.";
        assert saveAsMenu != null : "fx:id=\"saveAsMenu\" was not injected: check your FXML file 'D&DHelper.fxml'.";
        
        loadMenu.setOnAction(
                new EventHandler<ActionEvent>() {
                    public void handle(final ActionEvent e) {
                    	load();
                    }
                });

        saveAsMenu.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(final ActionEvent e) {
        		save(true);
        	}
        });

        saveMenu.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                    	save(false);
                    }
                });
        
		
	}

	protected void save(boolean b) {
		// TODO Auto-generated method stub
		
	}

	protected void load() {
		// TODO Auto-generated method stub
		
	}

}
