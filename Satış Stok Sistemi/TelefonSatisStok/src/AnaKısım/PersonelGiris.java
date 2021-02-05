package AnaKýsým;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Kasiyer.K_OturumAc;
import SatisElemani.SatisElemaniOturumAC;
import StokSorumlusu.SO_OturumAC;
import YoneticiKýsmý.OturumAc;

public class PersonelGiris extends JFrame{
	
	public PersonelGiris() {
			
		
		
		//Burada hangi pozisyondaysa ona girmnesi için þýklar bulunan sayfalar yaratýlýyor.
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		setTitle("SATIÞ-STOK TAKÝP SÝSTEMÝ");
		
		// arka plan eklemek için ImageIconu kullandým ve tüm sayfayý kaplamasýný istedim
        Image imgEkle = new ImageIcon(PersonelGiris.class.getResource("/resim7.jpg")).getImage();
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon(imgEkle));
        background.setBounds(0,0,700, 500);
		
		getContentPane().setLayout(null);
		JButton yonetici = new JButton("YÖNETÝCÝ");
		yonetici.setBounds(270,130,150,35);
		yonetici.setBackground(Color.GRAY);
		yonetici.setForeground(Color.white);
		
		JButton satisElemani = new JButton("SATÝÞ ELEMANI");
		satisElemani.setBounds(270,190,150,35);
		satisElemani.setBackground(Color.GRAY);
		satisElemani.setForeground(Color.white);
		
		JButton depoSorumlusu = new JButton("DEPO SORUMLUSU");
		depoSorumlusu.setBounds(270,250,150,35);
		depoSorumlusu.setBackground(Color.GRAY);
		depoSorumlusu.setForeground(Color.white);
		
		JButton kasiyer = new JButton("KASÝYER");
		kasiyer.setBounds(270,310,150,35);
		kasiyer.setBackground(Color.GRAY);
		kasiyer.setForeground(Color.white);
		
		
		
		
		// Her yönetici pozisyonuna göre farklý giriþe ve özelliklere  sahiptir. Bu yüzden ilk giriþ yapýldýðýnda 
		//herkez kendi pozisyonu yazdýðý buttona basacak.

		yonetici.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				OturumAc cagir = new OturumAc();
				setVisible(false);
				
			}
		});
		
		satisElemani.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SatisElemaniOturumAC cagir = new SatisElemaniOturumAC();
				setVisible(false);
				
			}
		});
			
		depoSorumlusu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SO_OturumAC cagir = new SO_OturumAC();
				setVisible(false);
				
			}
		});
		
		kasiyer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				K_OturumAc cagir = new K_OturumAc();
				setVisible(false);
				
			}
		});
	
		getContentPane().add(yonetici);
		getContentPane().add(kasiyer);
		getContentPane().add(satisElemani);
		getContentPane().add(depoSorumlusu);
		 // Background u en sona ekrana ekledim  çünkü diðerlerinden önce eklersem eðer üzerine gelmedikçe o nesneler ekranda gözükmez
		getContentPane().add(background);
		
		setVisible(true);
	}
	

}
