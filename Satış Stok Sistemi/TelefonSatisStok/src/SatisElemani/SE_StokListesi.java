package SatisElemani;

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

public class SE_StokListesi extends  JFrame{

	DefaultTableModel tablemodel;
	JTable table;
	UrunDAL ekle;
	SatisListesiDAL satisEkle;
	int id;
	public SE_StokListesi() {
		ekle = new UrunDAL("EkleUrun");
		satisEkle = new SatisListesiDAL("SatisEkrani");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		setTitle("STOK LÝSTESÝ");
		
		getContentPane().setLayout(null);

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
		
		tablemodel = new DefaultTableModel();
		table = new JTable();
		table.setModel(tablemodel);
		table.setEnabled(false);
		updateTable(ekle.getDefaultTable());
		table.setBackground(Color.orange);
		
		
		JScrollPane liste = new JScrollPane(table);
		liste.setBounds(10, 29, 665, 400);
		
		
		JPopupMenu pm = new JPopupMenu();
		JMenuItem guncelle = new JMenuItem("Kaydý Güncelle");
		JMenuItem sil = new JMenuItem("Kaydý Sil");
		JMenuItem satildi = new JMenuItem("Satýldý");
		pm.add(guncelle);
		pm.add(sil);
		pm.add(satildi);
		
		
		
		
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
				
				
				p.add(tfadd);
				p.add(tfkategori);
                p.add(tfadedi);
				p.add(tfhafiza);
				p.add(tfalis);
				p.add(tfsatis);
				
				JOptionPane.showMessageDialog(null, p,"VERÝLERÝ GÜNCELLE",JOptionPane.PLAIN_MESSAGE);
				
				k.setUrunAdi(tfadd.getText());
				k.setKategori(tfkategori.getText());;
				k.setAdedi(Integer.valueOf(tfadedi.getText()));
				k.setHafiza(tfhafiza.getText());
                k.setAlisFiyati(Integer.valueOf(tfalis.getText()));
                k.setSatisFiyati(Integer.valueOf(tfsatis.getText()));
                k.setTarih(tarih);
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
				SatisListesiGetSet n = new SatisListesiGetSet();
			
				
				JPanel p = new JPanel();
				p.setLayout(null);
                
			
				
				
				
				JTextField kacaAdedi=new JTextField();
				kacaAdedi.setBounds(15, 0, 200, 30);
				p.add(kacaAdedi);
				

				JOptionPane.showMessageDialog(null, p,"KAÇ TANE SATILDI",JOptionPane.PLAIN_MESSAGE);
				int adet=ekle.getCell(k, "Adedi");
				

				k.setAdedi(adet-Integer.valueOf(kacaAdedi.getText()));
				ekle.update1(k);
				
				
				
				
				// Veri tabanýnda ki mevcut deðerleri çekmek için getCell methodunu kullandým Ve adedi azalan ürünleri çekip
				// satýþ listesine ekledim
				String adi = ekle.getCell1(k, "UrunAdi");
				String kategori = ekle.getCellKategori(k, "Kategorisi");
				String hafiza = ekle.getCellHafiza(k, "Hafiza");
				String satisFiyati = ekle.getCellSatisFiayti(k, "SatisFiyati");
				
				
				// Burada satýþtan elde edilen toplam miktarý hesaplmak için (SATÝÞ TUTARÝ * KAC ADET SATÝLDÝ ) ÝÞLEMÝ YAPTIM
				int toplamSatisTutar = Integer.valueOf(satisFiyati) * Integer.valueOf(kacaAdedi.getText());
				
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
				SatisElAnaSayfa cagir = new SatisElAnaSayfa();
				setVisible(false);
				
			}
		});

		
		
		getContentPane().add(arama);
		getContentPane().add(liste);
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

