package ru.calc.banners; //�������� ������

import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.AttributeSet;				//��������� ���������� ��������d
import javax.swing.text.BadLocationException;		//����������
import javax.swing.text.Document; 					//��������
import javax.swing.text.PlainDocument;              //������� ��������, ������� �� �������� ��������� 
import java.util.regex.Matcher; 					//Matcher � ����� ��������� ������������ � �� � ������ ���������� ����� ������������
import java.util.regex.Pattern; 					//Pattern ������������ ����� ���������������� ������������� ��

public class BannerCalculator extends JFrame {
	private JButton button = new JButton("������ ���������");
	private JTextField input1 = new CustomJTextField(3);  //����� CustomJTextField
	private JTextField input2 = new CustomJTextField(3);
	private JTextField input3 = new CustomJTextField(3);
	private JLabel label1 = new JLabel("������, �");
	private JLabel label2 = new JLabel("������, �");
	private JLabel label3 = new JLabel("���-��, ��.");
	private JLabel label4 = new JLabel("��������");
	private JRadioButton radio1 = new JRadioButton("����������� �������� ������");
	private JRadioButton radio2 = new JRadioButton("���������� �������� ������");
	private JCheckBox check = new JCheckBox("������� �����", false);
	private String[] items = {"ظ��", "�������� �����", "������"}; 
	private JComboBox editComboBox = new JComboBox(items);     

	public BannerCalculator () { 					  //����������� ��������� ����
		super("����������� ������� ��������� ������ ��������"); 
		this.setBounds(500, 100, 550, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    Container container = this.getContentPane();  	  //c����� ��������� ��� ��������
    container.setLayout(new GridLayout(7,3,2,2));
	    container.add(label1);
	    container.add(input1);
	    container.add(label2);
	    container.add(input2);
	    container.add(label3);
	    container.add(input3);
	    container.add(label4);
	    container.add(editComboBox);    
		ButtonGroup group = new ButtonGroup();        //������ RadioButtons
		    group.add(radio1);
		    group.add(radio2);
		    container.add(radio1);
		    radio1.setSelected(true);
		    container.add(radio2);
		container.add(check);
		button.addActionListener(new ButtonEventListener());
		container.add(button);
	}   
	/* ����� add ��������� ���������: (������*������)*���-��*�������(20) */
	public double add(double a, double b, double c) {
		return ((a*b)*c)*20;
	}
	/* ����� mult ��������� ��������� ����������: ���������+(���������*��������� ���������) */
	public double mult(double a, double b) {
		return a+(a*b);
	}
	/* ����� checkForEmptiness ��������� �� ������ �� ��������� ���� */
	public boolean checkForEmptiness(String check1,String check2,String check3) {
		 if (check1.trim().length() == 0 || check2.trim().length() == 0 || check3.trim().length() == 0) {
			    return true;  
			    } else return false;
	}
	/*��������� � ������� ���������� �������� ���������� � �� �������� ����������� ����� ����*/
	private	class ButtonEventListener implements ActionListener {       //����������
		public void actionPerformed(ActionEvent e) {                    //����� � ����������
			int wid, heig, quantity;
			double itog, mainitog;
			String message = "", smeta = "";
			Object materia = ""; 
			boolean urgency;
		BannerCalculator univers = new BannerCalculator();           //������ ��������� ������
			boolean checkEmptyText = univers.checkForEmptiness((input1.getText()),(input2.getText()),(input3.getText()));
				if (checkEmptyText == true){
					JOptionPane.showMessageDialog(null,
				    		"���������� ��������� ��� ����",
				    		"������",
				    	    JOptionPane.PLAIN_MESSAGE);
				}
				else {
					wid = Integer.parseInt(input1.getText());
					heig=Integer.parseInt(input2.getText());
					quantity=Integer.parseInt(input3.getText());
					materia = editComboBox.getSelectedItem();
					double fullItog = univers.add(wid,heig,quantity); 
						mainitog = fullItog;
							if (materia == "ظ��") {
								fullItog = univers.mult(fullItog,3);
							}
							else if (materia == "�������� �����") {
								fullItog = univers.mult(fullItog,1);
							}
							else {
								fullItog = univers.mult(fullItog,0.1);
							}
				fullItog +=(radio1.isSelected()?0:mainitog + (mainitog * 0.05));  //�����������/����������
				fullItog +=(check.isSelected()?(mainitog + (mainitog * 0.5)):0); //�������/���������	
				smeta = Double.toString(fullItog);
					message += "���� = :"+ smeta +" ���. \n";
					message += "������ - " + input1.getText() +",�"+"\n";
					message += "������ - " + input2.getText() +",�"+ "\n";
					message += "���������� - " + input3.getText() + "\n";
					message += "�������� - " + editComboBox.getSelectedItem()+ "\n";
					message += "�������� ������ - "+ (radio1.isSelected()?"�����������":"���������") 
		                                + "\n"; 
					message += "��������� - " + ((check.isSelected())
		                                ?"������� �����":"��������� �����"); 
						JOptionPane.showMessageDialog(null,
					    		message,
					    		"������ ���������",
					    	    JOptionPane.PLAIN_MESSAGE);
						}
		}
	}	/* ����� ����� � ���������*/ /* AWT-����. ��������. ����, IE - ����������� ������ ����������*/ 	     
	 public static void main(String[] args) throws AWTException, InterruptedException {
		 BannerCalculator app = new BannerCalculator();
		 app.setVisible(true); 
	}
} /* ����� �������������� ���-�� ��������*/
class CustomJTextField extends JTextField {
    public CustomJTextField(int columns) {
        super(columns);
    }
 /*��������������� ������ - ������ ��������� ������ �� ���������. ��������� ����� createDefaultModel*/
    @Override 
    protected Document createDefaultModel() {
        return new CustomPlainDocument();
    }
}

