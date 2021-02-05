package StokSorumlusu;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import IadeArsivi.IadeDAL;
import IadeArsivi.IadeEdilenUrunlerGetSet;
import SatisArsivi.SatisListesiDAL;
import SatisArsivi.SatisListesiGetSet;
import YoneticiKýsmý.AnaSayfa;

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

public class SO_SatisListesi extends JFrame{

	
	DefaultTableModel tablemodel;
	JTable table;
	SatisListesiDAL satisEkle;
	IadeDAL iadeUrunlerListe;
	int id;
	public SO_SatisListesi() {
		
		satisEkle = new SatisListesiDAL("SatisEkrani");
		iadeUrunlerListe = new IadeDAL("IadeEdilenUrunler");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		setTitle("STOK LÝSTESÝ");
		getContentPane().setLayout(null);
		
		Image imgEkle = new ImageIcon(SO_SatisListesi.class.getResource("/resim7.jpg")).getImage();
	     JLabel background = new JLabel();
	     background.setIcon(new ImageIcon(imgEkle));
	     background.setBounds(0,0,700, 500);
		

		
		
		String tarih = sistemTarihiniGetir("yyyy/MM/dd H:mm");
		
		
		JButton geri = new JButton("GERÝ");
		geri.setBounds(10,440,80,15);
		geri.setBackground(Color.orange);
		
		
		
		JLabel aramaAdý = new JLabel();
		aramaAdý.setText("ARAMA  ");
		aramaAdý.setBounds(250,5,80,20);
		
		JTextField arama = new JTextField();
		arama.setBounds(310,5,130,20);
		
		JPopupMenu pm = new JPopupMenu();
		JMenuItem iade = new JMenuItem("Ýade Edildi");
		pm.add(iade);
		
		tablemodel = new DefaultTableModel();
		table = new JTable();
		table.setModel(tablemodel);
		table.setEnabled(false);
		updateTable(satisEkle.getDefaultTable());
		table.setBackground(Color.orange);
		
		JScrollPane liste = new JScrollPane(table);
		liste.setBounds(10, 29, 665, 400);
		
	
		iade.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SatisListesiGetSet k= new SatisListesiGetSet();
				IadeEdilenUrunlerGetSet m = new IadeEdilenUrunlerGetSet();
				m.setId(id);
				k.setId(id);
				
				JPanel p = new JPanel();
				p.setLayout(null);
	
				JTextField iadeSebebi =new JTextField();
				iadeSebebi.setBounds(15, 0, 200, 30);
				p.add(iadeSebebi);
       
				JOptionPane.showMessageDialog(null, p,"ÝADE NEDENÝNÝ GÝRÝNÝZ",JOptionPane.PLAIN_MESSAGE);
			
				
				
				String adi = satisEkle.getCellAdi(k, "UrunAdi");
				String kategori = satisEkle.getCellKategori(k, "Kategorisi");
				String hafiza = satisEkle.getCellHafiza(k, "Hafiza");
				
				
				
				m.setUrunAdi(adi);
				m.setKategorisi(kategori);
				m.setHafiza(hafiza);
			    m.setIadeSebebi(iadeSebebi.getText());
				m.setTarih(tarih);
				
				
				
				
				
				
				
				iadeUrunlerListe.add(m);
				
				satisEkle.delete(k);
				updateTable(satisEkle.getDefaultTable());
				

				
			}
		});
		
	

		table.getTableHeader().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				String columnName = table.getColumnName(table.columnAtPoint(e.getPoint()));
				updateTable(satisEkle.getSortedTable(columnName));
			}
			
		});
		
		
		
		arama.getDocument().addDocumentListener(new DocumentListener() {
			
			public void update() {
				updateTable(satisEkle.getSearchedTable(arama.getText()));
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
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				id = Integer.valueOf((String) tablemodel.getValueAt(table.rowAtPoint(e.getPoint()), 0));
				pm.show(table,e.getX(),e.getY());
			}
			
		}); 
		
		geri.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SO_AnaSayfa cagir = new SO_AnaSayfa();
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
		Object columnTitle[]=satisEkle.getColumnList(satisEkle.getSatiEkle()).toArray();
		Object rows[][]=satisEkle.getObjectArray(list);
		tablemodel.setDataVector(rows, columnTitle);
	}
	
	public static String sistemTarihiniGetir(String tarihFormati)
	{
	 Calendar takvim = Calendar.getInstance();
	 SimpleDateFormat sdf = new SimpleDateFormat(tarihFormati);
	 return sdf.format(takvim.getTime());
	}
}