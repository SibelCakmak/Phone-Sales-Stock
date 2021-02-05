package OturumBilgileri;

import java.util.ArrayList;

import InterfaceKisimlari.IDAL1;
import VeriTabaniBaglanti.MyDataBaseLayer;

public class OturumBilgiDAL extends MyDataBaseLayer implements IDAL1<OturumBilgileriGetSet>  {
	private String sortToggle="desc";
	String tableSifre;
	public OturumBilgiDAL(String path) {
		super(path);
		
		tableSifre = "oturumBilgileri";
		
		createTable(tableSifre);
		addColumn("kullaniciAdi", tableSifre, type.VARCHAR);
        addColumn("Sifre", tableSifre, type.VARCHAR);
        addColumn("telNo", tableSifre, type.VARCHAR);
        addColumn("pozisyon", tableSifre, type.VARCHAR);
	}


	
	@Override
	public void add(OturumBilgileriGetSet object) {
		insertDefaultRow(tableSifre);
		int id = getLastId(tableSifre);
		object.setId(id);
		updateCell(tableSifre, object.getId(), "kullaniciAdi", object.getUsername());
        updateCell(tableSifre, object.getId(), "Sifre", object.getSifre());
        updateCell(tableSifre, object.getId(), "telNo", object.getTelNo());
        updateCell(tableSifre, object.getId(), "pozisyon", object.getPozisyon());
		
	}

	@Override
	public void delete(OturumBilgileriGetSet object) {
		
		deleteRow(tableSifre, object.getId());
	}

	@Override
	public void update(OturumBilgileriGetSet k) {
		updateCell(tableSifre, k.getId(), "Sifre", k.getSifre());
		
	}
	
	public String getTableSifre() {
		return tableSifre;
	}
	
	public final ArrayList<ArrayList<String>> getDefaultTable(){
		String sql="Select * FROM "+tableSifre+" ;";
		String message="Sorgu yapýlmadý";
		ArrayList<ArrayList<String>> queryList= executeQuerySQL(sql, message, null);
		return queryList;
	}
	
	public final ArrayList<ArrayList<String>> getSortedTable(String columnName){
		tableSifre= tableSifre.equals("asc")?"desc":"asc";
		String sql="Select * FROM "+tableSifre+" order by "+columnName+" "+tableSifre+" ;";
		String message="Sorgu yapýlmadý";
		ArrayList<ArrayList<String>> queryList= executeQuerySQL(sql, message, null);
		return queryList;
	}
	
	public final ArrayList<ArrayList<String>> getSearchedTable(String search){	
		String delimiter=" like '%"+search+"%' or ";
		String subSql = String.join(delimiter, getColumnList(tableSifre));
		String sql="Select * FROM "+tableSifre+" where "+subSql+" like '%"+search+"%';";
		String message="Sorgu yapýlmadý";
		ArrayList<ArrayList<String>> queryList= executeQuerySQL(sql, message, null);
		return queryList;
	}
	
	
	// oturumBilgileri adlý tablodaki getCell.. fonksiyonlarý sayesinde 
//	-kullaniciAdi
//	-Sifre
//	-id
//	bilgilerini çekip baþka sýnýfta bu bilgiler üzerinde iþlem yapabilecem
	public String getCellKullanýcýAdi(OturumBilgileriGetSet object,String columnName){
		String sql = "Select * FROM "+tableSifre+" where id="+object.getId()+";";
		ArrayList<ArrayList<String>> queryList=executeQuerySQL(sql, "message","kullaniciAdi");
		return queryList.get(0).get(0);
	}
	
	public String getCellSifre(OturumBilgileriGetSet object,String columnName){
		String sql = "Select * FROM "+tableSifre+" where id="+object.getId()+";";
		ArrayList<ArrayList<String>> queryList=executeQuerySQL(sql, "message","Sifre");
		return queryList.get(0).get(0);
	}
	
	public String getCellPozisyon(OturumBilgileriGetSet object,String columnName){
		String sql = "Select * FROM "+tableSifre+" where id="+object.getId()+";";
		ArrayList<ArrayList<String>> queryList=executeQuerySQL(sql, "message","pozisyon");
		return queryList.get(0).get(0);
	}
	
	public int getCellId(OturumBilgileriGetSet object,String columnName){
		String sql = "Select * FROM "+tableSifre+" where id="+object.getId()+";";
		ArrayList<ArrayList<String>> queryList=executeQuerySQL(sql, "message","id");
		return Integer.valueOf(queryList.get(0).get(0));
	}
	
	// mevcut kayit sayýsýný çekmek için sayi adýnda bir fonksiyon oluþturdum.
	// getLastId yi MyDataBaseLayer sýnýfýndan çektim. 
	public int sayi() {
		return getLastId(tableSifre);
	}
	
	
	
	
}