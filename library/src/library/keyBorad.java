package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class keyBorad extends JFrame{
	private JPanel p1 = new JPanel();
	public JTextField input = new JTextField(15);
	private JButton[] btn_key = new JButton[38];
	private String[] key = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"," q", "w", "e", "r", "t", "y", "u",
			"i", "o", "p", "a", "s", "d", "f","g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m", "shift_A"};
	private String[] shift_key = {"!", "@", "#", "$", "%",	"^", "&", "*", "(", ")"," Q", "W", "E", "R",
			"T", "Y", "U", "I", "O", "P", "A", "S", "D", "F","G", "H", "J", "K", "L", "Z", "X", "C", "V",
			"B", "N", "M", "shift_a"};
	private JButton btn_input = new JButton("입력");
	
	
	public String getInput() { return input.getText(); }
	
	public keyBorad() {
		setTitle("키보드");
		Container con = getContentPane(); 
		con.setLayout(new FlowLayout());
		
		GridLayout grid = new GridLayout(6,7);
		grid.setVgap(2);
		p1.setLayout(grid);
		
		for(int i=0; i<key.length; ++i) {
			btn_key[i] = new JButton(key[i]);
			btn_key[i].addActionListener(new inputkey_ActionListener());
			p1.add(btn_key[i]);
		}
		
		btn_input.addActionListener(new input_ActionListener());
		p1.add(btn_input);
		
		con.add(input);
		con.add(p1);
		 
		setSize(500, 300); 
		setVisible(true); 
	}
	
	class input_ActionListener implements ActionListener {		
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("입력"))	{
				dispose();
			}
		}
	}
	
	class inputkey_ActionListener implements ActionListener {		
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("1"))	{
				input.setText("1");
			}
			else if (b.getText().equals("2"))	{
				input.setText("2");
			}
			else if (b.getText().equals("3"))	{
				input.setText("3");
			}
			else if (b.getText().equals("4"))	{
				input.setText("4");
			}
			else if (b.getText().equals("5"))	{
				input.setText("5");
			}
			else if (b.getText().equals("6"))	{
				input.setText("6");
			}
			else if (b.getText().equals("7"))	{
				input.setText("7");
			}
			else if (b.getText().equals("8"))	{
				input.setText("8");
			}
			else if (b.getText().equals("9"))	{
				input.setText("9");
			}
			else if (b.getText().equals("0"))	{
				input.setText("0");
			}
			else if (b.getText().equals("q"))	{
				input.setText("q");
			}
			else if (b.getText().equals("w"))	{
				input.setText("w");
			}
			else if (b.getText().equals("e"))	{
				input.setText("e");
			}
			else if (b.getText().equals("r"))	{
				input.setText("r");
			}
			else if (b.getText().equals("t"))	{
				input.setText("t");
			}
			else if (b.getText().equals("y"))	{
				input.setText("y");
			}
			else if (b.getText().equals("u"))	{
				input.setText("u");
			}
			else if (b.getText().equals("i"))	{
				input.setText("i");
			}
			else if (b.getText().equals("o"))	{
				input.setText("o");
			}
			else if (b.getText().equals("p"))	{
				input.setText("p");
			}
			else if (b.getText().equals("a"))	{
				input.setText("a");
			}
			else if (b.getText().equals("s"))	{
				input.setText("s");
			}
			else if (b.getText().equals("d"))	{
				input.setText("d");
			}
			else if (b.getText().equals("f"))	{
				input.setText("f");
			}
			else if (b.getText().equals("g"))	{
				input.setText("g");
			}
			else if (b.getText().equals("h"))	{
				input.setText("h");
			}
			else if (b.getText().equals("j"))	{
				input.setText("j");
			}
			else if (b.getText().equals("k"))	{
				input.setText("k");
			}
			else if (b.getText().equals("l"))	{
				input.setText("l");
			}
			else if (b.getText().equals("z"))	{
				input.setText("z");
			}
			else if (b.getText().equals("x"))	{
				input.setText("x");
			}
			else if (b.getText().equals("c"))	{
				input.setText("c");
			}
			else if (b.getText().equals("v"))	{
				input.setText("v");
			}
			else if (b.getText().equals("b"))	{
				input.setText("b");
			}
			else if (b.getText().equals("n"))	{
				input.setText("n");
			}
			else if (b.getText().equals("m"))	{
				input.setText("m");
			}
			
			// shift_A
			else if (b.getText().equals("!"))	{
				input.setText("!");
			}
			else if (b.getText().equals("@"))	{
				input.setText("@");
			}
			else if (b.getText().equals("#"))	{
				input.setText("#");
			}
			else if (b.getText().equals("$"))	{
				input.setText("$");
			}
			else if (b.getText().equals("%"))	{
				input.setText("%");
			}
			else if (b.getText().equals("^"))	{
				input.setText("^");
			}
			else if (b.getText().equals("&"))	{
				input.setText("&");
			}
			else if (b.getText().equals("*"))	{
				input.setText("*");
			}
			else if (b.getText().equals("("))	{
				input.setText("(");
			}
			else if (b.getText().equals(")"))	{
				input.setText(")");
			}
			else if (b.getText().equals("Q"))	{
				input.setText("Q");
			}
			else if (b.getText().equals("W"))	{
				input.setText("W");
			}
			else if (b.getText().equals("E"))	{
				input.setText("E");
			}
			else if (b.getText().equals("R"))	{
				input.setText("R");
			}
			else if (b.getText().equals("T"))	{
				input.setText("T");
			}
			else if (b.getText().equals("Y"))	{
				input.setText("Y");
			}
			else if (b.getText().equals("U"))	{
				input.setText("U");
			}
			else if (b.getText().equals("I"))	{
				input.setText("I");
			}
			else if (b.getText().equals("O"))	{
				input.setText("O");
			}
			else if (b.getText().equals("P"))	{
				input.setText("P");
			}
			else if (b.getText().equals("A"))	{
				input.setText("A");
			}
			else if (b.getText().equals("S"))	{
				input.setText("S");
			}
			else if (b.getText().equals("D"))	{
				input.setText("D");
			}
			else if (b.getText().equals("F"))	{
				input.setText("F");
			}
			else if (b.getText().equals("G"))	{
				input.setText("G");
			}
			else if (b.getText().equals("H"))	{
				input.setText("H");
			}
			else if (b.getText().equals("J"))	{
				input.setText("J");
			}
			else if (b.getText().equals("K"))	{
				input.setText("K");
			}
			else if (b.getText().equals("L"))	{
				input.setText("L");
			}
			else if (b.getText().equals("Z"))	{
				input.setText("Z");
			}
			else if (b.getText().equals("X"))	{
				input.setText("X");
			}
			else if (b.getText().equals("C"))	{
				input.setText("C");
			}
			else if (b.getText().equals("V"))	{
				input.setText("V");
			}
			else if (b.getText().equals("B"))	{
				input.setText("B");
			}
			else if (b.getText().equals("N"))	{
				input.setText("N");
			}
			else if (b.getText().equals("M"))	{
				input.setText("M");
			}
			else if  (b.getText().equals("shift_A"))	{
				for(int i=0; i<btn_key.length; ++i) {
					btn_key[i].setText(shift_key[i]);
				}
			}
			else if (b.getText().equals("shift_a"))	{
				for(int i=0; i<btn_key.length; ++i) {
					btn_key[i].setText(key[i]);
				}
			}
		}
	}
}
