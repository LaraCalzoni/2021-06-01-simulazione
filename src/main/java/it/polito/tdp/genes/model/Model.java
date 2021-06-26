package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	private SimpleWeightedGraph <Genes, DefaultWeightedEdge> grafo;
	private GenesDao dao;
	private Map <String, Genes> idMap;
	
	public Model() {
		dao = new GenesDao();
		idMap = new HashMap<>();
		dao.getAllGenes(idMap);
	}
	
	public void creaGrafo() {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		//aggiungo vertici
		Graphs.addAllVertices(grafo, dao.getVertici(idMap));
		
		//aggiungo archi
		for(Adiacenza a : dao.getAdiacenze(idMap)) {
			Graphs.addEdgeWithVertices(grafo,a.getGene1(), a.getGene2(), a.getPeso());
		//	a.getGene1().setPeso(a.getPeso());
		//	a.getGene2().setPeso(a.getPeso());
		}
	//	System.out.println("GRAFO CREATO : "+this.grafo.vertexSet().size()+this.grafo.edgeSet().size());
	}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List <Genes> getVertici(){
		return dao.getVertici(idMap);
	}
	
	public List <Genes> getAdiacenti ( Genes g ){
		List <Genes> result = new ArrayList<>();
		for( Genes gg : Graphs.neighborListOf(this.grafo, g)) {
			result.add(gg);
		}
		return result;
	}
	
	public List <Adiacenza> getAdiacenze(){
		return this.dao.getAdiacenze(idMap);
	}
	
}
