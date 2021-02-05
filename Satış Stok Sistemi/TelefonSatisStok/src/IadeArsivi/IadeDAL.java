package IadeArsivi;

import java.util.ArrayList;

import InterfaceKisimlari.IDAL_IADE;
import VeriTabaniBaglanti.MyDataBaseLayer;



public class IadeDAL extends MyDataBaseLayer implements IDAL_IADE<IadeEdilenUrunlerGetSet>{
	private String iade;
	private String sortToggle="desc";
	
	public IadeDAL(String path) {
		super(path);
		
		iade = "iadeEdilenUrunler";
		
		createTable(iade);
		
		addColumn("UrunAdi",iade, type.VARCHAR);
		addColumn("Kategorisi",iade, type.VARCHAR);
		addColumn("Hafiza",iade, type.VARCHAR);
		addColumn("IadeSebebi",iade, type.INTEGER);
		addColumn("Tarih",iade, type.VARCHAR);
		
	}
	public String getIade() {
		return iade;
	}
	
	public final ArrayList<ArrayList<String>> getDefaultTable(){
		String sql="Select * FROM "+iade+" ;";
		String message="Sorgu yapýlmadý";
		ArrayList<ArrayList<String>> queryList= executeQuerySQL(sql, message, null);
		return queryList;
	}
	
	public final ArrayList<ArrayList<String>> getSortedTable(String columnName){
		sortToggle= sortToggle.equals("asc")?"desc":"asc";
		String sql="Select * FROM "+iade+" order by "+columnName+" "+sortToggle+" ;";
		String message="Sorgu yapýlmadý";
		ArrayList<ArrayList<String>> queryList= executeQuerySQL(sql, message, null);
		return queryList;
	}
	
	public final ArrayList<ArrayList<String>> getSearchedTable(String search){	
		String delimiter=" like '%"+search+"%' or ";
		String subSql = String.join(delimiter, getColumnList(iade));
		String sql="Select * FROM "+iade+" where "+subSql+" like '%"+search+"%';";
		String message="Sorgu yapýlmadý";
		ArrayList<ArrayList<String>> queryList= executeQuerySQL(sql, message, null);
		return queryList;
	}
	
	

	@Override
	public void add(IadeEdilenUrunlerGetSet object) {

		insertDefaultRow(iade);
		int id = getLastId(iade);
		object.setId(id);
		updateCell(iade, object.getId(),"UrunAdi", object.getUrunAdi());
        updateCell(iade, object.getId(),"Kategorisi",object.getKategorisi());
		updateCell(iade, object.getId(), "Hafiza", object.getHafiza());
		updateCell(iade, object.getId(), "IadeSebebi",object.getIadeSebebi());
		updateCell(iade, object.getId(), "Tarih", ""+object.getTarih());
		
	}

	@Override
	public void delete(IadeEdilenUrunlerGetSet object) {
		deleteRow(iade, object.getId());
		
	}

	@Override
	public void update(IadeEdilenUrunlerGetSet k) {
		updateCell(iade, k.getId(),"UrunAdi", k.getUrunAdi());
        updateCell(iade, k.getId(),"Kategorisi",k.getKategorisi());
		updateCell(iade, k.getId(), "Hafiza", k.getHafiza());
		updateCell(iade, k.getId(), "IadeSebebi",k.getIadeSebebi());
		updateCell(iade, k.getId(), "Tarih", ""+k.getTarih());
		
		
	}

}
