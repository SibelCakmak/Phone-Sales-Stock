package CalisanBilgileri;



import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ButtonGroup;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import YoneticiKýsmý.AnaSayfa;

import javax.swing.JComboBox;


public class CalisanListe extends JFrame{
	
	DefaultTableModel tablemodel;
	JTable table;
	KisiD liste;
	int id;
   

	public CalisanListe() {
		
		
		
		// Bu sayfada, maðzada çalýþanlarýn adlarýný, yaþlarýný, telno, pozisyon gibi bir çok bilgisini tutuyrum
		// Bu sayfa yönetici kýsmýnda girülüR.
		// Çalýþanlarýn bilgisini görür ve yeni çalýþan ekleyebilir.
		
		liste = new KisiD("CalisanListesi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		setTitle("ders11");
		setLocation(300, 100);
		getContentPane().setLayout(null);
		
		 Image imgEkle = new ImageIcon(CalisanListe.class.getResource("/resim7.jpg")).getImage();
	     JLabel background = new JLabel();
	     background.setIcon(new ImageIcon(imgEkle));
	     background.setBounds(0,0,700, 500);
		
		JLabel lad = new JLabel("AD :");
		lad.setBounds(100, 270, 50, 25);
        lad.setForeground(Color.white);
        JLabel lsoyad = new JLabel("SOYAD :");
		lsoyad.setBounds(100, 310, 50, 25);
		lsoyad.setForeground(Color.white);
		JLabel lyas = new JLabel("YAÞ :");
		lyas.setBounds(100, 350, 50, 25);
		lyas.setForeground(Color.white);
		
		JLabel lcinsiyet = new JLabel("CÝNSÝYET :");
		lcinsiyet.setBounds(100, 390, 80, 25);
		lcinsiyet.setForeground(Color.white);
		JLabel lpozisyon = new JLabel("POZÝSYON :");
		lpozisyon.setBounds(290, 270, 80, 25);
		lpozisyon.setForeground(Color.white);       
        JLabel lnumara = new JLabel("TEL NO :");
		lnumara.setBounds(290, 310, 80, 25);
		lnumara.setForeground(Color.white);
		JLabel lmaas = new JLabel("MAAS:");
		lmaas.setBounds(290, 350, 80, 25);
		lmaas.setForeground(Color.white);

		JTextField tfad = new JTextField();
		tfad.setBounds(180, 270, 85, 25);
                
        JTextField tfsoyad = new JTextField();
		tfsoyad.setBounds(180, 310, 85, 25);
                
		
		JTextField tfyas = new JTextField();
		tfyas.setBounds(180, 350, 85, 25);
		
		
		JTextField tfpozisyon = new JTextField();
		tfpozisyon.setBounds(380, 270, 90, 25);
                
		
		JTextField tfnumara = new JTextField();
		tfnumara.setBounds(380, 310, 90, 25);
		
		JTextField tfmaas = new JTextField();
		tfmaas.setBounds(380, 350, 90, 25);

		
		JTextField tfCinsiyet = new JTextField();
		
		
		
		
       JRadioButton kadin = new JRadioButton("Kadin"); 
       kadin.setBounds(180, 386, 60, 30);
                
       JRadioButton erkek = new JRadioButton("erkek");
        erkek.setBounds(240,386,60,30);
                 
        ButtonGroup genders=new ButtonGroup();
       genders.add(kadin);
         genders.add(erkek);
         
        erkek.setBackground(Color.orange);
        kadin.setBackground(Color.orange);
                
		
		JTextField tfsearch = new JTextField();
		tfsearch.setBounds(280, 10, 150, 20);
		
       JLabel arama = new JLabel("  LÝSTEDE ARA ?");
       arama.setBounds(170,10,110,20);
                

		
		JButton add = new JButton("KAYIT ET");
		add.setBounds(250, 430, 100, 30);
		add.setBackground(Color.orange);
		
		JButton geri = new JButton("Geri");
		geri.setBounds(10, 440, 80, 15);
		geri.setBackground(Color.orange);
		geri.setForeground(Color.white);
		
		
		
		
		geri.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AnaSayfa anasayfa = new AnaSayfa();
				setVisible(false);
				
				
			}
		});
          
		
		tablemodel=new DefaultTableModel();
		table=new JTable();
		table.setModel(tablemodel);
		table.setEnabled(false);
		updateTable(liste.getDefaultTable());
		table.setBackground(Color.orange);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 50, 650, 200);
                
                 kadin.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                       tfCinsiyet.setText("Kadýn");
                    }
                } );
               
                 erkek.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                       tfCinsiyet.setText("Erkek");
                    }
                } );
                
                
                
		
		table.getTableHeader().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				String columnName = table.getColumnName(table.columnAtPoint(e.getPoint()));
				updateTable(liste.getSortedTable(columnName));
			}
			
		});
		
		tfsearch.getDocument().addDocumentListener(new DocumentListener() {
			
			public void update() {
				updateTable(liste.getSearchedTable(tfsearch.getText()));
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
		
		
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Calisan k = new Calisan();
				k.setAd(tfad.getText());
                k.setSoyad(tfsoyad.getText());
				k.setYas(Integer.valueOf(tfyas.getText()));
				k.setCinsiyet(tfCinsiyet.getText());
				k.setPozisyon(tfpozisyon.getText());
				k.setNumara(tfnumara.getText());
				k.setMaas(Integer.valueOf(tfmaas.getText()));
                               
				liste.add(k);
				updateTable(liste.getDefaultTable());
							
                        }
		});
		
		JPopupMenu pm = new JPopupMenu();
		JMenuItem item1 = new JMenuItem("Kaydý Güncelle");
		JMenuItem item2 = new JMenuItem("Kaydý Sil");
		pm.add(item1);
		pm.add(item2);
		
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				id = Integer.valueOf((String) tablemodel.getValueAt(table.rowAtPoint(e.getPoint()), 0));
				pm.show(table,e.getX(),e.getY());
			}
			
		});
		
		item1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Calisan k = new Calisan();
				k.setId(id);
				
                JPanel p = new JPanel();
                p.setSize(150,150);
                                
				JTextField tfadd=new JTextField("ad");
				tfadd.setBounds(0, 0, 40, 30);
                JTextField tfsoyad=new JTextField("Soyad");
				tfadd.setBounds(40, 0, 40, 30);
				JTextField tfyass=new JTextField("yas");
				tfyass.setBounds(80, 0, 40, 30);
				JTextField tfcinsiyett=new JTextField("cinsiyet");
				tfcinsiyett.setBounds(120, 0, 40, 30);
				JTextField tfpozisyonn=new JTextField("pozsiyon");
				tfpozisyonn.setBounds(160, 0, 40, 30);
				JTextField tfnumaraa=new JTextField("tel no");
				tfnumaraa.setBounds(200, 0, 40, 30);
				JTextField tfmaas=new JTextField("mass");
				tfnumaraa.setBounds(240, 0, 40, 30);
				
				
				
				
				p.add(tfadd);
                p.add(tfsoyad);
				p.add(tfyass);
				p.add(tfcinsiyett);
				p.add(tfpozisyonn);
				p.add(tfnumaraa);
				p.add(tfmaas);
                                
				
				JOptionPane.showMessageDialog(null, p,"VERÝLERÝ GÜNCELLE",JOptionPane.PLAIN_MESSAGE);
				k.setYas(Integer.valueOf(tfyass.getText()));
				k.setAd(tfadd.getText());
				k.setSoyad(tfsoyad.getText());;
				k.setCinsiyet(tfcinsiyett.getText());
                k.setPozisyon(tfpozisyonn.getText());
                k.setNumara(tfnumaraa.getText());
                k.setMaas(Integer.valueOf(tfmaas.getText()));
                liste.update(k);
				updateTable(liste.getDefaultTable());
				
				
			}
		});
		
		item2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Calisan k= new Calisan();
				k.setId(id);
				liste.delete(k);
				updateTable(liste.getDefaultTable());
			}
		});
		
		
	
		
		
		getContentPane().add(kadin);
		getContentPane().add(pm);
		getContentPane().add(sp);
		getContentPane().add(lad);
		getContentPane().add(lyas);
		getContentPane().add(tfad);
		getContentPane().add(tfyas);
		getContentPane().add(erkek);
		getContentPane().add(tfsearch);
		getContentPane().add(add);
        getContentPane().add(arama);
        getContentPane().add(lsoyad);
        getContentPane().add(tfsoyad);
        getContentPane().add(lnumara);
        getContentPane().add(lpozisyon);
        getContentPane().add(tfnumara);
        getContentPane().add(tfpozisyon);
        getContentPane().add(lcinsiyet);
        getContentPane().add(geri);
        getContentPane().add(tfmaas);
		getContentPane().add(lmaas);
		getContentPane().add(background);
		
		setVisible(true);
	}

	private void updateTable(ArrayList<ArrayList<String>> list) {
		Object columnTitle[]=liste.getColumnList(liste.getTableName()).toArray();
		Object rows[][]=liste.getObjectArray(list);
		tablemodel.setDataVector(rows, columnTitle);
	}
}
