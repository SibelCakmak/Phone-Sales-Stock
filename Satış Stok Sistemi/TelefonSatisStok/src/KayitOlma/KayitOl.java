package KayitOlma;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import OturumBilgileri.OturumBilgiDAL;
import OturumBilgileri.OturumBilgileriGetSet;
import YoneticiKýsmý.OturumAc;

public class KayitOl extends JFrame{
	public static int kayitSayisi=11;
	int randomSayi;
	OturumBilgiDAL ekle;
	public KayitOl() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setResizable(false);
        getContentPane().setLayout(null);
        setTitle("TELEFON SATIS-STOK TAKÝBÝ");
        ekle = new OturumBilgiDAL("OturumVeri");
        
        
        Image imgEkle = new ImageIcon(KayitOl.class.getResource("/resim7.jpg")).getImage();
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon(imgEkle));
        background.setBounds(0,0,700, 500);
     
        
        JLabel kullaniciAdi = new JLabel();
        kullaniciAdi.setText("Kullanýcý Adý :");
        kullaniciAdi.setBounds(140, 90, 150, 25);
        
        JTextField kullaniciText = new JTextField();        
        kullaniciText.setBounds(225,90,185,25);
        
        JButton olustur = new JButton("ÝLERÝ");
        olustur.setBounds(421,95,70,17);
        olustur.setBackground(Color.yellow);

        
        
        // ileri didyence bu kýsýmlar açýlacaðý için burda sadece tanýmlayýp,  týkladýksan sonra bu kýsýmlar gelsin diye 
        // addAction kýsmýnda setBounds ý  tanýmlýyoruz 
        JLabel sifre = new JLabel();
        JTextField sifreText = new JTextField();
        JLabel sifreTekrar = new JLabel();
        JTextField sifreTekrarText = new JTextField();
        JButton kayitEt = new JButton("DEVAM");
        JLabel telefonNo = new JLabel();
        JTextField telefonNoText = new JTextField();
        JButton kayitEkle = new JButton("KAYIT OL");
        JLabel tempUyari = new JLabel();
        JLabel koduGirme = new JLabel();
        JTextField koduGirmeText = new JTextField();
        JButton onayla = new JButton("ONAYLA");
        JButton anaSayfaDon = new JButton("oturum ac");
        anaSayfaDon.setBackground(Color.white);
        JLabel text = new JLabel();
        
        
        // pozisyonu yanlýþ girilmesi yanlýþ kayýta sonuç açabilir bazen. Bu yüzden pozsyonu girmesin, seçsin
        
        JLabel pozisyon = new JLabel();
        JComboBox comboBox = new JComboBox( );
        comboBox.addItem("satýs elemaný");
        comboBox.addItem("stok sorumlusu");
        comboBox.addItem("kasiyer");
        comboBox.setBackground(Color.yellow);
        
        
        
        JLabel  hata = new JLabel();
        hata.setText("girilen þifreler birbirine uymuyor");
        hata.setForeground(Color.red);
    
        
        olustur.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// EÐER KULLANICI ADI BOÞSA UYARI VERÝLECEK
				if(kullaniciText.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Kullanýcý Adýný Giriniz");
				}
				
				else {
					
					olustur.setBounds(1000,95,70,17); //buttonu ekrandan atmak için
					
					sifre.setText("Sifre :");
					sifre.setBounds(140, 140, 150, 25);
			               
					sifreText.setBounds(225,140,185,25);
			        
			       
					sifreTekrar.setText("Sifre Tekrar :");
					sifreTekrar.setBounds(140, 180, 150, 25);
			        
			                
					sifreTekrarText.setBounds(225,180,185,25);
			        
					telefonNo.setText("Tel No : ");
			        telefonNo.setBounds(140, 220, 150, 25);
			               
			        telefonNoText.setBounds(225,220,185,25);
			        
			        
			        pozisyon.setText("Pozisyon :");
					pozisyon.setBounds(140, 260, 150, 25);
			        
			        comboBox.setBounds(225,260,170,20);
			        
			     
					
					kayitEt.setBounds(260,322,90,17);
					kayitEt.setBackground(Color.yellow);
					
					kayitEt.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							
							// telefon numarasýný girmediyse hata alacak
							if(telefonNoText.getText().equals("")) {
								
								tempUyari.setText("Tel No Alaný Boþ Býrakýlamaz");
								tempUyari.setForeground(Color.RED);
								tempUyari.setBounds(225,380,180,30);
							}
					
							
							
							
							
							
							
							String sifree =sifreText.getText();
							String tekrar = sifreTekrarText.getText();

							// bu koþulda birbirlerinin içindeki deðerleri kendilerinki ile karþýlaþtýrýyorlar.
							//Ayný olursa if koþuluna giricek deðilse uyarý alýcak þifreler eþit deðil diye
							if(sifreText.getText().equals(tekrar) && sifreTekrarText.getText().equals(sifree)) {
								
								
								
								
								// YENÝ BÝR CLASS AÇMAK YERÝNE, AYNI CLASSIN ARAYÜZÜNÜ BOÞALIP ARAYÜZÜ YENÝDEN DÝZAYN ETTÝM
								sifreTekrar.setBounds(1000,0,0,0);
								sifreTekrarText.setBounds(1000,0,0,0);
								sifreText.setBounds(1000,0,0,0);
								sifre.setBounds(1000,0,0,0);
								kayitEkle.setBounds(1000,0,0,0);
								kayitEt.setBounds(1000,0,0,0);
								kullaniciAdi.setBounds(1000,0,0,0);
								kullaniciText.setBounds(1000,0,0,0);
								hata.setBounds(1000,0,0,0);
								olustur.setBounds(1000,0,0,0);
								telefonNo.setBounds(1000,0,0,0);
								telefonNoText.setBounds(1000,0,0,0);
								comboBox.setBounds(10000,0,0,0);
								pozisyon.setBounds(1000,0,0,0);
								tempUyari.setBounds(1000,0,0,0);
								
								// TELEFONUNA GELEN 6 HANELÝ SAYI ÝÇÝN RANDOM SAYÝ ÜRETTÝM (tELEFON YERÝNE PROJEDE CONSOLA YAZILACAK)
								randomSayi = (int) (Math.random() * 1000000);

								System.out.println(randomSayi);
								
								String telno = telefonNoText.getText();
								
								sifre.setText(telno + " NUMARANIZA");
								sifre.setBounds(231,120,300,25);
								koduGirme.setText(" GELEN 6 HANELÝ KODU GÝRNÝZ");
								koduGirme.setBounds(222, 150, 300, 25);

								
								koduGirmeText.setBounds(220, 190, 200, 30);

								
								onayla.setBounds(280, 235, 90, 25);
								onayla.setBackground(Color.yellow);

								onayla.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {
										// TELEFONA GELEN 6 HANELÝ RANDOM SAYÝYÝ DOÐRU GÝRERSE OTURUMUMU GERÇEKLEÞECEK VE VERÝ TABANINA GÝRÝÞ BÝLGÝLERÝ KAYDEDÝLECEK
										if (koduGirmeText.getText().equals("" + randomSayi)) {
											OturumBilgileriGetSet k = new OturumBilgileriGetSet();
											k.setUsername(kullaniciText.getText());
							                k.setSifre(sifreText.getText());
											k.setTelNo(telefonNoText.getText());
											
											// Combobox nesne olarak tanýmandýðý için onu ilk olarak stringe çevirip sonra seçilen pozisyonu veri tabanýna kaydettim.
											k.setPozisyon(String.valueOf(comboBox.getSelectedItem()));
											ekle.add(k);
											kayitSayisi = kayitSayisi + 1;
											
											JOptionPane.showMessageDialog(null, "ÜYELÝÐÝNÝZ GERÇEKLEÞMÝÞTÝR HOÞGELDÝNÝZ");
											
											
											// OTURUM AÇINCA ÝÞLERÝ BÝTTÝÐÝ ÝÇÝN DÝREK ANA SAYFAYA DÖNMESÝ ÝÇÝN 'ANA SAYFAYA DÖN' TUÞU GELÝCEK
											anaSayfaDon.setBounds(265,420,130,17);
											anaSayfaDon.addActionListener(new ActionListener() {
												
												@Override
												public void actionPerformed(ActionEvent e) {
													
													
													OturumAc cagir = new OturumAc();
													setVisible(false);
													
											   }
												
											
										   });

									
										}
										
										else {
											// EÐER TELEFONUNA GELEN 6 HANELÝ DOÐRULAMA KODUNU DOÐRU GÝRMEZSE UYARI MESAJI YAZICAK EKRANDA
											text.setForeground(Color.red);
											text.setBounds(240,280,130,25);
											
											
										}

									}
								});
								
								
								
								
								
								
								
								
								
								
								
							}
						
							
							else {
								
							
								hata.setBounds(225, 380, 300, 40);
								sifreText.setText("");
								sifreTekrarText.setText("");
								tempUyari.setBounds(2255,380,180,30);
								
								
							}
							
						}
					});
					
					
					
				}
				
				
				
				
				
		        
		        
		        
		        
		       
				
				
			}
		});
       
       
        
        getContentPane().add(kullaniciAdi);
        getContentPane().add(kullaniciText);
        getContentPane().add(olustur);
        getContentPane().add(sifre);
        getContentPane().add(sifreText);
        getContentPane().add(sifreTekrar);
        getContentPane().add(sifreTekrarText);
        getContentPane().add(kayitEt);
        getContentPane().add(hata);
        getContentPane().add(telefonNo);
        getContentPane().add(telefonNoText);
        getContentPane().add(kayitEkle);
        getContentPane().add(tempUyari);
        getContentPane().add(koduGirme);
        getContentPane().add(koduGirmeText);
        getContentPane().add(onayla);
        getContentPane().add(anaSayfaDon);
        getContentPane().add(text);
        getContentPane().add(comboBox);
        getContentPane().add(pozisyon);
        getContentPane().add(background);
        
        setVisible(true);
	}

	
	
}
