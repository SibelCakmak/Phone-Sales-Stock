package IadeArsivi;


public class IadeEdilenUrunlerGetSet {
	
	String urunAdi;
	String Kategorisi;
	String Hafiza;

	String iadeSebebi;
	String Tarih;
	int id;
	
	

	public IadeEdilenUrunlerGetSet() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrunAdi() {
		return urunAdi;
	}

	public void setUrunAdi(String urunAdi) {
		this.urunAdi = urunAdi;
	}

	public String getKategorisi() {
		return Kategorisi;
	}

	public void setKategorisi(String kategorisi) {
		Kategorisi = kategorisi;
	}

	public String getHafiza() {
		return Hafiza;
	}

	public void setHafiza(String hafiza) {
		Hafiza = hafiza;
	}


	public String getIadeSebebi() {
		return iadeSebebi;
	}

	public String getTarih() {
		return Tarih;
	}

	public void setTarih(String tarih) {
		Tarih = tarih;
	}
	public void setIadeSebebi(String iadeSebebi) {
		this.iadeSebebi = iadeSebebi;
	}
	
	

}
