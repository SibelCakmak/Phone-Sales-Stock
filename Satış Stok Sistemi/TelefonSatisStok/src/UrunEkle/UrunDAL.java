package UrunEkle;

import java.util.ArrayList;

import InterfaceKisimlari.IDAL;
import InterfaceKisimlari.IDAL1;
import VeriTabaniBaglanti.MyDataBaseLayer;


public class UrunDAL extends MyDataBaseLayer implements IDAL<UrunBilgileri>{
	private String urunEklee;
	private String sortToggle="desc";
	
	public UrunDAL(String path) {
		super(path);
		
		urunEklee = "urunEklee";
		createTable(urunEklee);
		addColumn("UrunAdi", urunEklee , type.VARCHAR);
		addColumn("Kategorisi", urunEklee , type.VARCHAR);
		addColumn("Adedi", urunEklee , type.INTEGER);
		addColumn("Hafiza", urunEklee, type.VARCHAR);
		addColumn("AlisFiyati", urunEklee , type.INTEGER);
		addColumn("SatisFiyati", urunEklee , type.INTEGER);
		addColumn("Tarih", urunEklee , type.VARCHAR);

	}
	
	public String getUrunEklee() {
		return urunEklee;
	}
	
	public final ArrayList<ArrayList<String>> getDefaultTable(){
		String sql="Select * FROM "+urunEklee+" ;";
		String message="Sorgu yapýlmadý";
		ArrayList<ArrayList<String>> queryList= executeQuerySQL(sql, message, null);
		return queryList;
	}
	
	public final ArrayList<ArrayList<String>> getSortedTable(String columnName){
		sortToggle= sortToggle.equals("asc")?"desc":"asc";
		String sql="Select * FROM "+urunEklee+" order by "+columnName+" "+sortToggle+" ;";
		String message="Sorgu yapýlmadý";
		ArrayList<ArrayList<String>> queryList= executeQuerySQL(sql, message, null);
		return queryList;
	}
	
	public final ArrayList<ArrayList<String>> getSearchedTable(String search){	
		String delimiter=" like '%"+search+"%' or ";
		String subSql = String.join(delimiter, getColumnList(urunEklee));
		String sql="Select * FROM "+urunEklee+" where "+subSql+" like '%"+search+"%';";
		String message="Sorgu yapýlmadý";
		ArrayList<ArrayList<String>> queryList= executeQuerySQL(sql, message, null);
		return queryList;
	}
	
	
// Bu kýsýmlarýn(getCell...) hepsi ürünle ilgili sýrasýyla 
//	-Adedi
//	- UrunAdi
//	-Kategorisi
//	-Hafiza
//	-SatisFiyati 
//	Bilgilerni çekiyor. Bunlarý tablodaki var olan iþlemlerde deiþiklik yada bir iþlem yapacaðýmýzda kullanýlýr.
	public int getCell(UrunBilgileri object,String columnName){
		String sql = "Select * FROM "+urunEklee+" where id="+object.getId()+";";
		ArrayList<ArrayList<String>> queryList=executeQuerySQL(sql, "message","Adedi");
		return Integer.valueOf(queryList.get(0).get(0));
	}
	
	public String getCell1(UrunBilgileri object,String columnName){
		String sql = "Select * FROM "+urunEklee+" where id="+object.getId()+";";
		ArrayList<ArrayList<String>> queryList=executeQuerySQL(sql, "message","UrunAdi");
		String  b = queryList.get(0).get(0);
		return b;
	}
	
	public String getCellKategori(UrunBilgileri object,String columnName){
		String sql = "Select * FROM "+urunEklee+" where id="+object.getId()+";";
		ArrayList<ArrayList<String>> queryList=executeQuerySQL(sql, "message","Kategorisi");
		return queryList.get(0).get(0);
	}
	
	public String getCellHafiza(UrunBilgileri object,String columnName){
		String sql = "Select * FROM "+urunEklee+" where id="+object.getId()+";";
		ArrayList<ArrayList<String>> queryList=executeQuerySQL(sql, "message","Hafiza");
		return queryList.get(0).get(0);
	}
	public String getCellSatisFiayti(UrunBilgileri object,String columnName){
		String sql = "Select * FROM "+urunEklee+" where id="+object.getId()+";";
		ArrayList<ArrayList<String>> queryList=executeQuerySQL(sql, "message","SatisFiyati");
		return queryList.get(0).get(0);
	}
	



	@Override
	public void add(UrunBilgileri object) {
		
		
		insertDefaultRow(urunEklee);
		int id = getLastId(urunEklee);
		object.setId(id);
		updateCell(urunEklee, object.getId(),"UrunAdi", object.getUrunAdi());
        updateCell(urunEklee, object.getId(),"Kategorisi",object.getKategori());
		updateCell(urunEklee, object.getId(), "Adedi","" + object.getAdedi());
		updateCell(urunEklee, object.getId(),"Hafiza",object.getHafiza());
		updateCell(urunEklee, object.getId(), "AlisFiyati", ""+object.getAlisFiyati());
		updateCell(urunEklee, object.getId(), "SatisFiyati", ""+object.getSatisFiyati());
		updateCell(urunEklee, object.getId(), "Tarih", ""+object.getTarih());

		

	}

	@Override
	public void delete(UrunBilgileri object) {
		deleteRow(urunEklee, object.getId());
		
	}
	

	@Override
	public void update(UrunBilgileri k) {
		
		
		 
		updateCell(urunEklee, k.getId(),"UrunAdi", k.getUrunAdi());
        updateCell(urunEklee, k.getId(),"Kategorisi", k.getKategori());
		updateCell(urunEklee, k.getId(), "Adedi","" + k.getAdedi());
		updateCell(urunEklee, k.getId(),"Hafiza", ""+k.getHafiza());
		updateCell(urunEklee, k.getId(), "AlisFiyati", ""+k.getAlisFiyati());
		updateCell(urunEklee, k.getId(), "SatisFiyati", ""+k.getSatisFiyati());
		updateCell(urunEklee, k.getId(), "Tarih", ""+k.getTarih());
	
		
		
	}
	
	@Override
	public void update1(UrunBilgileri i) {
		
		
		 

		updateCell(urunEklee, i.getId(), "Adedi","" + i.getAdedi());
		
		
	}
	public int sayi() {
		
		return getLastId(urunEklee);
	}
	
	
	
	

}
