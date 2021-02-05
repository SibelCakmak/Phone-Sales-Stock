package UrunEkle;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import IadeArsivi.IadeListesi;
import SatisArsivi.SatisListesiEkrani;
import Stok.StokListesi;
import YoneticiK�sm�.AnaSayfa;

public class UrunEkle extends JFrame {
	UrunDAL dataEkle;

    public UrunEkle() {
    	
    			
    	dataEkle = new UrunDAL("EkleUrun");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(700, 520);
        setResizable(false);
        getContentPane().setLayout(null);
        setTitle("TELEFON SATIS-STOK TAK�B�");
        
        Image imgEkle = new ImageIcon(UrunEkle.class.getResource("/resim7.jpg")).getImage();
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon(imgEkle));
        background.setBounds(0,0,700, 500);
        

        
        // Bu k�s�m proje i�in kullan�lacak men� bar k�sm�d�r 
        JMenuBar menu = new JMenuBar();
        setJMenuBar(menu);
        JMenu liste = new JMenu("Stok");
        menu.add(liste);
        JMenu geriCikis = new JMenu("��k�� Yap");
        menu.add(geriCikis);


        
        JMenuItem anasayfayaDon = new JMenuItem("Ana Sayfaya D�n");
        geriCikis.add(anasayfayaDon);
        JMenuItem cikis = new JMenuItem("Sistemden ��k");
        geriCikis.add(cikis);
        
        
        anasayfayaDon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				AnaSayfa anaSayfaGecis = new AnaSayfa();
				setVisible(false);
				
				
			}
		});
        
        
        cikis.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				  System.exit(0);
				
			}
		});
        
        JMenuItem mevcutListe = new JMenuItem("�r�n Listesi");
        liste.add(mevcutListe);
        JMenuItem satilanUrunListe = new JMenuItem("Sat�lan �r�n Listesi");
        liste.add(satilanUrunListe);
        JMenuItem iadeListe = new JMenuItem("�ade Edilen �r�n Listesi");
        liste.add(iadeListe);
        
        
        mevcutListe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StokListesi cagir = new StokListesi();
				setVisible(false);
				
			}
		});
        
        satilanUrunListe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SatisListesiEkrani cagir = new SatisListesiEkrani();
				setVisible(false);	
			}
		});
        iadeListe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				IadeListesi cagir = new IadeListesi();
				setVisible(false);	
			}
		});
        
        
        
        
        
        JLabel adi = new JLabel();
        adi.setText("�R�N ADI :");
        adi.setBounds(110, 50, 150, 40);
      
        
        JLabel kategori = new JLabel();
        kategori.setText("KATEGOR�S� :");
        kategori.setBounds(110, 100, 150, 40);
        
        JLabel adet = new JLabel();
        adet.setText("ADED� :");
        adet.setBounds(110, 150, 150, 40);
        
        JLabel hafiza = new JLabel();
        hafiza.setText("HAFIZA :");
        hafiza.setBounds(110, 200, 150, 40);
        
        JLabel alisFiyati = new JLabel();
        alisFiyati.setText("ALI� F�YATI :");
        alisFiyati.setBounds(110, 250, 150, 40);
        
        JLabel satisFiyati = new JLabel();
        satisFiyati.setText("SATI� F�YATI :");
        satisFiyati.setBounds(110, 300, 150, 40);
        
        JLabel tarih = new JLabel();
        tarih.setText("TAR�H� :");
        tarih.setBounds(110, 350, 150, 40);
        
        

        
        JTextField tfAdi = new JTextField();
        tfAdi.setBounds(210,53,300,30);
        
        JTextField tfKategori = new JTextField();
        tfKategori.setBounds(210,103,300,30);
        
        JTextField  tfAdedi = new JTextField();
        tfAdedi.setBounds(210,153,300,30);
        
        JTextField tfHafiza = new JTextField();
        tfHafiza.setBounds(210,203,300,30);
        
        JTextField tfAlisFiyati = new JTextField();
       tfAlisFiyati.setBounds(210,253,300,30);
        
        JTextField tfSatisFiyati = new JTextField();
        tfSatisFiyati.setBounds(210,303,300,30);
        
        JTextField tfTarih = new JTextField();
        tfTarih.setBounds(210,353,300,30);
        
        
        // elle girmesinden ziyade tarih otomatik atan�r. isterse de�i�tirir ve ona uygun olan tarihi girer
        String tarihOtomatik = sistemTarihiniGetir("yyyy/MM/dd H:mm");
        
        tfTarih.setText(tarihOtomatik);
        
        
        JButton ekle = new JButton("EKLE");
        ekle.setBounds(305,403,100,30);
        ekle.setBackground(Color.orange);
        ekle.setForeground(Color.white);
        

        JButton geri = new JButton("GER�");
        geri.setBounds(10,445,70,13);
        geri.setBackground(Color.orange);
        geri.setForeground(Color.white);
        
        
        
        ekle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// girilen �r�nler databaseye eklesin
				
				UrunBilgileri urun = new UrunBilgileri();
				urun.setUrunAdi(tfAdi.getText());
				urun.setKategori(tfKategori.getText());
				urun.setAdedi(Integer.valueOf(tfAdedi.getText()));
				urun.setHafiza(tfHafiza.getText());
				urun.setAlisFiyati(Integer.valueOf(tfAlisFiyati.getText()));
				urun.setSatisFiyati(Integer.valueOf(tfSatisFiyati.getText()));
				urun.setTarih(tfTarih.getText());
				dataEkle.add(urun);
				JOptionPane.showMessageDialog(null, "�R�N EKLEND�");
				
				
				
				// Kay�t eklendikten sonra girilen kay�t bilgileri text fieldlerden temilensin. Ard�ndan yeni bir 
				// de�er girmeye kalk�nd���nda eski de�erler silmek zorunda kalmas�n
				tfAdi.setText("");
				tfAdedi.setText("");
				tfAlisFiyati.setText("");
				tfHafiza.setText("");
				tfKategori.setText("");
				tfSatisFiyati.setText("");
				
				
				
				
				
			}
		});
        
        
        

        geri.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AnaSayfa anaSayfaGecis = new AnaSayfa();
				setVisible(false);
				
			}
		});
        
     
        
        

        getContentPane().add(adi);
        getContentPane().add(kategori);
        getContentPane().add(adet);
        getContentPane().add(alisFiyati);
        getContentPane().add(tarih);
        getContentPane().add(tfAdi);
        getContentPane().add(tfKategori);
        getContentPane().add(tfAdedi);
        getContentPane().add(tfAlisFiyati);
        getContentPane().add(tfTarih);
        getContentPane().add(satisFiyati);
        getContentPane().add(tfSatisFiyati);
        getContentPane().add(hafiza);
        getContentPane().add(tfHafiza);
        getContentPane().add(ekle);
        getContentPane().add(geri);
        // Background u en sona ekrana ekledim  ��nk� di�erlerinden �nce eklersem e�er �zerine gelmedik�e o nesneler ekranda g�z�kmez
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