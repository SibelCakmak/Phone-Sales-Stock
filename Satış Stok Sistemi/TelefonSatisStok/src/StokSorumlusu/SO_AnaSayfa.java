package StokSorumlusu;

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

public class SO_AnaSayfa extends JFrame{
	public SO_AnaSayfa() {
		
		// DEPO SORUMLUSU  SADECE �R�N L�STES�, �R�N SATI� L�STES�, �ADE, �R�N EKLE  KISIMLARINA ULA�IR. BU Y�ZDEN SATI� ELEMANINA A�T AYRI B�R 
        // ANA SAYFA VE OTURUM A�MA SAYFASI TARARLADIM TASARLADIM
	
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setResizable(false);
        getContentPane().setLayout(null);
        setTitle("TELEFON SATIS-STOK TAK�B�");
        
        
        
       // arka plan eklemek i�in ImageIconu kulland�m ve t�m sayfay� kaplamas�n� istedim
        Image imgEkle = new ImageIcon(SO_AnaSayfa.class.getResource("/resim7.jpg")).getImage();
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
        
        Image takvim = new ImageIcon(SO_AnaSayfa.class.getResource("/takvim4.png")).getImage();
        
        JLabel ltakvim = new JLabel();
        ltakvim.setIcon(new ImageIcon(takvim));
        ltakvim.setBounds(560,0,50, 50);
        
       
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
        
        JMenuItem versiyonNumarasi = new JMenuItem("Versiyon Numaras�");
        bilgi.add(versiyonNumarasi);
        JMenuItem gelistirici = new JMenuItem("Gelistirici");
        bilgi.add(gelistirici);
        
        versiyonNumarasi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JLabel label = new JLabel();
				label.setText("                                1.5.9");
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
        
        

        
        JButton urunEkle = new JButton("�R�N EKLE");
        urunEkle.setBounds(445,160,150,40); 
        urunEkle.setBackground(Color.lightGray);
        urunEkle.setForeground(Color.white);

        JButton urunListesi = new JButton("�R�N L�STES�");
        urunListesi.setBounds(50,160,150,40);
        urunListesi.setBackground(Color.lightGray);
        urunListesi.setForeground(Color.white);
   
        JButton  urunStisi = new JButton("�R�N SATI� L�STES�");
        urunStisi.setBounds(250,160,150,40);
        urunStisi.setBackground(Color.lightGray);
        urunStisi.setForeground(Color.white);

        JButton  iadeEdilenUrunler = new JButton("�ADE");
        iadeEdilenUrunler.setBounds(250,250,150,40);
        iadeEdilenUrunler.setBackground(Color.lightGray);
        iadeEdilenUrunler.setForeground(Color.white);
        

        
        
        
        urunEkle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				SO_UrunEkle urunEkleSayfas�naGit = new SO_UrunEkle();
				setVisible(false);
				
			}
		});
        
        urunListesi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SO_StokListesi cagir = new SO_StokListesi();
				setVisible(false);
				
			}
		});

        urunStisi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				SO_SatisListesi urunSatsSayfas�naGit = new SO_SatisListesi();
				setVisible(false);
				
			}
		});
        
        iadeEdilenUrunler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SO_IadeListesi cagir = new SO_IadeListesi();
				setVisible(false);
				
			}
		});
        
        
        getContentPane().add(urunStisi);
        getContentPane().add(urunEkle);
        getContentPane().add(urunListesi);
        getContentPane().add(iadeEdilenUrunler);
        getContentPane().add(ltarih);
        getContentPane().add(ltakvim);
       getContentPane().add(background);
             
       


 
        
        setVisible(true);
       
	}
	
	public static String sistemTarihiniGetir(String tarihFormati)
	{
	 Calendar takvim = Calendar.getInstance();
	 SimpleDateFormat sdf = new SimpleDateFormat(tarihFormati);
	 return sdf.format(takvim.getTime());
	}
}
