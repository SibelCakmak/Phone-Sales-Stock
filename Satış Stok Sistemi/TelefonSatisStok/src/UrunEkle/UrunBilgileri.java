package UrunEkle;


public class UrunBilgileri {
	
	String urunAdi;
	String kategori;
	int adedi;
	String hafiza;
	int satisFiyati;
	int alisFiyati;
	int id;
	int satildi;
	
	


	String tarih;
	
	
	
   public UrunBilgileri() {

	
   }
	
	public String getUrunAdi() {
		return urunAdi;
	}


	public void setUrunAdi(String urunAdi) {
		this.urunAdi = urunAdi;
		
	}


	public String getKategori() {
		return kategori;
	}


	public void setKategori(String kategori) {
		this.kategori = kategori;
	}
	
	
	
	public int getAdedi() {
		return adedi;
	}
   

	public void setAdedi(int adedi) {
		this.adedi = adedi;
		
		
	}
	
	

	public String getHafiza() {
		return hafiza;
	}


	public void setHafiza(String hafiza) {
		this.hafiza = hafiza;
	}


	public int getSatisFiyati() {
		return satisFiyati;
	}


	public void setSatisFiyati(int satisFiyati) {
		this.satisFiyati = satisFiyati;
	}


	public int getAlisFiyati() {
		return alisFiyati;
	}


	public void setAlisFiyati(int alisFiyati) {
		this.alisFiyati = alisFiyati;
	}


	public String getTarih() {
		return tarih;
	}


	public void setTarih(String tarih) {
		this.tarih = tarih;
	}
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public int getSatildi() {
		return adedi-1;
	}

	public void setSatildi(int satildi) {
		this.satildi = adedi-1;
	}
	
	

}
