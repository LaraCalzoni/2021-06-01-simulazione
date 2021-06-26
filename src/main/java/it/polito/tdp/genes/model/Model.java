package it.polito.tdp.genes.model;

import java.util.HashMap;
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
		}
	//	System.out.println("GRAFO CREATO : "+this.grafo.vertexSet().size()+this.grafo.edgeSet().size());
	}
	
}
