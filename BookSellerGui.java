/*****************************************************************
JADE - Java Agent DEvelopment Framework is a framework to develop 
multi-agent systems in compliance with the FIPA specifications.
Copyright (C) 2000 CSELT S.p.A. 

GNU Lesser General Public License

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation, 
version 2.1 of the License. 

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the
Free Software Foundation, Inc., 59 Temple Place - Suite 330,
Boston, MA  02111-1307, USA.
*****************************************************************/



import jade.core.AID;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
  @author Giovanni Caire - TILAB
 */
@SuppressWarnings({ "serial", "unused" })
class BookSellerGui extends JFrame {	
	private BookSellerAgent myAgent;
	
	private JTextField titleField, priceField, sizeField, typeField;
	
	BookSellerGui(BookSellerAgent a) {
		super(a.getLocalName());
		
		myAgent = a;
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4, 2));
		p.add(new JLabel("Memory brand:"));
		
		String[] brandNames = new String[] {"Kingston", "ADATA", "Samsung", "Sony", "GOODRAM"};
		String[] memorySizes = new String[] {"2 GB", "4 GB", "8 GB", "16 GB"};
		String[] memoryType = new String[] {"DDR4", "DDR3", "DDR2"};
		
		JComboBox<String> brandListBox = new JComboBox<>(brandNames);
		//add(brandListBox);
		p.add(brandListBox);
		String selectedBrand = (String) brandListBox.getSelectedItem();

		//titleField = new JTextField(15);
		//p.add(titleField);
		p.add(new JLabel("Memory Size:"));
		//sizeField = new JTextField(15);
		//p.add(sizeField);
		
		JComboBox<String> memorySizeBox = new JComboBox<>(memorySizes);
		p.add(memorySizeBox);
		String selectedMemory = (String) memorySizeBox.getSelectedItem();
		
		
		
		p.add(new JLabel("Memory Type:"));
		//typeField = new JTextField(15);
		//p.add(typeField);
		
		JComboBox<String> memoryTypeBox = new JComboBox<>(memoryType);
		p.add(memoryTypeBox);
		String selectedType= (String) memoryTypeBox.getSelectedItem();
		
		
		p.add(new JLabel("Price:"));
		priceField = new JTextField(15);
		p.add(priceField);

		getContentPane().add(p, BorderLayout.CENTER);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					//String title = titleField.getText().trim();
					String title = (String) brandListBox.getSelectedItem().toString().trim();
					String price = priceField.getText().trim();
					//String size = sizeField.getText().trim();
					String size = (String) memorySizeBox.getSelectedItem().toString().trim();
					//String type = typeField.getText().trim();
					String type = (String) memoryTypeBox.getSelectedItem().toString().trim();
					myAgent.updateCatalogue(title,size,type, Integer.parseInt(price));
					//titleField.setText("");
					priceField.setText("");
					//sizeField.setText("");
					//typeField.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(BookSellerGui.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		p = new JPanel();
		p.add(addButton);
		getContentPane().add(p, BorderLayout.SOUTH);
		
		// Make the agent terminate when the user closes 
		// the GUI using the button on the upper right corner	
		addWindowListener(new	WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				myAgent.doDelete();
			}
		} );
		
		setResizable(false);
	}
	
	public void showGui() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		super.setVisible(true);
	}	
}
