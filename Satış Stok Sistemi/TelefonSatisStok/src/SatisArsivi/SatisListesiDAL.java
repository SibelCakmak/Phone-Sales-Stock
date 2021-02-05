package SatisArsivi;

import java.util.ArrayList;

import InterfaceKisimlari.IDALSATIS;

import VeriTabaniBaglanti.MyDataBaseLayer;

public class SatisListesiDAL extends MyDataBaseLayer implements IDALSATIS<SatisListesiGetSet>{
	private String satisEkle;
	private String sortToggle="desc";
	
	public SatisListesiDAL(String path) {
		super(path);
		
		satisEkle = "satisListesi";
		createTable(satisEkle);
		addColumn("UrunAdi",satisEkle, type.VARCHAR);
		addColumn("Kategorisi",satisEkle, type.VARCHAR);
		addColumn("Hafiza",satisEkle, type.VARCHAR);
		addColumn("KacAdetSatildi",satisEkle, type.INTEGER);
		addColumn("SatisFiyati",satisEkle, type.VARCHAR);
		addColumn("Tarih",satisEkle, type.VARCHAR);
		
	}
	
	public String getSatiEkle() {
		return satisEkle;
	}
	
	public final ArrayList<ArrayList<String>> getDefaultTable(){
		String sql="Select * FROM "+satisEkle+" ;";
		String message="Sorgu yapýlmadý";
		ArrayList<ArrayList<String>> queryList= executeQuerySQL(sql, message, null);
		return queryList;
	}
	
	public final ArrayList<ArrayList<String>> getSortedTable(String columnName){
		sortToggle= sortToggle.equals("asc")?"desc":"asc";
		String sql="Select * FROM "+satisEkle+" order by "+columnName+" "+sortToggle+" ;";
		String message="Sorgu yapýlmadý";
		ArrayList<ArrayList<String>> queryList= executeQuerySQL(sql, message, null);
		return queryList;
	}
	
	public final ArrayList<ArrayList<String>> getSearchedTable(String search){	
		String delimiter=" like '%"+search+"%' or ";
		String subSql = String.join(delimiter, getColumnList(satisEkle));
		String sql="Select * FROM "+satisEkle+" where "+subSql+" like '%"+search+"%';";
		String message="Sorgu yapýlmadý";
		ArrayList<ArrayList<String>> queryList= executeQuerySQL(sql, message, null);
		return queryList;
	}
	
	
	public String getCellAdi(SatisListesiGetSet k,String columnName){
		String sql = "Select * FROM "+satisEkle+" where id="+k.getId()+";";
		ArrayList<ArrayList<String>> queryList=executeQuerySQL(sql, "message","UrunAdi");
		String  b = queryList.get(0).get(0);
		return b;
	}
	public String getCellKategori(SatisListesiGetSet object,String columnName){
		String sql = "Select * FROM "+satisEkle+" where id="+object.getId()+";";
		ArrayList<ArrayList<String>> queryList=executeQuerySQL(sql, "message","Kategorisi");
		return queryList.get(0).get(0);
	}
	
	public String getCellHafiza(SatisListesiGetSet object,String columnName){
		String sql = "Select * FROM "+satisEkle+" where id="+object.getId()+";";
		ArrayList<ArrayList<String>> queryList=executeQuerySQL(sql, "message","Hafiza");
		return queryList.get(0).get(0);
	}
	


	@Override
	public void add(SatisListesiGetSet object) {
		
		
		insertDefaultRow(satisEkle);
		int id = getLastId(satisEkle);
		object.setId(id);
		updateCell(satisEkle, object.getId(),"UrunAdi", object.getUrunAdi());
        updateCell(satisEkle, object.getId(),"Kategorisi",object.getKategorisi());
		updateCell(satisEkle, object.getId(), "Hafiza", object.getHafiza());
		updateCell(satisEkle, object.getId(),"KacAdetSatildi", "" + object.getAdedi());
		updateCell(satisEkle, object.getId(), "SatisFiyati",object.getSatisFiyati());
		updateCell(satisEkle, object.getId(), "Tarih", ""+object.getTarih());
		
		

	}

	@Override
	public void delete(SatisListesiGetSet object) {
		deleteRow(satisEkle, object.getId());
		
	}
	

	@Override
	public void update(SatisListesiGetSet k) {

		updateCell(satisEkle, k.getId(),"UrunAdi", k.getUrunAdi());
        updateCell(satisEkle, k.getId(),"Kategorisi", k.getKategorisi());
		updateCell(satisEkle, k.getId(),"Hafiza",k.getHafiza());
		updateCell(satisEkle, k.getId(), "KacAdetSatildi","" + k.getAdedi());
		updateCell(satisEkle, k.getId(), "SatisFiyati", ""+k.getSatisFiyati());
		updateCell(satisEkle, k.getId(), "Tarih", ""+k.getTarih());

	}
	
	
}
