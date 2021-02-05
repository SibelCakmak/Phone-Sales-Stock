package IadeArsivi;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import YoneticiK�sm�.AnaSayfa;

public class IadeListesi extends JFrame{
	
	DefaultTableModel tablemodel;
	JTable table;
	IadeDAL iadeUrunlerListe;
	int id;
	
	
	public IadeListesi() {
		
		// Sat�lan �r�nlerin geri verilmesi ola�an bir durumdur. Bu y�zden iade s�n�f� bulunmaktad�r.
		// Buras� sat�lanlar listesi ile ba�lant�l�r. ��nk� satlan listesinden iade et k�sm�na t�kland���nda o �r�n, iade sebei ile buraya aktar�l�r.
		iadeUrunlerListe = new IadeDAL("IadeEdilenUrunler");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		setTitle("STOK L�STES�");
		
		getContentPane().setLayout(null);

		Image imgEkle = new ImageIcon(IadeListesi.class.getResource("/resim7.jpg")).getImage();
	     JLabel background = new JLabel();
	     background.setIcon(new ImageIcon(imgEkle));
	     background.setBounds(0,0,700, 500);

		
		JButton geri = new JButton("GER�");
		geri.setBounds(10,440,80,17);
		geri.setForeground(Color.WHITE);
		geri.setBackground(Color.orange);
		
		
		
		JLabel aramaAd� = new JLabel();
		aramaAd�.setText("ARAMA  ");
		aramaAd�.setBounds(250,5,80,20);
		
		JTextField arama = new JTextField();
		arama.setBounds(310,5,130,20);
		
		
		
		
		// �adeedilen �r�nler "IadeEdilenUrunler" adl� veri tan�nda tutuluyor. Burada onlar� �ekip ekranda listeliyoruz.

		tablemodel = new DefaultTableModel();
		table = new JTable();
		table.setModel(tablemodel);
		table.setEnabled(false);
		updateTable(iadeUrunlerListe.getDefaultTable());
		table.setBackground(Color.orange);
		
		JScrollPane liste = new JScrollPane(table);
		liste.setBounds(10, 29, 665, 400);
		
		geri.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {	
				AnaSayfa cagir = new AnaSayfa();
				setVisible(false);
			}
		});
		
		
		
		
		
		getContentPane().add(arama);
		getContentPane().add(liste);
		getContentPane().add(geri);
		getContentPane().add(aramaAd�);
		getContentPane().add(background);
		
		setVisible(true);
		
	}
	
	private void updateTable(ArrayList<ArrayList<String>> list) {
		Object columnTitle[]=iadeUrunlerListe.getColumnList(iadeUrunlerListe.getIade()).toArray();
		Object rows[][]=iadeUrunlerListe.getObjectArray(list);
		tablemodel.setDataVector(rows, columnTitle);
	}
	

}

