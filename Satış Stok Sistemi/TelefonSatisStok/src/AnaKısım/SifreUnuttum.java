package AnaK�s�m;

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
import YoneticiK�sm�.OturumAc;

public class SifreUnuttum extends JFrame{
	OturumBilgiDAL KullaniciBilgileri;
	public SifreUnuttum() {
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(700, 520);
        setResizable(false);
        setTitle("S�FRE YEN�LE");
        getContentPane().setLayout(null);
        
        KullaniciBilgileri = new OturumBilgiDAL("OturumVeri");
        
        Image imgEkle = new ImageIcon(PersonelGiris.class.getResource("/resim7.jpg")).getImage();
	     JLabel background = new JLabel();
	     background.setIcon(new ImageIcon(imgEkle));
	     background.setBounds(0,0,700, 500);
        
        JLabel kullan�c� = new JLabel();
        kullan�c�.setText("MEVCUT KULLANICI ADINIZI G�R�N�Z :");
        kullan�c�.setBounds(175, 130, 300, 40);
        
        
        JTextField kullan�c�Text = new JTextField();
        kullan�c�Text.setBounds(175,170,300,30);

        JButton sorgula = new JButton("SORGULA");
        sorgula.setBounds(275,220,100,25);
        sorgula.setForeground(Color.WHITE);
        sorgula.setBackground(Color.orange);
        
        
        JButton geri = new JButton("GER�");
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
		
		// local de�i�kn de�ilde global olmas� i�in burada tan�mlay�p a�a��da �zellik katt�m.
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
					String kullaniciyazdir = KullaniciBilgileri.getCellKullan�c�Adi(k, "kullaniciAdi");
			        System.out.println(kullaniciyazdir);
					if(kullan�c�Text.getText().equals(kullaniciyazdir)) {
						// �ifre yenilemek i�in yeni calss a�mak yerine bu sayfada mevcut olan 3 nesneyi sayfa d���na at�p
						//hem class tasarrufu yapt�m hemde fazla kod sat�r� 
						//yazmak zorunda kalmad�m 
						System.out.println("oldu");
						kullan�c�.setBounds(1000,25,0,0);
						kullan�c�Text.setBounds(1000,25,0,0);
						sorgula.setBounds(1000,25,0,0);
				        yeniSifre.setText("YEN� S�FRE :");
				        yeniSifre.setBounds(200, 130, 150, 40);
				        sifreTekrar.setText("YEN� S�FRE TEKRAR :");
				        sifreTekrar.setBounds(200, 210, 150, 40);

				        yeniSifreText.setBounds(200,170,200,30);

				   
				        sifreTekrarText.setBounds(200,250,200,30);

				        kaydet.setBounds(246,300,90,27);
				        kaydet.setForeground(Color.WHITE);
				        kaydet.setBackground(Color.orange);
				
				        hata.setText("G�R�LEN ��FRELER B�RB�R�NE UYMUYOR! TEKRAR DENEY�N�Z");
				        hata.setFont(new Font("Serif",Font.ITALIC,10));
				        
				         
				        
				        
				        kaydet.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								String sifre =yeniSifreText.getText();
								String tekrar = sifreTekrarText.getText();
								
								if(yeniSifreText.getText().equals(tekrar) && sifreTekrarText.getText().equals(sifre)) {
									// e�er iki �ifre birbirine uyuyorsa tutulan mevcut �ifreyi girilen yeni �ifre olarak de�i�tiriyoruz.
									// �ifrelerin tutuldu�u k�sm� �a��r�p girilen kullan�c� ad�ya sahip �ifreyi g�ncelliyor
									k.setSifre(sifre);
									KullaniciBilgileri.update(k);
									updateTable(KullaniciBilgileri.getDefaultTable());
									JOptionPane.showMessageDialog(null, "�ifreniz De�i�ti");
								}
								
								// Girilen iki �ifre e�it de�ilse �ifre de�i�mez ve hata mesaj� verir
								else {
									
									hata.setForeground(Color.RED);
									hata.setBounds(180, 350, 300, 40);
									yeniSifreText.setText("");
									sifreTekrarText.setText("");
									
								}
								
							}
							
							
						
						});
				        
				        
				     // d�ng�de oldu�u i�in do�ru girsem bile geri kalan kay�t say�s� kadar "KULLANICI ADI MEVCUT DE��L" hayas� vericek bunu �nlemek i�in 
				     //do�ru girildi an d�ng� bitsin diye break komutunu ekledim
				       break;

					
					}
					
					
				}	 
				
					
				
			}
				
				
		});
        
        
        
        getContentPane().add(kullan�c�);
        getContentPane().add(kullan�c�Text);
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
