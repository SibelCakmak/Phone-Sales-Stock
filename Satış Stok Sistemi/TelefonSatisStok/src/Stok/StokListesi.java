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
import YoneticiKýsmý.AnaSayfa;

public class StokListesi extends  JFrame{

	DefaultTableModel tablemodel;
	JTable table;
	UrunDAL ekle;
	SatisListesiDAL satisEkle;
	int id;
	public StokListesi() {
		
		
		// Burada yeni bir veri tabaný yaratmaktan ziyade var olan bir veri tabýný çekiyoruz bu yüzden tablonun adýný vra olan tablonun aynýsý
		//olarak yaýyoruz. Yanlýþ yazarsak eðer yeni bir database oluþur. Ve çekmek istediðimiz verileri çekip ekrana yazamayýz. 
		ekle = new UrunDAL("EkleUrun");
		
		
		
		// Satýþ veilerin tutmak için burada bir database oluþturuyoruz. Ve her satýlan ürün burada depolanýp satýþ listesine aktarýlacak.
		satisEkle = new SatisListesiDAL("SatisEkrani");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		setTitle("STOK LÝSTESÝ");
		getContentPane().setLayout(null);

		
		
		// arka plan eklemek için ImageIcon u kullandým. Bunun için "img" adýnda bir dosya oluþturup fotoðraflarý bunun için atýp çektim
		 Image imgEkle = new ImageIcon(UrunEkle.class.getResource("/resim7.jpg")).getImage();
	     JLabel background = new JLabel();
	     background.setIcon(new ImageIcon(imgEkle));
	      background.setBounds(0,0,700, 500);
		
		
		JButton geri = new JButton("GERÝ");
		geri.setBounds(10,430,80,20);
		geri.setBackground(Color.orange);
		
		JButton urunEkle = new JButton("EKLE");
		urunEkle.setBounds(596,430,80,20);
		urunEkle.setBackground(Color.orange);
		
		JLabel aramaAdý = new JLabel();
		aramaAdý.setText("ARAMA  ");
		aramaAdý.setBounds(250,5,80,20);
		
		JTextField arama = new JTextField();
		arama.setBounds(310,5,130,20);
		
		
		
		//  Tablo þekilde verilerin yazýlmasý için DefaultTableModel deyiminden yararlanýyoruz.
		tablemodel = new DefaultTableModel();
		table = new JTable();
		table.setModel(tablemodel);
		table.setEnabled(false);
		updateTable(ekle.getDefaultTable());
		table.setBackground(Color.orange);
		
		
		// Tablonun ekranda gözükmesi ve o alaný belirlemek için bir tane "JScrollPane" tanýmladýk
		JScrollPane liste = new JScrollPane(table);
		liste.setBounds(10, 29, 665, 400);
		
		
		JPopupMenu pm = new JPopupMenu();
		JMenuItem guncelle = new JMenuItem("Kaydý Güncelle");
		JMenuItem sil = new JMenuItem("Kaydý Sil");
		JMenuItem satildi = new JMenuItem("Satýldý");
		pm.add(guncelle);
		pm.add(sil);
		pm.add(satildi);
		
		
		
		// güncel tarihi çektim burada. Tarih giriþleri daha doðru olsun diye
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
				
				// JTextField leri panele atýyoruz burada
				p.add(tfadd);
				p.add(tfkategori);
                p.add(tfadedi);
				p.add(tfhafiza);
				p.add(tfalis);
				p.add(tfsatis);
				
				JOptionPane.showMessageDialog(null, p,"VERÝLERÝ GÜNCELLE",JOptionPane.PLAIN_MESSAGE);
				// Burada veriler güncelleniyor. Tarih ise otomatik olarak atanýyor.
				k.setUrunAdi(tfadd.getText());
				k.setKategori(tfkategori.getText());;
				k.setAdedi(Integer.valueOf(tfadedi.getText()));
				k.setHafiza(tfhafiza.getText());
                k.setAlisFiyati(Integer.valueOf(tfalis.getText()));
                k.setSatisFiyati(Integer.valueOf(tfsatis.getText()));
                k.setTarih(tarih);// tarih otomatk olarak atanýr ama isterlerse deðiþip kendi istedikleri tarihleri girebilirler
                
                
                // Burada yeni deðiþiklikleri tabloya ekliyoruz.
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
				
				
				// Satis listesinin deðerlerini oluþturmak için "SatisListesiGetSet" i çaðýrdým. 
				SatisListesiGetSet n = new SatisListesiGetSet();
			
				JPanel p = new JPanel();
				p.setLayout(null);
               
				JTextField kacaAdedi=new JTextField();
				kacaAdedi.setBounds(15, 0, 200, 30);
				p.add(kacaAdedi);
				
				//Satýsýn kac adet olduðunu girmesi için, satýþ adedini gidi alan ekrana mesaj yolladým.
				JOptionPane.showMessageDialog(null, p,"KAÇ TANE SATILDI",JOptionPane.PLAIN_MESSAGE);
				
				
				
				// veri tabanýnda ki id si girilen kýsýmýn adedi çekiliyor.
				int adet=ekle.getCell(k, "Adedi");
				
				// Mevcut adetten kaç tane satýldýðýný çýkarýp ürünlerin adet sayýsýný güncelliyor.
				k.setAdedi(adet-Integer.valueOf(kacaAdedi.getText()));
				ekle.update1(k);
				
				
				
				
				// Veri tabanýnda ki mevcut deðerleri çekmek için getCell methodunu kullandým Ve adedi azalan ürünleri çekip
				// satýþ listesine ekledim
				String adi = ekle.getCell1(k, "UrunAdi");
				String kategori = ekle.getCellKategori(k, "Kategorisi");
				String hafiza = ekle.getCellHafiza(k, "Hafiza");
				String satisFiyati = ekle.getCellSatisFiayti(k, "SatisFiyati");
				
				
				// Burada satýþtan elde edilen toplam miktarý hesaplmak için (SATÝÞ TUTARÝ * KAC ADET SATÝLDÝ ) ÝÞLEMÝ YAPTIM
				// Böylece satýþtan elde edilen toplam miktarý hesap yapmadan otomatik bir þekilde hesaplanmýþ bir þekilde göreceðiz.
				int toplamSatisTutar = Integer.valueOf(satisFiyati) * Integer.valueOf(kacaAdedi.getText());
				
				
				
				
				// Satýlan ürünün bazý bilgilerini alýp sonrada "SatisEkrani" adlý database ye ekliyor.
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
		
	
		
		
		
		
		// stok listesi ekranýna gittiðinde ürün eklemek istediðinde direk eklesin diye ekle ürün ekle sýnýfýný çaðýran bir buton oluþturdum 
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
		getContentPane().add(aramaAdý);
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
