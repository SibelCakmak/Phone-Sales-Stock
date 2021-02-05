package YoneticiKýsmý;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

import AnaKýsým.SifreUnuttum;
import KayitOlma.KayitOl;
import OturumBilgileri.OturumBilgiDAL;
import OturumBilgileri.OturumBilgileriGetSet;

public class OturumAc extends JFrame {
	DefaultTableModel tablemodel;
	JTable table;
	OturumBilgiDAL sifreler;

	public OturumAc() {

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(700, 500);
		setResizable(false);
		getContentPane().setLayout(null);
		setTitle("TELEFON SATIS-STOK TAKÝBÝ");

		sifreler = new OturumBilgiDAL("OturumVeri");

		// her kayýt yapýldýðýnda döngü sayýýs bir artsýn diye arttýr deðiþkenini
		// tanýmladým


		// arka plan eklemek için ImageIconu kullandým ve tüm sayfayý kaplamasýný
		// istedim
		Image imgEkle = new ImageIcon(OturumAc.class.getResource("/resim7.jpg")).getImage();
		JLabel background = new JLabel();
		background.setIcon(new ImageIcon(imgEkle));
		background.setBounds(0, 0, 700, 500);

		JLabel girisYapýlmadý = new JLabel();
		girisYapýlmadý.setText("KULLANICI ADI VEYA ÞÝFRE YANLIÞ");
		girisYapýlmadý.setFont(new Font("Serif", Font.ITALIC, 10));

		JLabel baslik = new JLabel();
		baslik.setText("YÖNETÝCÝ GÝRÝÞÝ");
		baslik.setBounds(205, 50, 250, 40);
		baslik.setForeground(Color.DARK_GRAY);
		baslik.setFont(new Font("Serif", Font.ITALIC, 30));

		JLabel kullanici = new JLabel();
		kullanici.setText("KULLANICI ADI :");
		kullanici.setForeground(Color.WHITE);
		kullanici.setBounds(175, 130, 150, 40);

		JLabel sifre = new JLabel();
		sifre.setText("SÝFRE :");
		sifre.setForeground(Color.WHITE);
		sifre.setBounds(175, 210, 150, 40);

		JButton sifremiUnuttum = new JButton("sifremi unuttum ?");
		sifremiUnuttum.setBackground(Color.LIGHT_GRAY);
		sifremiUnuttum.setBounds(245, 380, 150, 20);

		JLabel kayitText = new JLabel();
		kayitText.setText("henüz üye deðil misin ?");
		kayitText.setFont(new Font("Serif", Font.ITALIC, 17));
		kayitText.setForeground(Color.WHITE);
		kayitText.setBounds(243, 408, 180, 21);

		JButton kayitOl = new JButton("kayýt ol");
		kayitOl.setBackground(Color.lightGray);
		kayitOl.setBounds(245, 434, 150, 20);

		JTextField sifreyiGosterText = new JTextField();

		JTextField kullanýcýText = new JTextField();
		kullanýcýText.setBounds(175, 170, 300, 30);

		JPasswordField sifreText = new JPasswordField();
		sifreText.setBounds(175, 250, 300, 30);

		JButton giris = new JButton("GÝRÝÞ");
		giris.setBounds(265, 320, 100, 25);
		giris.setBackground(Color.lightGray);

		sifremiUnuttum.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SifreUnuttum cagir = new SifreUnuttum();
				setVisible(false);
				girisYapýlmadý.setText("");

			}
		});
		
		
		// Mevcut kayýt sayýsýný çekiyoruz. Çünkü for döngüsünde id leri karþýlaþtýrýyor ve olmayan id olduðunda hata verir.
		// Bu sorunu özmek için mevcut kayýt sayýsýný çektim
		int kayitSayi = sifreler.sayi();

		giris.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Ana sayfadaki kayit ol kýsmýndan kayýt olduðumuzda bilgilerimiz veri
				// tabanýnýna kaydoluyor. Bu bilgilere sadece yönetici ulaþabiliyor.
				// ve mevcut kayýtlarý(kullanýcý adý ve þifreleri) bu kýsýmda karþýlaþtýrýyorum.
				// Onun için ilk olarak çekiyorum bilgileri tek tek veri tabanýndan
				for (int i = 1; i <= kayitSayi; i++) {

					OturumBilgileriGetSet k = new OturumBilgileriGetSet();

					k.setId(i);
					String kullaniciyazdir = sifreler.getCellKullanýcýAdi(k, "kullaniciAdi");
					String sifreCek = sifreler.getCellSifre(k, "Sifre");

					// burada pozisyonunu çekiyoruz. burada sadece pozisyonu yönetici olan larýn
					// kullnýcý adý ve þifresi çekilecek
					String pozisyon = sifreler.getCellPozisyon(k, "pozisyon");

					// id ile tek tek çekiyor ve girilern kullanýcý adi ve þifre kombinasyonu veri
					// tabýnda varsa anasayfaya giriyor
					if (kullanýcýText.getText().equals(kullaniciyazdir) && sifreText.getText().equals(sifreCek)
							&& pozisyon.equals("yonetici")) {
						AnaSayfa cagir = new AnaSayfa();
						setVisible(false);
					}

					else {

						girisYapýlmadý.setForeground(Color.RED);
						girisYapýlmadý.setBounds(230, 105, 250, 40);

					}

				}

			}
		});

		kayitOl.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				KayitOl cagir = new KayitOl();
				setVisible(false);

			}
		});

		getContentPane().add(kullanici);
		getContentPane().add(sifre);
		getContentPane().add(kullanýcýText);
		getContentPane().add(sifreText);
		getContentPane().add(sifreyiGosterText);
		getContentPane().add(baslik);
		getContentPane().add(giris);
		// getContentPane().add(sifreyiGoster);
		getContentPane().add(sifremiUnuttum);
		getContentPane().add(girisYapýlmadý);
		getContentPane().add(kayitText);
		getContentPane().add(kayitOl);
		getContentPane().add(background);

		setVisible(true);

	}

}
