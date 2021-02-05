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
import YoneticiK�sm�.OturumAc;

public class KayitOl extends JFrame{
	public static int kayitSayisi=11;
	int randomSayi;
	OturumBilgiDAL ekle;
	public KayitOl() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setResizable(false);
        getContentPane().setLayout(null);
        setTitle("TELEFON SATIS-STOK TAK�B�");
        ekle = new OturumBilgiDAL("OturumVeri");
        
        
        Image imgEkle = new ImageIcon(KayitOl.class.getResource("/resim7.jpg")).getImage();
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon(imgEkle));
        background.setBounds(0,0,700, 500);
     
        
        JLabel kullaniciAdi = new JLabel();
        kullaniciAdi.setText("Kullan�c� Ad� :");
        kullaniciAdi.setBounds(140, 90, 150, 25);
        
        JTextField kullaniciText = new JTextField();        
        kullaniciText.setBounds(225,90,185,25);
        
        JButton olustur = new JButton("�LER�");
        olustur.setBounds(421,95,70,17);
        olustur.setBackground(Color.yellow);

        
        
        // ileri didyence bu k�s�mlar a��laca�� i�in burda sadece tan�mlay�p,  t�klad�ksan sonra bu k�s�mlar gelsin diye 
        // addAction k�sm�nda setBounds �  tan�ml�yoruz 
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
        
        
        // pozisyonu yanl�� girilmesi yanl�� kay�ta sonu� a�abilir bazen. Bu y�zden pozsyonu girmesin, se�sin
        
        JLabel pozisyon = new JLabel();
        JComboBox comboBox = new JComboBox( );
        comboBox.addItem("sat�s eleman�");
        comboBox.addItem("stok sorumlusu");
        comboBox.addItem("kasiyer");
        comboBox.setBackground(Color.yellow);
        
        
        
        JLabel  hata = new JLabel();
        hata.setText("girilen �ifreler birbirine uymuyor");
        hata.setForeground(Color.red);
    
        
        olustur.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// E�ER KULLANICI ADI BO�SA UYARI VER�LECEK
				if(kullaniciText.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Kullan�c� Ad�n� Giriniz");
				}
				
				else {
					
					olustur.setBounds(1000,95,70,17); //buttonu ekrandan atmak i�in
					
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
							
							// telefon numaras�n� girmediyse hata alacak
							if(telefonNoText.getText().equals("")) {
								
								tempUyari.setText("Tel No Alan� Bo� B�rak�lamaz");
								tempUyari.setForeground(Color.RED);
								tempUyari.setBounds(225,380,180,30);
							}
					
							
							
							
							
							
							
							String sifree =sifreText.getText();
							String tekrar = sifreTekrarText.getText();

							// bu ko�ulda birbirlerinin i�indeki de�erleri kendilerinki ile kar��la�t�r�yorlar.
							//Ayn� olursa if ko�uluna giricek de�ilse uyar� al�cak �ifreler e�it de�il diye
							if(sifreText.getText().equals(tekrar) && sifreTekrarText.getText().equals(sifree)) {
								
								
								
								
								// YEN� B�R CLASS A�MAK YER�NE, AYNI CLASSIN ARAY�Z�N� BO�ALIP ARAY�Z� YEN�DEN D�ZAYN ETT�M
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
								
								// TELEFONUNA GELEN 6 HANEL� SAYI ���N RANDOM SAY� �RETT�M (tELEFON YER�NE PROJEDE CONSOLA YAZILACAK)
								randomSayi = (int) (Math.random() * 1000000);

								System.out.println(randomSayi);
								
								String telno = telefonNoText.getText();
								
								sifre.setText(telno + " NUMARANIZA");
								sifre.setBounds(231,120,300,25);
								koduGirme.setText(" GELEN 6 HANEL� KODU G�RN�Z");
								koduGirme.setBounds(222, 150, 300, 25);

								
								koduGirmeText.setBounds(220, 190, 200, 30);

								
								onayla.setBounds(280, 235, 90, 25);
								onayla.setBackground(Color.yellow);

								onayla.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {
										// TELEFONA GELEN 6 HANEL� RANDOM SAY�Y� DO�RU G�RERSE OTURUMUMU GER�EKLE�ECEK VE VER� TABANINA G�R�� B�LG�LER� KAYDED�LECEK
										if (koduGirmeText.getText().equals("" + randomSayi)) {
											OturumBilgileriGetSet k = new OturumBilgileriGetSet();
											k.setUsername(kullaniciText.getText());
							                k.setSifre(sifreText.getText());
											k.setTelNo(telefonNoText.getText());
											
											// Combobox nesne olarak tan�mand��� i�in onu ilk olarak stringe �evirip sonra se�ilen pozisyonu veri taban�na kaydettim.
											k.setPozisyon(String.valueOf(comboBox.getSelectedItem()));
											ekle.add(k);
											kayitSayisi = kayitSayisi + 1;
											
											JOptionPane.showMessageDialog(null, "�YEL���N�Z GER�EKLE�M��T�R HO�GELD�N�Z");
											
											
											// OTURUM A�INCA ��LER� B�TT��� ���N D�REK ANA SAYFAYA D�NMES� ���N 'ANA SAYFAYA D�N' TU�U GEL�CEK
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
											// E�ER TELEFONUNA GELEN 6 HANEL� DO�RULAMA KODUNU DO�RU G�RMEZSE UYARI MESAJI YAZICAK EKRANDA
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
