package cleanedUpGames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class InGameMenu extends JFrame {
	@SuppressWarnings("rawtypes")
	JList list = new JList();
	JPanel pnl = new JPanel();
	JFrame frame = new JFrame();
	JButton add = new JButton("Add");
	JButton close = new JButton("Close");
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public InGameMenu(int x, int y, int a, int b) {
        String path="C:/Users/ktp/workspace/KPT/src/Images/Pieces/";
        File folder=new File(path);
        String[]listOfFiles=folder.list();
        list=new JList(listOfFiles);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        JScrollPane scrollPane = new JScrollPane(pnl);
        
        pnl.add(list);
		pnl.add(add);
		frame.setSize(250,600);
		frame.setLocation(a, b);
		frame.setVisible(true);
		frame.getContentPane().add(scrollPane);
		frame.add(scrollPane);
		pack();
        add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object gamefile = list.getSelectedValue();
				try {
					Game.addDoodad(path + (String) gamefile, x, y);
					frame.dispose();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
			}
		});
	}
	}