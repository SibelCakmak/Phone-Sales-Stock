package YoneticiK�sm�;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import CalisanBilgileri.CalisanListe;
import IadeArsivi.IadeListesi;
import OturumBilgileri.OturumBilgileri;
import SatisArsivi.SatisListesiEkrani;
import Stok.StokListesi;
import UrunEkle.UrunEkle;

public class AnaSayfa extends JFrame{
	public AnaSayfa() {
	
	
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setResizable(false);
        getContentPane().setLayout(null);
        setTitle("TELEFON SATIS-STOK TAK�B�");
        
        
        
       // arka plan eklemek i�in ImageIconu kulland�m ve t�m sayfay� kaplamas�n� istedim
        Image imgEkle = new ImageIcon(AnaSayfa.class.getResource("/resim7.jpg")).getImage();
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon(imgEkle));
        background.setBounds(0,0,700, 500);

        // Labelin i�inde tarih tutulsun. Stok sisteminde tarih g�z�ks�n
        String tarih = sistemTarihiniGetir("yyyy/MM/dd");
        JLabel ltarih = new JLabel();
        ltarih.setText(tarih);
        ltarih.setBounds(590,13,90,20);
        ltarih.setForeground(Color.BLACK);
        ltarih.setFont(new Font("Serif",Font.ITALIC,16));
        
        
        // G�ncel tarihin yan�na estetiklik a��s�ndan takvim imgesi koydum.
        Image takvim = new ImageIcon(AnaSayfa.class.getResource("/takvim4.png")).getImage();
        
        JLabel ltakvim = new JLabel();
        ltakvim.setIcon(new ImageIcon(takvim));
        ltakvim.setBounds(560,0,50, 50);
        
       // Men� bar olu�turum sonradan buna men� ekledim
        JMenuBar menu = new JMenuBar();	
        setJMenuBar(menu);
        
        
        JMenu iletisim = new JMenu("�letisim");
        menu.add(iletisim);
        JMenu bilgi = new JMenu("Bilgi");
        menu.add(bilgi);
        
         
        
        JMenuItem numara = new JMenuItem("TelNo : 05367 023 56 32");
        iletisim.add(numara);
        JMenuItem mail = new JMenuItem("mail : telefondukkani@gmail.com");
        iletisim.add(mail);
        
        
        
        // burada bilgiye t�klay�nca versiyon ve geli�tirici adl� itemler ��k�yor. Onlara t�klay�nca onllarla ilgili 
        //bilgiler ekrana mesaj olarak gelir.  
        JMenuItem versiyonNumarasi = new JMenuItem("Versiyon Numaras�");
        bilgi.add(versiyonNumarasi);
        JMenuItem gelistirici = new JMenuItem("Gelistirici");
        bilgi.add(gelistirici);
        
        JMenu geriCikis = new JMenu("��k�� Yap");
        menu.add(geriCikis);
        JMenuItem cikis = new JMenuItem("Sistemden ��k");
        geriCikis.add(cikis);
        
        
        // oturumu kapat�yor tamamen
        cikis.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				  System.exit(0);
				
			}
		});     
        
        // Versiyon numaras� ve geli�tirici yi men� item olarak ekledim
        versiyonNumarasi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JLabel label = new JLabel();
				label.setText("                                1.5.9");
				
				// JOptionPane, ekrana mesaj vermemize yard�mc� oluyor.
				JOptionPane.showMessageDialog(null, label,"Versiyon Numaras�",JOptionPane.PLAIN_MESSAGE);
				
			}
		}
        );
        
        gelistirici.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JLabel label = new JLabel();
				label.setText("                        S�BEL �AKMAK");
				JOptionPane.showMessageDialog(null, label,"Geli�tirici",JOptionPane.PLAIN_MESSAGE);
				
			}
		}
        );
        
        JButton urunEkle = new JButton("�R�N EKLE");
        urunEkle.setBounds(445,160,150,40); 
        urunEkle.setBackground(Color.lightGray);
        urunEkle.setForeground(Color.white);

        JButton urunListesi = new JButton("�R�N L�STES�");
        urunListesi.setBounds(50,160,150,40);
        urunListesi.setBackground(Color.lightGray);
        urunListesi.setForeground(Color.white);

        
        JButton cal�sanListesi = new JButton("�ALI�AN L�STES�");
        cal�sanListesi.setBounds(250,160,150,40);
        cal�sanListesi.setBackground(Color.lightGray);
        cal�sanListesi.setForeground(Color.white);
   
        
        JButton  urunStisi = new JButton("�R�N SATI� L�STES�");
        urunStisi.setBounds(50,250,150,40);
        urunStisi.setBackground(Color.lightGray);
        urunStisi.setForeground(Color.white);
     
        JButton  genelSifreler = new JButton("GENEL S�FRELER");
        genelSifreler.setBounds(250,250,150,40);
        genelSifreler.setBackground(Color.lightGray);
        genelSifreler.setForeground(Color.white);
        
        JButton  iadeEdilenUrunler = new JButton("�ADE");
        iadeEdilenUrunler.setBounds(445,250,150,40);
        iadeEdilenUrunler.setBackground(Color.lightGray);
        iadeEdilenUrunler.setForeground(Color.white);
        

        
        
  // t�klay�nca ilgili sayfalara ge�i� yap�lmas� i�in addAction deyiminden yaraland�m      
        urunEkle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				UrunEkle urunEkleSayfas�naGit = new UrunEkle();
				
				// False dememmin sebbei yeni pencere a��ld���nda eski pencerenin kapanmas�
				setVisible(false);
				
			}
		});
        
        urunListesi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StokListesi cagir = new StokListesi();
				setVisible(false);
				
			}
		});
        
        cal�sanListesi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CalisanListe cagir = new CalisanListe();
				setVisible(false);
				
			}
		});
        
        genelSifreler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				OturumBilgileri urunEkleSayfas�naGit = new OturumBilgileri();
				setVisible(false);
				
			}
		});
        
        urunStisi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				SatisListesiEkrani urunSatsSayfas�naGit = new SatisListesiEkrani();
				setVisible(false);
				
			}
		});
        
        iadeEdilenUrunler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				IadeListesi cagir = new IadeListesi();
				setVisible(false);
				
			}
		});
        

        
        getContentPane().add(urunStisi);
        getContentPane().add(urunEkle);
        getContentPane().add(cal�sanListesi);
        getContentPane().add(urunListesi);
        getContentPane().add(genelSifreler);
        getContentPane().add(iadeEdilenUrunler);
        getContentPane().add(ltarih);
        getContentPane().add(ltakvim);
        
        
        // Background u en sona ekrana ekledim  ��nk� di�erlerinden �nce eklersem e�er �zerine gelmedik�e o nesneler ekranda g�z�kmez
       getContentPane().add(background);
             
       


 
        
        setVisible(true);
       
	}
	
	
	// G�nl�k tarihi �ekip kay�t tarihlerini daha g�venli yapmak i�in tam tarihi olu�turuyoruz
	public static String sistemTarihiniGetir(String tarihFormati)
	{
	 Calendar takvim = Calendar.getInstance();
	 SimpleDateFormat sdf = new SimpleDateFormat(tarihFormati);
	 return sdf.format(takvim.getTime());
	}
}
