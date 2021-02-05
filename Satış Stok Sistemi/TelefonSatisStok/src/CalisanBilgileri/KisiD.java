package CalisanBilgileri;


import java.util.ArrayList;

import InterfaceKisimlari.IDAL3;
import VeriTabaniBaglanti.MyDataBaseLayer;

public class KisiD extends MyDataBaseLayer implements IDAL3<Calisan> {
	
	// Bu sayfa çalýþanlara ait biliglerin tutulduðu iþlendiði sayfa.
	// Çalýþanlarý ekleme yada bilgileri görme gibi iþlevlere sahip.
	
	private String tableName;

	private String sortToggle="desc";

	public KisiD(String path) {
		super(path);
		tableName="CalisanListesi";
		
		
		createTable(tableName);
		addColumn("Ad", tableName, type.VARCHAR);
        addColumn("Soyad", tableName, type.VARCHAR);
		addColumn("Yas", tableName, type.INTEGER);
		addColumn("Cinsiyet", tableName, type.VARCHAR);
		addColumn("Pozisyon", tableName, type.VARCHAR);
		addColumn("TelNo", tableName, type.VARCHAR);
		addColumn("Maas", tableName, type.INTEGER);
		
	}

	@Override
	public void add(Calisan object) {
		
	
		
		insertDefaultRow(tableName);
		int id = getLastId(tableName);
		object.setId(id);
		updateCell(tableName, object.getId(), "Ad", object.getAd());
        updateCell(tableName, object.getId(), "Soyad", object.getSoyad());
		updateCell(tableName, object.getId(), "Yas", ""+object.getYas());
		updateCell(tableName, object.getId(), "Cinsiyet", object.getCinsiyet());
		updateCell(tableName, object.getId(), "Pozisyon", object.getPozisyon());
		updateCell(tableName, object.getId(), "TelNo", object.getNumara());
		updateCell(tableName, object.getId(), "Maas", ""+object.getMaas());
		
		

	}

	@Override
	public void delete(Calisan object) {
		deleteRow(tableName, object.getId());
		
	}
	

	@Override
	public void update(Calisan k) {
		updateCell(tableName, k.getId(), "Ad", k.getAd());
        updateCell(tableName, k.getId(), "Soyad", k.getSoyad());
		updateCell(tableName, k.getId(), "Yas", ""+k.getYas());
		updateCell(tableName, k.getId(), "Cinsiyet", k.getCinsiyet());
		updateCell(tableName, k.getId(), "Pozisyon", k.getPozisyon());
		updateCell(tableName, k.getId(), "TelNo", k.getNumara());
		updateCell(tableName, k.getId(), "Maas", ""+ k.getMaas());
	
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public final ArrayList<ArrayList<String>> getDefaultTable(){
		String sql="Select * FROM "+tableName+" ;";
		String message="Sorgu yapýlmadý";
		ArrayList<ArrayList<String>> queryList= executeQuerySQL(sql, message, null);
		return queryList;
	}
	
	public final ArrayList<ArrayList<String>> getSortedTable(String columnName){
		sortToggle= sortToggle.equals("asc")?"desc":"asc";
		String sql="Select * FROM "+tableName+" order by "+columnName+" "+sortToggle+" ;";
		String message="Sorgu yapýlmadý";
		ArrayList<ArrayList<String>> queryList= executeQuerySQL(sql, message, null);
		return queryList;
	}
	
	public final ArrayList<ArrayList<String>> getSearchedTable(String search){	
		String delimiter=" like '%"+search+"%' or ";
		String subSql = String.join(delimiter, getColumnList(tableName));
		String sql="Select * FROM "+tableName+" where "+subSql+" like '%"+search+"%';";
		String message="Sorgu yapýlmadý";
		ArrayList<ArrayList<String>> queryList= executeQuerySQL(sql, message, null);
		return queryList;
	}
	
}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
