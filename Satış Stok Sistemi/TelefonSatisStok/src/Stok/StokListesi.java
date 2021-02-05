package Stok;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import SatisArsivi.SatisListesiDAL;
import SatisArsivi.SatisListesiGetSet;
import UrunEkle.UrunBilgileri;
import UrunEkle.UrunDAL;
import UrunEkle.UrunEkle;
import YoneticiK�sm�.AnaSayfa;

public class StokListesi extends  JFrame{

	DefaultTableModel tablemodel;
	JTable table;
	UrunDAL ekle;
	SatisListesiDAL satisEkle;
	int id;
	public StokListesi() {
		
		
		// Burada yeni bir veri taban� yaratmaktan ziyade var olan bir veri tab�n� �ekiyoruz bu y�zden tablonun ad�n� vra olan tablonun ayn�s�
		//olarak ya�yoruz. Yanl�� yazarsak e�er yeni bir database olu�ur. Ve �ekmek istedi�imiz verileri �ekip ekrana yazamay�z. 
		ekle = new UrunDAL("EkleUrun");
		
		
		
		// Sat�� veilerin tutmak i�in burada bir database olu�turuyoruz. Ve her sat�lan �r�n burada depolan�p sat�� listesine aktar�lacak.
		satisEkle = new SatisListesiDAL("SatisEkrani");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		setTitle("STOK L�STES�");
		getContentPane().setLayout(null);

		
		
		// arka plan eklemek i�in ImageIcon u kulland�m. Bunun i�in "img" ad�nda bir dosya olu�turup foto�raflar� bunun i�in at�p �ektim
		 Image imgEkle = new ImageIcon(UrunEkle.class.getResource("/resim7.jpg")).getImage();
	     JLabel background = new JLabel();
	     background.setIcon(new ImageIcon(imgEkle));
	      background.setBounds(0,0,700, 500);
		
		
		JButton geri = new JButton("GER�");
		geri.setBounds(10,430,80,20);
		geri.setBackground(Color.orange);
		
		JButton urunEkle = new JButton("EKLE");
		urunEkle.setBounds(596,430,80,20);
		urunEkle.setBackground(Color.orange);
		
		JLabel aramaAd� = new JLabel();
		aramaAd�.setText("ARAMA  ");
		aramaAd�.setBounds(250,5,80,20);
		
		JTextField arama = new JTextField();
		arama.setBounds(310,5,130,20);
		
		
		
		//  Tablo �ekilde verilerin yaz�lmas� i�in DefaultTableModel deyiminden yararlan�yoruz.
		tablemodel = new DefaultTableModel();
		table = new JTable();
		table.setModel(tablemodel);
		table.setEnabled(false);
		updateTable(ekle.getDefaultTable());
		table.setBackground(Color.orange);
		
		
		// Tablonun ekranda g�z�kmesi ve o alan� belirlemek i�in bir tane "JScrollPane" tan�mlad�k
		JScrollPane liste = new JScrollPane(table);
		liste.setBounds(10, 29, 665, 400);
		
		
		JPopupMenu pm = new JPopupMenu();
		JMenuItem guncelle = new JMenuItem("Kayd� G�ncelle");
		JMenuItem sil = new JMenuItem("Kayd� Sil");
		JMenuItem satildi = new JMenuItem("Sat�ld�");
		pm.add(guncelle);
		pm.add(sil);
		pm.add(satildi);
		
		
		
		// g�ncel tarihi �ektim burada. Tarih giri�leri daha do�ru olsun diye
		String tarih = sistemTarihiniGetir("yyyy/MM/dd H:mm");
		
	
		

		table.getTableHeader().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				String columnName = table.getColumnName(table.columnAtPoint(e.getPoint()));
				updateTable(ekle.getSortedTable(columnName));
			}
			
		});
		
		
		
		arama.getDocument().addDocumentListener(new DocumentListener() {
			
			public void update() {
				updateTable(ekle.getSearchedTable(arama.getText()));
			}
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				update();
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				update();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				update();
			}
		});

		
		guncelle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				UrunBilgileri k = new UrunBilgileri();
				k.setId(id);
				
                JPanel p = new JPanel();
                p.setSize(150,150);
                
                
             
				JTextField tfadd=new JTextField("ad");
				tfadd.setBounds(0, 0, 40, 30);
                JTextField tfkategori=new JTextField("katgori");
				tfadd.setBounds(40, 0, 40, 30);
				JTextField tfadedi=new JTextField("adedi");
				tfadedi.setBounds(80, 0, 40, 30);
				JTextField tfhafiza=new JTextField("hafiza");
				tfhafiza.setBounds(120, 0, 40, 30);
				JTextField tfalis=new JTextField("alifiyati");
				tfalis.setBounds(160, 0, 40, 30);
				JTextField tfsatis=new JTextField("satisfiyati");
				tfsatis.setBounds(200, 0, 40, 30);
				
				// JTextField leri panele at�yoruz burada
				p.add(tfadd);
				p.add(tfkategori);
                p.add(tfadedi);
				p.add(tfhafiza);
				p.add(tfalis);
				p.add(tfsatis);
				
				JOptionPane.showMessageDialog(null, p,"VER�LER� G�NCELLE",JOptionPane.PLAIN_MESSAGE);
				// Burada veriler g�ncelleniyor. Tarih ise otomatik olarak atan�yor.
				k.setUrunAdi(tfadd.getText());
				k.setKategori(tfkategori.getText());;
				k.setAdedi(Integer.valueOf(tfadedi.getText()));
				k.setHafiza(tfhafiza.getText());
                k.setAlisFiyati(Integer.valueOf(tfalis.getText()));
                k.setSatisFiyati(Integer.valueOf(tfsatis.getText()));
                k.setTarih(tarih);// tarih otomatk olarak atan�r ama isterlerse de�i�ip kendi istedikleri tarihleri girebilirler
                
                
                // Burada yeni de�i�iklikleri tabloya ekliyoruz.
                ekle.update(k);
				updateTable(ekle.getDefaultTable());
				
				
				
			}
		});
		
		sil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				UrunBilgileri k= new UrunBilgileri();
				k.setId(id);
				ekle.delete(k);
				updateTable(ekle.getDefaultTable());
				
				
			}
		});
		
		satildi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				UrunBilgileri k = new UrunBilgileri();
				k.setId(id);
				
				
				// Satis listesinin de�erlerini olu�turmak i�in "SatisListesiGetSet" i �a��rd�m. 
				SatisListesiGetSet n = new SatisListesiGetSet();
			
				JPanel p = new JPanel();
				p.setLayout(null);
               
				JTextField kacaAdedi=new JTextField();
				kacaAdedi.setBounds(15, 0, 200, 30);
				p.add(kacaAdedi);
				
				//Sat�s�n kac adet oldu�unu girmesi i�in, sat�� adedini gidi alan ekrana mesaj yollad�m.
				JOptionPane.showMessageDialog(null, p,"KA� TANE SATILDI",JOptionPane.PLAIN_MESSAGE);
				
				
				
				// veri taban�nda ki id si girilen k�s�m�n adedi �ekiliyor.
				int adet=ekle.getCell(k, "Adedi");
				
				// Mevcut adetten ka� tane sat�ld���n� ��kar�p �r�nlerin adet say�s�n� g�ncelliyor.
				k.setAdedi(adet-Integer.valueOf(kacaAdedi.getText()));
				ekle.update1(k);
				
				
				
				
				// Veri taban�nda ki mevcut de�erleri �ekmek i�in getCell methodunu kulland�m Ve adedi azalan �r�nleri �ekip
				// sat�� listesine ekledim
				String adi = ekle.getCell1(k, "UrunAdi");
				String kategori = ekle.getCellKategori(k, "Kategorisi");
				String hafiza = ekle.getCellHafiza(k, "Hafiza");
				String satisFiyati = ekle.getCellSatisFiayti(k, "SatisFiyati");
				
				
				// Burada sat��tan elde edilen toplam miktar� hesaplmak i�in (SAT�� TUTAR� * KAC ADET SAT�LD� ) ��LEM� YAPTIM
				// B�ylece sat��tan elde edilen toplam miktar� hesap yapmadan otomatik bir �ekilde hesaplanm�� bir �ekilde g�rece�iz.
				int toplamSatisTutar = Integer.valueOf(satisFiyati) * Integer.valueOf(kacaAdedi.getText());
				
				
				
				
				// Sat�lan �r�n�n baz� bilgilerini al�p sonrada "SatisEkrani" adl� database ye ekliyor.
				n.setUrunAdi(adi);
				n.setKategorisi(kategori);
				n.setHafiza(hafiza);
				n.setAdedi(Integer.valueOf(kacaAdedi.getText()));
				n.setSatisFiyati(String.valueOf(toplamSatisTutar) + " TL");
				n.setTarih(tarih);
				satisEkle.add(n);
				
				
				
				updateTable(ekle.getDefaultTable());
				
				
				
				
			}
		});
		
		
		
		
		
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				id = Integer.valueOf((String) tablemodel.getValueAt(table.rowAtPoint(e.getPoint()), 0));
				pm.show(table,e.getX(),e.getY());
			}
			
		});
		
		table.getTableHeader().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				String columnName = table.getColumnName(table.columnAtPoint(e.getPoint()));
				updateTable(ekle.getSortedTable(columnName));
			}
			
		});
		
		geri.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AnaSayfa cagir = new AnaSayfa();
				setVisible(false);
				
			}
		});
		
	
		
		
		
		
		// stok listesi ekran�na gitti�inde �r�n eklemek istedi�inde direk eklesin diye ekle �r�n ekle s�n�f�n� �a��ran bir buton olu�turdum 
		urunEkle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UrunEkle cagir = new UrunEkle();
				setVisible(false);
				
				
				
				
				
				
				
			}
		});
		
		
		getContentPane().add(arama);
		getContentPane().add(liste);
		getContentPane().add(urunEkle);
		getContentPane().add(geri);
		getContentPane().add(aramaAd�);
		getContentPane().add(background);
		
		
		setVisible(true);
		
	}
	
	private void updateTable(ArrayList<ArrayList<String>> list) {
		Object columnTitle[]=ekle.getColumnList(ekle.getUrunEklee()).toArray();
		Object rows[][]=ekle.getObjectArray(list);
		tablemodel.setDataVector(rows, columnTitle);
	}
	
	public static String sistemTarihiniGetir(String tarihFormati)
	{
	 Calendar takvim = Calendar.getInstance();
	 SimpleDateFormat sdf = new SimpleDateFormat(tarihFormati);
	 return sdf.format(takvim.getTime());
	}
}
