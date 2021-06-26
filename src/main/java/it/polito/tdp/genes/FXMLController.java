/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.genes;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.genes.model.Adiacenza;
import it.polito.tdp.genes.model.Genes;
import it.polito.tdp.genes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="cmbGeni"
    private ComboBox<Genes> cmbGeni; // Value injected by FXMLLoader

    @FXML // fx:id="btnGeniAdiacenti"
    private Button btnGeniAdiacenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtIng"
    private TextField txtIng; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	this.model.creaGrafo();
    	txtResult.appendText("GRAFO CREATO con "+this.model.nVertici()+" vertici e "+this.model.nArchi()+" archi!"+"\n");

    	this.cmbGeni.getItems().addAll(this.model.getVertici());
    }

    @FXML
    void doGeniAdiacenti(ActionEvent event) {

    	txtResult.clear();
    	List <Genes> adiacenti = new ArrayList<>();
    	Genes gene = this.cmbGeni.getValue();
    	if(gene==null ) {
    		txtResult.appendText("Selezionare un gene!!!");
    		return;
    	}
    	for(Adiacenza a : this.model.getAdiacenze()) {
    		if(a.getGene1().equals(gene) ) { //vuol dire che gene2 è adiacente
    			adiacenti.add(a.getGene2());
    			a.getGene2().setPeso(a.getPeso());
    			
    		}
    		if(a.getGene2().equals(gene)) { //vuol dire che gene1 è adiacente
    			adiacenti.add(a.getGene1());
    			a.getGene1().setPeso(a.getPeso());
    		}
    	}
    	
    	Collections.sort(adiacenti, new Comparator <Genes>() {

			@Override
			public int compare(Genes o1, Genes o2) {
				// TODO Auto-generated method stub
				return  -Double.compare(o1.getPeso(), o2.getPeso());
			}
    		
    	});
    	
    	for (Genes gg : adiacenti) {
    		txtResult.appendText(gg+" --> "+ gg.getPeso()+"\n");
    	}
    	
    	/*
    	for (Genes g : this.model.getAdiacenti(gene)) {
    		
    		adiacenti.add(g);
    		
    	}
    	
    
    	    	
    	for (Genes gg : adiacenti) {
    		txtResult.appendText(gg+" --> "+ gg.getPeso()+"\n");
    	}
    	*/
    }

    @FXML
    void doSimula(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbGeni != null : "fx:id=\"cmbGeni\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnGeniAdiacenti != null : "fx:id=\"btnGeniAdiacenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtIng != null : "fx:id=\"txtIng\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
    
}
