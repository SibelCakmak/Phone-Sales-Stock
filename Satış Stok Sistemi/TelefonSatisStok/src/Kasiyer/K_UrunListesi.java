package Kasiyer;

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

public class K_UrunListesi extends  JFrame{

	DefaultTableModel tablemodel;
	JTable table;
	UrunDAL ekle;
	SatisListesiDAL satisEkle;
	int id;
	public K_UrunListesi() {
		
		// Kasiyerin tek ulaþacaðý kýsým ürün listesidir. O yüzden ona ayrý bir sayfa tasarlayýp sadece ürün listesini görme özelliðini ekledim
		
		ekle = new UrunDAL("EkleUrun");
		satisEkle = new SatisListesiDAL("SatisEkrani");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		setTitle("STOK LÝSTESÝ");
		
		getContentPane().setLayout(null);

		 Image imgEkle = new ImageIcon(K_UrunListesi.class.getResource("/resim7.jpg")).getImage();
	     JLabel background = new JLabel();
	     background.setIcon(new ImageIcon(imgEkle));
	      background.setBounds(0,0,700, 500);
		
		
		JButton geri = new JButton("GERÝ");
		geri.setBounds(10,430,80,20);
		geri.setBackground(Color.orange);

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
				K_AnaSayfa cagir = new K_AnaSayfa();
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
