package CalisanBilgileri;


public class Calisan {
	
	
	// �ALI�ANLARA A�T B�LG�LER� ALIP SAKLAMAK ���N GEREKL� COLONLARI BURADA TANIMLIYORUZ.
	//DAHA SONRA BUNLARI OLU�TURDU�UMUZ TABLODAK� COLONLARIN ALTLARINA DE�ER OLARAK G�R�CEZ
	private String ad;
	private String soyad;
    private String cinsiyet;
    private String pozisyon;
    private String numara;
	private int yas;
	private int id;
	private int maas;

	

	public  Calisan() {
	}

	public String getCinsiyet() {
		return cinsiyet;
	}

	public void setCinsiyet(String cinsiyet) {
		this.cinsiyet = cinsiyet;
	}

	
	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public int getYas() {
		return yas;
	}

	public void setYas(int yas) {
		this.yas = yas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public String getSoyad() {
         return soyad;
    }
        
    public void setSoyad(String soyad) {
          this.soyad = soyad;
    }
    
    public String getPozisyon() {
		return pozisyon;
	}

	public void setPozisyon(String pozisyon) {
		this.pozisyon = pozisyon;
	}

	public String getNumara() {
		return numara;
	}

	public void setNumara(String numara) {
		this.numara = numara;
	}
	
	public int getMaas() {
		return maas;
	}

	public void setMaas(int maas) {
		this.maas = maas;
	}
        
	
	
}


	

