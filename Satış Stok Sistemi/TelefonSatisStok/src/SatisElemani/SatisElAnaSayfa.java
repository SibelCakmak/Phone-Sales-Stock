package SatisElemani;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import IadeArsivi.IadeListesi;
import SatisArsivi.SatisListesiEkrani;
import YoneticiKýsmý.AnaSayfa;

public class SatisElAnaSayfa extends JFrame{
	
	public SatisElAnaSayfa() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setResizable(false);
        getContentPane().setLayout(null);
        setTitle("TELEFON SATIS-STOK TAKÝBÝ (SATIÞ ELEMNANI) ");
        
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
        
        Image takvim = new ImageIcon(AnaSayfa.class.getResource("/takvim4.png")).getImage();
        
        JLabel ltakvim = new JLabel();
        ltakvim.setIcon(new ImageIcon(takvim));
        ltakvim.setBounds(560,0,50, 50);
        
       
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
        
        JMenuItem versiyonNumarasi = new JMenuItem("Versiyon Numarasý");
        bilgi.add(versiyonNumarasi);
        JMenuItem gelistirici = new JMenuItem("Gelistirici");
        bilgi.add(gelistirici);
        
        versiyonNumarasi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JLabel label = new JLabel();
				label.setText("                                1.5.9");
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
        
        
        // SATIÞ ELEMANI SADECE ÜRÜN LÝSTESÝ, ÜRÜN SATIÞ LÝSTESÝ, ÝADE KISIMLARINA ULAÞIR. BU YÜZDEN SATIÞ ELEMANINA AÝT AYRI BÝR 
        // ANA SAYFA VE OTURUM TASARLADIM

        JButton urunListesi = new JButton("ÜRÜN LÝSTESÝ");
        urunListesi.setBounds(50,180,150,40);
        urunListesi.setBackground(Color.lightGray);
        urunListesi.setForeground(Color.white);

        JButton  urunStisi = new JButton("ÜRÜN SATIÞ LÝSTESÝ");
        urunStisi.setBounds(260,180,150,40);
        urunStisi.setBackground(Color.lightGray);
        urunStisi.setForeground(Color.white);
        
        JButton  iadeEdilenUrunler = new JButton("ÝADE");
        iadeEdilenUrunler.setBounds(470,180,150,40);
        iadeEdilenUrunler.setBackground(Color.lightGray);
        iadeEdilenUrunler.setForeground(Color.white);
     
   
        

        
        urunListesi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SE_StokListesi cagir = new SE_StokListesi();
				setVisible(false);
				
			}
		});
        
        
        
        urunStisi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				SE_SatisListesiEkrani urunSatsSayfasýnaGit = new SE_SatisListesiEkrani();
				setVisible(false);
				
			}
		});
        
        iadeEdilenUrunler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SE_IadeListesi cagir = new SE_IadeListesi();
				setVisible(false);
				
			}
		}); 
        
        getContentPane().add(urunStisi);
        getContentPane().add(urunListesi);
        getContentPane().add(iadeEdilenUrunler);
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
	