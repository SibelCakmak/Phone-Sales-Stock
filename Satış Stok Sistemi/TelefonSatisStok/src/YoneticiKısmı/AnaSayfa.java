package YoneticiKýsmý;

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
        setTitle("TELEFON SATIS-STOK TAKÝBÝ");
        
        
        
       // arka plan eklemek için ImageIconu kullandým ve tüm sayfayý kaplamasýný istedim
        Image imgEkle = new ImageIcon(AnaSayfa.class.getResource("/resim7.jpg")).getImage();
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon(imgEkle));
        background.setBounds(0,0,700, 500);

        // Labelin içinde tarih tutulsun. Stok sisteminde tarih gözüksün
        String tarih = sistemTarihiniGetir("yyyy/MM/dd");
        JLabel ltarih = new JLabel();
        ltarih.setText(tarih);
        ltarih.setBounds(590,13,90,20);
        ltarih.setForeground(Color.BLACK);
        ltarih.setFont(new Font("Serif",Font.ITALIC,16));
        
        
        // Güncel tarihin yanýna estetiklik açýsýndan takvim imgesi koydum.
        Image takvim = new ImageIcon(AnaSayfa.class.getResource("/takvim4.png")).getImage();
        
        JLabel ltakvim = new JLabel();
        ltakvim.setIcon(new ImageIcon(takvim));
        ltakvim.setBounds(560,0,50, 50);
        
       // Menü bar oluþturum sonradan buna menü ekledim
        JMenuBar menu = new JMenuBar();	
        setJMenuBar(menu);
        
        
        JMenu iletisim = new JMenu("Ýletisim");
        menu.add(iletisim);
        JMenu bilgi = new JMenu("Bilgi");
        menu.add(bilgi);
        
         
        
        JMenuItem numara = new JMenuItem("TelNo : 05367 023 56 32");
        iletisim.add(numara);
        JMenuItem mail = new JMenuItem("mail : telefondukkani@gmail.com");
        iletisim.add(mail);
        
        
        
        // burada bilgiye týklayýnca versiyon ve geliþtirici adlý itemler çýkýyor. Onlara týklayýnca onllarla ilgili 
        //bilgiler ekrana mesaj olarak gelir.  
        JMenuItem versiyonNumarasi = new JMenuItem("Versiyon Numarasý");
        bilgi.add(versiyonNumarasi);
        JMenuItem gelistirici = new JMenuItem("Gelistirici");
        bilgi.add(gelistirici);
        
        JMenu geriCikis = new JMenu("Çýkýþ Yap");
        menu.add(geriCikis);
        JMenuItem cikis = new JMenuItem("Sistemden Çýk");
        geriCikis.add(cikis);
        
        
        // oturumu kapatýyor tamamen
        cikis.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				  System.exit(0);
				
			}
		});     
        
        // Versiyon numarasý ve geliþtirici yi menü item olarak ekledim
        versiyonNumarasi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JLabel label = new JLabel();
				label.setText("                                1.5.9");
				
				// JOptionPane, ekrana mesaj vermemize yardýmcý oluyor.
				JOptionPane.showMessageDialog(null, label,"Versiyon Numarasý",JOptionPane.PLAIN_MESSAGE);
				
			}
		}
        );
        
        gelistirici.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JLabel label = new JLabel();
				label.setText("                        SÝBEL ÇAKMAK");
				JOptionPane.showMessageDialog(null, label,"Geliþtirici",JOptionPane.PLAIN_MESSAGE);
				
			}
		}
        );
        
        JButton urunEkle = new JButton("ÜRÜN EKLE");
        urunEkle.setBounds(445,160,150,40); 
        urunEkle.setBackground(Color.lightGray);
        urunEkle.setForeground(Color.white);

        JButton urunListesi = new JButton("ÜRÜN LÝSTESÝ");
        urunListesi.setBounds(50,160,150,40);
        urunListesi.setBackground(Color.lightGray);
        urunListesi.setForeground(Color.white);

        
        JButton calýsanListesi = new JButton("ÇALIÞAN LÝSTESÝ");
        calýsanListesi.setBounds(250,160,150,40);
        calýsanListesi.setBackground(Color.lightGray);
        calýsanListesi.setForeground(Color.white);
   
        
        JButton  urunStisi = new JButton("ÜRÜN SATIÞ LÝSTESÝ");
        urunStisi.setBounds(50,250,150,40);
        urunStisi.setBackground(Color.lightGray);
        urunStisi.setForeground(Color.white);
     
        JButton  genelSifreler = new JButton("GENEL SÝFRELER");
        genelSifreler.setBounds(250,250,150,40);
        genelSifreler.setBackground(Color.lightGray);
        genelSifreler.setForeground(Color.white);
        
        JButton  iadeEdilenUrunler = new JButton("ÝADE");
        iadeEdilenUrunler.setBounds(445,250,150,40);
        iadeEdilenUrunler.setBackground(Color.lightGray);
        iadeEdilenUrunler.setForeground(Color.white);
        

        
        
  // týklayýnca ilgili sayfalara geçiþ yapýlmasý için addAction deyiminden yaralandým      
        urunEkle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				UrunEkle urunEkleSayfasýnaGit = new UrunEkle();
				
				// False dememmin sebbei yeni pencere açýldýðýnda eski pencerenin kapanmasý
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
        
        calýsanListesi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CalisanListe cagir = new CalisanListe();
				setVisible(false);
				
			}
		});
        
        genelSifreler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				OturumBilgileri urunEkleSayfasýnaGit = new OturumBilgileri();
				setVisible(false);
				
			}
		});
        
        urunStisi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				SatisListesiEkrani urunSatsSayfasýnaGit = new SatisListesiEkrani();
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
        getContentPane().add(calýsanListesi);
        getContentPane().add(urunListesi);
        getContentPane().add(genelSifreler);
        getContentPane().add(iadeEdilenUrunler);
        getContentPane().add(ltarih);
        getContentPane().add(ltakvim);
        
        
        // Background u en sona ekrana ekledim  çünkü diðerlerinden önce eklersem eðer üzerine gelmedikçe o nesneler ekranda gözükmez
       getContentPane().add(background);
             
       


 
        
        setVisible(true);
       
	}
	
	
	// Günlük tarihi çekip kayýt tarihlerini daha güvenli yapmak için tam tarihi oluþturuyoruz
	public static String sistemTarihiniGetir(String tarihFormati)
	{
	 Calendar takvim = Calendar.getInstance();
	 SimpleDateFormat sdf = new SimpleDateFormat(tarihFormati);
	 return sdf.format(takvim.getTime());
	}
}
