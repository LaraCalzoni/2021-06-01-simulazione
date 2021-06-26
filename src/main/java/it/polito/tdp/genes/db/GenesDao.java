package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Adiacenza;
import it.polito.tdp.genes.model.Genes;


public class GenesDao {
	
	public void getAllGenes(Map <String, Genes> idMap){
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome FROM Genes";
		//List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				if(!idMap.containsKey(res.getString("GeneID"))) {
				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				//result.add(genes);
				idMap.put(genes.getGeneId(), genes);
			}
			}
			res.close();
			st.close();
			conn.close();
		//	return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			//return null;
		}
	}
	


	public List <Genes> getVertici(Map <String, Genes> idMap){
		String sql="SELECT DISTINCT g.GENEID "
				+ "FROM genes g "
				+ "WHERE g.Essential= \"Essential\"";
		Connection conn = DBConnect.getConnection();
		List <Genes> result = new ArrayList<>();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				result.add(idMap.get(res.getString("GeneID")));
				
			}
			
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List <Adiacenza> getAdiacenze(Map <String, Genes> idMap){
		String sql="SELECT DISTINCT i.*, i.Expression_Corr AS corr "
				+ "FROM genes g1, genes g2, interactions i "
				+ "WHERE g1.Essential= \"Essential\" AND g2.Essential= \"Essential\" AND "
				+ "i.GeneID1= g1.GeneID AND i.GeneID2= g2.GeneID AND g1.GeneID <> g2.GeneID";
	
		Connection conn = DBConnect.getConnection();
		List <Adiacenza> result = new ArrayList<>();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes g1 = idMap.get(res.getString("i.GeneID1"));
				Genes g2 = idMap.get(res.getString("i.GeneID2"));
				Adiacenza a = new Adiacenza(g1, g2,res.getDouble("i.Expression_Corr"));
	
				if(g1.getChromosome()== g2.getChromosome()) {
				//doppio del valore assoluto di corr se i due geni stesso cromosoma.
					a.setPeso(2*Math.abs(res.getDouble("corr")));
					//g1.setPeso(a.getPeso());
					//g2.setPeso(a.getPeso());
				}
				
				else {			
				//valore assoluto di tale corr se i due geni non appartengono a stesso cromosoma
					a.setPeso(Math.abs(res.getDouble("corr")));
					//g1.setPeso(a.getPeso());
					//g2.setPeso(a.getPeso());
				}
				
				result.add(a);
			}
			
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	
}
