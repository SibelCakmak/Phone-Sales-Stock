package OturumBilgileri;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import YoneticiKýsmý.AnaSayfa;

public class OturumBilgileri extends JFrame{
	OturumBilgiDAL ekle;
	DefaultTableModel tablemodel;
	JTable table;

	public OturumBilgileri() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setResizable(false);
        getContentPane().setLayout(null);
        setTitle("TELEFON SATIS-STOK TAKÝBÝ");
        
       
        
        ekle = new OturumBilgiDAL("OturumVeri");
        
        Image imgEkle = new ImageIcon(OturumBilgileri.class.getResource("/resim7.jpg")).getImage();
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon(imgEkle));
        background.setBounds(0,0,700, 500);
        
        
        
        
        
        tablemodel = new DefaultTableModel();
		table = new JTable();
		table.setModel(tablemodel);
		table.setEnabled(false);
		updateTable(ekle.getDefaultTable());
		table.setBackground(Color.orange);
		
		
		JScrollPane liste = new JScrollPane(table);
		liste.setBounds(10, 29, 665, 400);
        
		JButton geri = new JButton("GERÝ");
        geri.setBounds(10,445,70,15);
        geri.setBackground(Color.orange);
        geri.setForeground(Color.white);
        
        
        geri.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AnaSayfa cagir = new AnaSayfa();
				setVisible(false);
				
			}
		});
        
        
        getContentPane().add(liste);
        getContentPane().add(background);
        getContentPane().add(geri);
        
        
        
        setVisible(true);
	}
	
	private void updateTable(ArrayList<ArrayList<String>> list) {
		Object columnTitle[]=ekle.getColumnList(ekle.getTableSifre()).toArray();
		Object rows[][]=ekle.getObjectArray(list);
		tablemodel.setDataVector(rows, columnTitle);
	}
}
