package YoneticiK�sm�;

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

import AnaK�s�m.SifreUnuttum;
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
		setTitle("TELEFON SATIS-STOK TAK�B�");

		sifreler = new OturumBilgiDAL("OturumVeri");

		// her kay�t yap�ld���nda d�ng� say��s bir arts�n diye artt�r de�i�kenini
		// tan�mlad�m


		// arka plan eklemek i�in ImageIconu kulland�m ve t�m sayfay� kaplamas�n�
		// istedim
		Image imgEkle = new ImageIcon(OturumAc.class.getResource("/resim7.jpg")).getImage();
		JLabel background = new JLabel();
		background.setIcon(new ImageIcon(imgEkle));
		background.setBounds(0, 0, 700, 500);

		JLabel girisYap�lmad� = new JLabel();
		girisYap�lmad�.setText("KULLANICI ADI VEYA ��FRE YANLI�");
		girisYap�lmad�.setFont(new Font("Serif", Font.ITALIC, 10));

		JLabel baslik = new JLabel();
		baslik.setText("Y�NET�C� G�R���");
		baslik.setBounds(205, 50, 250, 40);
		baslik.setForeground(Color.DARK_GRAY);
		baslik.setFont(new Font("Serif", Font.ITALIC, 30));

		JLabel kullanici = new JLabel();
		kullanici.setText("KULLANICI ADI :");
		kullanici.setForeground(Color.WHITE);
		kullanici.setBounds(175, 130, 150, 40);

		JLabel sifre = new JLabel();
		sifre.setText("S�FRE :");
		sifre.setForeground(Color.WHITE);
		sifre.setBounds(175, 210, 150, 40);

		JButton sifremiUnuttum = new JButton("sifremi unuttum ?");
		sifremiUnuttum.setBackground(Color.LIGHT_GRAY);
		sifremiUnuttum.setBounds(245, 380, 150, 20);

		JLabel kayitText = new JLabel();
		kayitText.setText("hen�z �ye de�il misin ?");
		kayitText.setFont(new Font("Serif", Font.ITALIC, 17));
		kayitText.setForeground(Color.WHITE);
		kayitText.setBounds(243, 408, 180, 21);

		JButton kayitOl = new JButton("kay�t ol");
		kayitOl.setBackground(Color.lightGray);
		kayitOl.setBounds(245, 434, 150, 20);

		JTextField sifreyiGosterText = new JTextField();

		JTextField kullan�c�Text = new JTextField();
		kullan�c�Text.setBounds(175, 170, 300, 30);

		JPasswordField sifreText = new JPasswordField();
		sifreText.setBounds(175, 250, 300, 30);

		JButton giris = new JButton("G�R��");
		giris.setBounds(265, 320, 100, 25);
		giris.setBackground(Color.lightGray);

		sifremiUnuttum.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SifreUnuttum cagir = new SifreUnuttum();
				setVisible(false);
				girisYap�lmad�.setText("");

			}
		});
		
		
		// Mevcut kay�t say�s�n� �ekiyoruz. ��nk� for d�ng�s�nde id leri kar��la�t�r�yor ve olmayan id oldu�unda hata verir.
		// Bu sorunu �zmek i�in mevcut kay�t say�s�n� �ektim
		int kayitSayi = sifreler.sayi();

		giris.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Ana sayfadaki kayit ol k�sm�ndan kay�t oldu�umuzda bilgilerimiz veri
				// taban�n�na kaydoluyor. Bu bilgilere sadece y�netici ula�abiliyor.
				// ve mevcut kay�tlar�(kullan�c� ad� ve �ifreleri) bu k�s�mda kar��la�t�r�yorum.
				// Onun i�in ilk olarak �ekiyorum bilgileri tek tek veri taban�ndan
				for (int i = 1; i <= kayitSayi; i++) {

					OturumBilgileriGetSet k = new OturumBilgileriGetSet();

					k.setId(i);
					String kullaniciyazdir = sifreler.getCellKullan�c�Adi(k, "kullaniciAdi");
					String sifreCek = sifreler.getCellSifre(k, "Sifre");

					// burada pozisyonunu �ekiyoruz. burada sadece pozisyonu y�netici olan lar�n
					// kulln�c� ad� ve �ifresi �ekilecek
					String pozisyon = sifreler.getCellPozisyon(k, "pozisyon");

					// id ile tek tek �ekiyor ve girilern kullan�c� adi ve �ifre kombinasyonu veri
					// tab�nda varsa anasayfaya giriyor
					if (kullan�c�Text.getText().equals(kullaniciyazdir) && sifreText.getText().equals(sifreCek)
							&& pozisyon.equals("yonetici")) {
						AnaSayfa cagir = new AnaSayfa();
						setVisible(false);
					}

					else {

						girisYap�lmad�.setForeground(Color.RED);
						girisYap�lmad�.setBounds(230, 105, 250, 40);

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
		getContentPane().add(kullan�c�Text);
		getContentPane().add(sifreText);
		getContentPane().add(sifreyiGosterText);
		getContentPane().add(baslik);
		getContentPane().add(giris);
		// getContentPane().add(sifreyiGoster);
		getContentPane().add(sifremiUnuttum);
		getContentPane().add(girisYap�lmad�);
		getContentPane().add(kayitText);
		getContentPane().add(kayitOl);
		getContentPane().add(background);

		setVisible(true);

	}

}
