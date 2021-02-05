package AnaKýsým;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import OturumBilgileri.OturumBilgiDAL;
import OturumBilgileri.OturumBilgileriGetSet;
import YoneticiKýsmý.OturumAc;

public class SifreUnuttum extends JFrame{
	OturumBilgiDAL KullaniciBilgileri;
	public SifreUnuttum() {
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(700, 520);
        setResizable(false);
        setTitle("SÝFRE YENÝLE");
        getContentPane().setLayout(null);
        
        KullaniciBilgileri = new OturumBilgiDAL("OturumVeri");
        
        Image imgEkle = new ImageIcon(PersonelGiris.class.getResource("/resim7.jpg")).getImage();
	     JLabel background = new JLabel();
	     background.setIcon(new ImageIcon(imgEkle));
	     background.setBounds(0,0,700, 500);
        
        JLabel kullanýcý = new JLabel();
        kullanýcý.setText("MEVCUT KULLANICI ADINIZI GÝRÝNÝZ :");
        kullanýcý.setBounds(175, 130, 300, 40);
        
        
        JTextField kullanýcýText = new JTextField();
        kullanýcýText.setBounds(175,170,300,30);

        JButton sorgula = new JButton("SORGULA");
        sorgula.setBounds(275,220,100,25);
        sorgula.setForeground(Color.WHITE);
        sorgula.setBackground(Color.orange);
        
        
        JButton geri = new JButton("GERÝ");
		geri.setBounds(10,440,80,17);
		geri.setForeground(Color.WHITE);
		geri.setBackground(Color.orange);
		
		geri.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {	
				OturumAc cagir = new OturumAc();
				setVisible(false);
			}
		});
		
		// local deðiþkn deðilde global olmasý için burada tanýmlayýp aþaðýda özellik kattým.
		JLabel yeniSifre = new JLabel();
		JLabel sifreTekrar = new JLabel();
		JTextField yeniSifreText = new JTextField();
		JTextField sifreTekrarText = new JTextField();
		JButton kaydet = new JButton("KAYDET");
		 JLabel  hata = new JLabel();
        sorgula.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 1; i<=20 ;i++) {
					OturumBilgileriGetSet k = new OturumBilgileriGetSet();
					k.setId(i); 
					String kullaniciyazdir = KullaniciBilgileri.getCellKullanýcýAdi(k, "kullaniciAdi");
			        System.out.println(kullaniciyazdir);
					if(kullanýcýText.getText().equals(kullaniciyazdir)) {
						// Þifre yenilemek için yeni calss açmak yerine bu sayfada mevcut olan 3 nesneyi sayfa dýþýna atýp
						//hem class tasarrufu yaptým hemde fazla kod satýrý 
						//yazmak zorunda kalmadým 
						System.out.println("oldu");
						kullanýcý.setBounds(1000,25,0,0);
						kullanýcýText.setBounds(1000,25,0,0);
						sorgula.setBounds(1000,25,0,0);
				        yeniSifre.setText("YENÝ SÝFRE :");
				        yeniSifre.setBounds(200, 130, 150, 40);
				        sifreTekrar.setText("YENÝ SÝFRE TEKRAR :");
				        sifreTekrar.setBounds(200, 210, 150, 40);

				        yeniSifreText.setBounds(200,170,200,30);

				   
				        sifreTekrarText.setBounds(200,250,200,30);

				        kaydet.setBounds(246,300,90,27);
				        kaydet.setForeground(Color.WHITE);
				        kaydet.setBackground(Color.orange);
				
				        hata.setText("GÝRÝLEN ÞÝFRELER BÝRBÝRÝNE UYMUYOR! TEKRAR DENEYÝNÝZ");
				        hata.setFont(new Font("Serif",Font.ITALIC,10));
				        
				         
				        
				        
				        kaydet.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								String sifre =yeniSifreText.getText();
								String tekrar = sifreTekrarText.getText();
								
								if(yeniSifreText.getText().equals(tekrar) && sifreTekrarText.getText().equals(sifre)) {
									// eðer iki þifre birbirine uyuyorsa tutulan mevcut þifreyi girilen yeni þifre olarak deðiþtiriyoruz.
									// Þifrelerin tutulduðu kýsmý çaðýrýp girilen kullanýcý adýya sahip þifreyi güncelliyor
									k.setSifre(sifre);
									KullaniciBilgileri.update(k);
									updateTable(KullaniciBilgileri.getDefaultTable());
									JOptionPane.showMessageDialog(null, "Þifreniz Deðiþti");
								}
								
								// Girilen iki þifre eþit deðilse þifre deðiþmez ve hata mesajý verir
								else {
									
									hata.setForeground(Color.RED);
									hata.setBounds(180, 350, 300, 40);
									yeniSifreText.setText("");
									sifreTekrarText.setText("");
									
								}
								
							}
							
							
						
						});
				        
				        
				     // döngüde olduðu için doðru girsem bile geri kalan kayýt sayýsý kadar "KULLANICI ADI MEVCUT DEÐÝL" hayasý vericek bunu önlemek için 
				     //doðru girildi an döngü bitsin diye break komutunu ekledim
				       break;

					
					}
					
					
				}	 
				
					
				
			}
				
				
		});
        
        
        
        getContentPane().add(kullanýcý);
        getContentPane().add(kullanýcýText);
        getContentPane().add(sorgula);
        getContentPane().add(geri);
        getContentPane().add(sifreTekrar);
        getContentPane().add(sifreTekrarText);
        getContentPane().add(yeniSifre);
        getContentPane().add(yeniSifreText);
        getContentPane().add(kaydet);
        getContentPane().add(hata);
        getContentPane().add(background);

        
        
        
        
        
        setVisible(true);
       
	
	}
	
	private void updateTable(ArrayList<ArrayList<String>> list) {
		Object columnTitle[]=KullaniciBilgileri.getColumnList(KullaniciBilgileri.getTableSifre()).toArray();
		Object rows[][]=KullaniciBilgileri.getObjectArray(list);
	}

}
