package com.lashower.dd.dice;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;

import com.lashower.dd.Main;

public class DiceController extends AnchorPane implements Initializable {

	private ObservableList<Dice> data  = FXCollections.observableList(Main.getCampaign().getDiceList());
	private BackgroundImage lock = null;
	private Boolean keepValues = false;
	
	private Dice selInst;
	private Calendar today = Calendar.getInstance();
	
	public static final Integer TOTALCOLUMN = 0;
	public static final Integer AVGCOLUMN = 1;
	public static final Integer MINCOLUM = 2;
	public static final Integer MAXCOLUM = 3;
	
	private HashMap<Integer,Integer> calc = new HashMap<Integer,Integer>();
	
    @FXML // fx:id="noReset"
    private CheckBox noReset; // Value injected by FXMLLoader
    
    @FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

    @FXML // fx:id="diceDisable"
    private CheckBox diceDisable; // Value injected by FXMLLoader
    
    @FXML // fx:id="diceName"
	private TextField diceName; // Value injected by FXMLLoader
    
    @FXML // fx:id="diceType"
    private TextField diceType; // Value injected by FXMLLoader
    
	@FXML // fx:id="diceMax"
	private TextField diceMax; // Value injected by FXMLLoader

	@FXML // fx:id="diceMod"
	private TextField diceMod; // Value injected by FXMLLoader

	@FXML // fx:id="diceAdd"
	private Button diceAdd; // Value injected by FXMLLoader

	@FXML // fx:id="diceUpdate"
	private Button diceUpdate; // Value injected by FXMLLoader

	@FXML // fx:id="diceDelete"
	private Button diceDelete; // Value injected by FXMLLoader
	
	@FXML // fx:id="handicap"
	private CheckBox handicap; // Value injected by FXMLLoader

    @FXML // fx:id="diceRollCount"
    private TextField diceRollCount; // Value injected by FXMLLoader

    /**
     * Dice Table Start
     */
	@FXML // fx:id="diceTable"
	private TableView<Dice> diceTable =  new TableView<Dice>(data);
	
    @FXML // fx:id="tableDisable"
    private TableColumn<Dice, Boolean> tableDisable; // Value injected by FXMLLoader

    @FXML // fx:id="tableName"
    private TableColumn<Dice, String> tableName; // Value injected by FXMLLoader

    @FXML // fx:id="tableType"
    private TableColumn<Dice, ?> tableType; // Value injected by FXMLLoader
    
    @FXML // fx:id="tableMax"
    private TableColumn<Dice, ?> tableMax; // Value injected by FXMLLoader

    @FXML // fx:id="tableMod"
    private TableColumn<Dice, ?> tableMod; // Value injected by FXMLLoader

    @FXML // fx:id="tableRoll"
    private TableColumn<Dice, ?> tableRoll; // Value injected by FXMLLoader
    /**
     * Dice Table End
     */
	


    @FXML // fx:id="rollButton"
	private Button rollButton; // Value injected by FXMLLoader

	@FXML // fx:id="diceId"
	private TextField diceId; // Value injected by FXMLLoader

	@FXML // fx:id="historyTable"
	private TableView<Roll> historyTable; // Value injected by FXMLLoader
	
    @FXML // fx:id="totalBox"
    private TextField totalBox; // Value injected by FXMLLoader

    @FXML // fx:id="averageBox"
    private TextField averageBox; // Value injected by FXMLLoader

    @FXML // fx:id="minBox"
    private TextField minBox; // Value injected by FXMLLoader

    @FXML // fx:id="maxBox"
    private TextField maxBox; // Value injected by FXMLLoader
    
    private URL fxmlFileLocation;
	private ResourceBundle resBundle;
	
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		try {
			this.lock = new BackgroundImage(new Image(getClass().getResource("lock.png").openStream(),20,20,false,true),
			        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
			          BackgroundSize.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(fxmlFileLocation == null)
		{
			fxmlFileLocation = this.fxmlFileLocation;
			resources = this.resBundle;
		}
		this.fxmlFileLocation = fxmlFileLocation;
		this.resBundle = resources;
		assert handicap != null : "fx:id=\"handicap\" was not injected: check your FXML file 'D&DHelper.fxml'.";
		assert rollButton != null : "fx:id=\"rollButton\" was not injected: check your FXML file 'D&DHelper.fxml'.";

		
		if(Main.getCampaign().isHandicap())
		{
			handicap.fire();
		}
		
		//Setup Top Sections
		handicap.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) { Main.getCampaign().flipHandicap(); }}
		);

		
		rollButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {	
				calc = Main.roll();
				if(calc.size() > 0)
				{
					totalBox.setText(Integer.toString(calc.get(TOTALCOLUMN)));
					averageBox.setText(Integer.toString(calc.get(AVGCOLUMN)));
					minBox.setText(Integer.toString(calc.get(MINCOLUM)));
					maxBox.setText(Integer.toString(calc.get(MAXCOLUM)));
				}
				reloadTables(false);
			}
		});

		setupDiceInput();

		setupHistory();
		
		setupDiceTable();
	}
	
	private void setupDiceTable() {
		assert diceTable != null : "fx:id=\"diceTable\" was not injected: check your FXML file 'D&DHelper.fxml'.";
        assert tableDisable != null : "fx:id=\"tableDisable\" was not injected: check your FXML file 'DiceTab.fxml'.";
        assert tableName != null : "fx:id=\"tableName\" was not injected: check your FXML file 'DiceTab.fxml'.";
        assert tableType != null : "fx:id=\"tableType\" was not injected: check your FXML file 'DiceTab.fxml'.";
        assert tableMax != null : "fx:id=\"tableMax\" was not injected: check your FXML file 'DiceTab.fxml'.";
        assert tableMod != null : "fx:id=\"tableMod\" was not injected: check your FXML file 'DiceTab.fxml'.";
        assert tableRoll != null : "fx:id=\"tableRoll\" was not injected: check your FXML file 'DiceTab.fxml'.";
        assert totalBox != null : "fx:id=\"totalBox\" was not injected: check your FXML file 'DiceTab.fxml'.";
        assert averageBox != null : "fx:id=\"averageBox\" was not injected: check your FXML file 'DiceTab.fxml'.";
        assert minBox != null : "fx:id=\"minBox\" was not injected: check your FXML file 'DiceTab.fxml'.";
        assert maxBox != null : "fx:id=\"maxBox\" was not injected: check your FXML file 'DiceTab.fxml'.";
        
		//Setup Columns
		tableDisable.setCellValueFactory(cellData -> cellData.getValue().getLockedProperty());
		

		tableDisable.setCellFactory(column -> {
			return new TableCell<Dice, Boolean>() {
				@Override
				protected void updateItem(Boolean item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						setText(null);
						setStyle("");
						this.setBackground(null);
					} else {
						if(item)
						{
							this.setBackground(new Background(lock));
						} else
						{
							this.setBackground(null);
							setText("");
						}
					}
				}
			};
		});
		tableName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		tableType.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
		
		tableMax.setCellValueFactory(cellData -> cellData.getValue().getMaxProperty());
		tableMax.setCellFactory(column -> {
			return new TableCell<Dice, Integer>() {
				@Override
				protected void updateItem(Integer item, boolean empty) {
					super.updateItem(item, empty);

					if (item == null || empty) {
						setText(null);
					} else {
						Dice d = (Dice) this.getTableRow().getItem();
						if(d != null)
						{
							setText(d.getRollCount() + "d" + Integer.toString(item));
							tableMax.setVisible(false);tableMax.setVisible(true);
						}
					}
				}
			};
		});

		
		tableMod.setCellValueFactory(cellData -> cellData.getValue().getModifierProperty());
		tableRoll.setCellValueFactory(cellData -> cellData.getValue().getLastTotalProperty());

		tableRoll.setCellFactory(column -> {
			return new TableCell<Dice, Integer>() {
				@Override
				protected void updateItem(Integer item, boolean empty) {
					super.updateItem(item, empty);

					if (item == null || empty) {
						setText(null);
						setStyle("");
					} else {
						Dice d = (Dice) this.getTableRow().getItem();
						if(d != null)
						{
							setText(Integer.toString(item));
							setTextFill(Color.BLACK);
							setStyle("-fx-font-weight: normal;");
							if(item == (d.getMax() + d.getModifier()))
							{
								setTextFill(Color.BLUE);
								setStyle("-fx-font-weight: bold;");
							}
							if(item - d.getModifier() == 1)
							{
								setTextFill(Color.RED);
								setStyle("-fx-font-weight: bold;");
							}
						}
					}
				}
			};
		});
		
		//Load Dice List
		diceTable.setItems(Main.getCampaign().getDiceList());
		
		/*
		 * Add Row Factory.
		 * When a user clicks on a row, we transfer data for them to update/remove.
		 */
		diceTable.setRowFactory( tv -> {
			TableRow<Dice> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
					Dice rowData = row.getItem();
					if (diceTable.getSelectionModel().getSelectedCells().size() == 1 && diceTable.getSelectionModel().getSelectedCells().get(0).getTableColumn().equals(tableDisable))
					{
						rowData.flipLocked();
					}
					loadDice(rowData);
				}
			});
			return row ;
		});
		
		/*
		 * Add Row Factory.
		 * When a user clicks on a row, we transfer data for them to update/remove.
		 */
		diceTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Dice>() {
			@Override
			public void changed(ObservableValue<? extends Dice> observable, Dice oldValue, Dice newValue) {
				if(newValue != null) {	loadDice(newValue);	}
			}
			});
	}


	private void loadDice(Dice dice) {
		if(dice != null)
		{
			diceId.setText(Long.toString(dice.getId()));
			diceDisable.selectedProperty().set(dice.getLocked());
			diceName.setText(dice.getName());
			diceType.setText(dice.getType());
			diceMax.setText(Integer.toString(dice.getMax()));
			diceMod.setText(Integer.toString(dice.getModifier()));
			diceRollCount.setText(Integer.toString(dice.getRollCount()));
			selInst = dice;
			historyTable.setItems(FXCollections.observableArrayList(dice.getHistory()));
			reloadTables(false);
		}
	}


	private void setupDiceInput() {
        assert noReset != null : "fx:id=\"noReset\" was not injected: check your FXML file 'DiceTab.fxml'.";
        assert diceId != null : "fx:id=\"diceId\" was not injected: check your FXML file 'D&DHelper.fxml'.";
        assert diceDisable != null : "fx:id=\"diceDisable\" was not injected: check your FXML file 'D&DHelper.fxml'.";
		assert diceName != null : "fx:id=\"diceName\" was not injected: check your FXML file 'D&DHelper.fxml'.";
		assert diceMax != null : "fx:id=\"diceMax\" was not injected: check your FXML file 'D&DHelper.fxml'.";
		assert diceMod != null : "fx:id=\"diceMod\" was not injected: check your FXML file 'D&DHelper.fxml'.";
        assert diceRollCount != null : "fx:id=\"diceRollCount\" was not injected: check your FXML file 'DiceTab.fxml'.";
		assert diceAdd != null : "fx:id=\"diceAdd\" was not injected: check your FXML file 'D&DHelper.fxml'.";
		assert diceUpdate != null : "fx:id=\"diceUpdate\" was not injected: check your FXML file 'D&DHelper.fxml'.";
		assert diceDelete != null : "fx:id=\"diceDelete\" was not injected: check your FXML file 'D&DHelper.fxml'.";
	
		noReset.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {	System.out.println(noReset.selectedProperty().getValue());keepValues = noReset.selectedProperty().getValue();}
		});
		
		//Setup Dice Modification sections
		diceAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {	addDice();}
		});

		diceUpdate.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {	updateDice();}
		});
		
		diceDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {	deleteDice();}
		});

	}


	private void setupHistory() {
		assert historyTable != null : "fx:id=\"historyTable\" was not injected: check your FXML file 'D&DHelper.fxml'.";
		//Setup Date Column
		TableColumn<Roll, ?> dateColumn = historyTable.getColumns().get(0); 
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		SimpleDateFormat past = new SimpleDateFormat("E MM/d k:mm a");
		SimpleDateFormat now = new SimpleDateFormat("h:mm.s a");
		dateColumn.setCellFactory(column -> {
			return new TableCell<Roll, Calendar>() {
				@Override
				protected void updateItem(Calendar item, boolean empty) {
					super.updateItem(item, empty);

					if (item == null || empty) {
						setText(null);
						setStyle("");
					} else {
						// Format date.
						if(today.get(Calendar.MONTH) == item.get(Calendar.MONTH) && today.get(Calendar.DATE) == item.get(Calendar.DATE))
						{
							setText(now.format(item.getTime()));
						} else
						{
							setText(past.format(item.getTime()));
							
						}
					}
				}
			};
		});
		
		//Setup Modifier Column
		historyTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("rollValue"));
		
		TableColumn<Roll, ?> totalColumn = historyTable.getColumns().get(1);
		totalColumn.setCellFactory(column -> {
			return new TableCell<Roll, Integer>() {
				@Override
				protected void updateItem(Integer item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						setText(null);
						setStyle("");
					} else {
						Roll d = (Roll) this.getTableRow().getItem();
						if(d != null)
						{
							setText(Integer.toString(item));
							setTextFill(Color.BLACK);
							setStyle("-fx-font-weight: normal;");
							if(item == d.getMax())
							{
								setTextFill(Color.BLUE);
								setStyle("-fx-font-weight: bold;");
							}
							if(item == 1)
							{
								setTextFill(Color.RED);
								setStyle("-fx-font-weight: bold;");
							}
						}
					}
				}
			};
		});

		
		//Setup Modifier Column
		historyTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("modifier"));
	}


	protected void deleteDice() {
		Dice d;
		if(diceId.getText() != null && !diceId.getText().isEmpty())
		{
			Iterator<Dice> dit = Main.getCampaign().getDiceList().iterator();
			for(int i = 0; dit.hasNext(); i++)
			{
				d = dit.next();
				if(d.getId() == Long.parseLong(diceId.getText()))
				{
					Main.getCampaign().getDiceList().remove(i);
					System.out.println("Deleted " + d.getId() + " of index " + i);
					if(i == Main.getCampaign().getDiceList().size() && Main.getCampaign().getDiceList().size() > 1)
					{
						loadDice(Main.getCampaign().getDiceList().get(i-1));
						
					} else if(Main.getCampaign().getDiceList().size() > 0)
					{
						loadDice(Main.getCampaign().getDiceList().get(i));
						reloadTables(false);
					} else
					{
						reloadTables(true);
					}
					break;
				}
			}
		}
	}


	protected void addDice() {
		Dice d = new Dice(diceName.getText(), Integer.parseInt(diceMax.getText()), Integer.parseInt(diceMod.getText()), handicap.selectedProperty().getValue(), diceDisable.selectedProperty().getValue());
		if(Integer.parseInt(diceRollCount.getText()) > 1)
		{
			d.setRollCount(Integer.parseInt(diceRollCount.getText()));
		}
		d.setType(diceType.getText());
		
		Main.getCampaign().getDiceList().add(d);
		System.out.println("Added " + d.getId() + " of index " + Main.getCampaign().getDiceList().size());
		reloadTables(true);
	}


	protected void updateDice() {
		Dice d;
		if(diceId.getText() != null && !diceId.getText().isEmpty())
		{
			Iterator<Dice> dit = Main.getCampaign().getDiceList().iterator();
			for(int i = 0; dit.hasNext(); i++)
			{
				d = dit.next();
				if(d.getId() == Long.parseLong(diceId.getText()))
				{
					d.setName(diceName.getText());
					d.setType(diceType.getText());
					d.setLocked(diceDisable.selectedProperty().getValue());
					d.setMax(Integer.parseInt(diceMax.getText()));
					d.setModifier(Integer.parseInt(diceMod.getText()));
					d.setHandicap(handicap.selectedProperty().getValue());
					if(Integer.parseInt(diceRollCount.getText()) > 1)
					{
						d.setRollCount(Integer.parseInt(diceRollCount.getText()));
					}
					System.out.println("Updated " + d.getId() + " of index " + i);
					break;
				}
			}
			reloadTables(true);
		}
	}
	
	private void reloadTables(Boolean resetInputs)
	{
//		Iterator<TableColumn<Dice, ?>> dit = diceTable.getColumns().iterator();
//		while(dit.hasNext())
//		{
//			TableColumn<Dice,?> temp = dit.next();
//			temp.setVisible(false);
//			temp.setVisible(true);
//		}
		
		if(selInst != null)
		{
			historyTable.setItems(FXCollections.observableArrayList(selInst.getHistory()));
		}
		
		Iterator<TableColumn<Roll, ?>> hit = historyTable.getColumns().iterator();
		while(hit.hasNext())
		{
			TableColumn<Roll,?> temp = hit.next();
			temp.setVisible(false);
			temp.setVisible(true);
		}

		if(resetInputs && !keepValues)
		{
			diceId.setText(null);
			diceName.setText("Dice");
			diceMax.setText("20");
			diceMod.setText("0");
		}
	}
}