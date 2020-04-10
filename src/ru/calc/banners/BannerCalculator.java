package ru.calc.banners; //название пакета

import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.AttributeSet;				//коллекция уникальных атрибутоd
import javax.swing.text.BadLocationException;		//исключение
import javax.swing.text.Document; 					//документ
import javax.swing.text.PlainDocument;              //Простой документ, который не содержит атрибутов 
import java.util.regex.Matcher; 					//Matcher — класс реализует согласования с РВ и хранит результаты этого согласования
import java.util.regex.Pattern; 					//Pattern представляет собой скомпилированное представление РВ

public class BannerCalculator extends JFrame {
	private JButton button = new JButton("Расчёт стоимости");
	private JTextField input1 = new CustomJTextField(3);  //Класс CustomJTextField
	private JTextField input2 = new CustomJTextField(3);
	private JTextField input3 = new CustomJTextField(3);
	private JLabel label1 = new JLabel("Ширина, м");
	private JLabel label2 = new JLabel("Высота, м");
	private JLabel label3 = new JLabel("Кол-во, шт.");
	private JLabel label4 = new JLabel("Материал");
	private JRadioButton radio1 = new JRadioButton("Стандартное качество печати");
	private JRadioButton radio2 = new JRadioButton("Повышенное качество печати");
	private JCheckBox check = new JCheckBox("Срочный заказ", false);
	private String[] items = {"Шёлк", "Банерная ткань", "Картон"}; 
	private JComboBox editComboBox = new JComboBox(items);     

	public BannerCalculator () { 					  //конструктор основного окна
		super("Калькулятор расчета стоимости печати баннеров"); 
		this.setBounds(500, 100, 550, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    Container container = this.getContentPane();  	  //cоздаём контейнер для объектов
    container.setLayout(new GridLayout(7,3,2,2));
	    container.add(label1);
	    container.add(input1);
	    container.add(label2);
	    container.add(input2);
	    container.add(label3);
	    container.add(input3);
	    container.add(label4);
	    container.add(editComboBox);    
		ButtonGroup group = new ButtonGroup();        //группа RadioButtons
		    group.add(radio1);
		    group.add(radio2);
		    container.add(radio1);
		    radio1.setSelected(true);
		    container.add(radio2);
		container.add(check);
		button.addActionListener(new ButtonEventListener());
		container.add(button);
	}   
	/* метод add вычисляет стоимость: (высота*ширина)*кол-во*наценка(20) */
	public double add(double a, double b, double c) {
		return ((a*b)*c)*20;
	}
	/* метод mult добавляет стоимость материалов: стоимость+(стоимость*стоимость материала) */
	public double mult(double a, double b) {
		return a+(a*b);
	}
	/* метод checkForEmptiness проверяет не пустые ли текстовые поля */
	public boolean checkForEmptiness(String check1,String check2,String check3) {
		 if (check1.trim().length() == 0 || check2.trim().length() == 0 || check3.trim().length() == 0) {
			    return true;  
			    } else return false;
	}
	/*Слушатель в котором происходят основные вычисления и из которого открываются новые окна*/
	private	class ButtonEventListener implements ActionListener {       //композиция
		public void actionPerformed(ActionEvent e) {                    //метод с параметром
			int wid, heig, quantity;
			double itog, mainitog;
			String message = "", smeta = "";
			Object materia = ""; 
			boolean urgency;
		BannerCalculator univers = new BannerCalculator();           //создаём экземпляр класса
			boolean checkEmptyText = univers.checkForEmptiness((input1.getText()),(input2.getText()),(input3.getText()));
				if (checkEmptyText == true){
					JOptionPane.showMessageDialog(null,
				    		"Необходимо запомнить все поля",
				    		"Ошибка",
				    	    JOptionPane.PLAIN_MESSAGE);
				}
				else {
					wid = Integer.parseInt(input1.getText());
					heig=Integer.parseInt(input2.getText());
					quantity=Integer.parseInt(input3.getText());
					materia = editComboBox.getSelectedItem();
					double fullItog = univers.add(wid,heig,quantity); 
						mainitog = fullItog;
							if (materia == "Шёлк") {
								fullItog = univers.mult(fullItog,3);
							}
							else if (materia == "Банерная ткань") {
								fullItog = univers.mult(fullItog,1);
							}
							else {
								fullItog = univers.mult(fullItog,0.1);
							}
				fullItog +=(radio1.isSelected()?0:mainitog + (mainitog * 0.05));  //Стандартное/Повышенное
				fullItog +=(check.isSelected()?(mainitog + (mainitog * 0.5)):0); //Срочный/Несрочные	
				smeta = Double.toString(fullItog);
					message += "Итог = :"+ smeta +" руб. \n";
					message += "Ширина - " + input1.getText() +",м"+"\n";
					message += "Высота - " + input2.getText() +",м"+ "\n";
					message += "Количество - " + input3.getText() + "\n";
					message += "Метериал - " + editComboBox.getSelectedItem()+ "\n";
					message += "Качество печати - "+ (radio1.isSelected()?"Стандартное":"Повышеное") 
		                                + "\n"; 
					message += "Срочность - " + ((check.isSelected())
		                                ?"Срочный заказ":"Несрочный заказ"); 
						JOptionPane.showMessageDialog(null,
					    		message,
					    		"Расчёт стоимости",
					    	    JOptionPane.PLAIN_MESSAGE);
						}
		}
	}	/* Точка входу в программу*/ /* AWT-Искл. абстракт. окон, IE - Блокировние потока исполнения*/ 	     
	 public static void main(String[] args) throws AWTException, InterruptedException {
		 BannerCalculator app = new BannerCalculator();
		 app.setVisible(true); 
	}
} /* Класс ограничивающий кол-во столбцов*/
class CustomJTextField extends JTextField {
    public CustomJTextField(int columns) {
        super(columns);
    }
 /*Переопределение метода - Создаём экземпляр модели по умолчанию. Возврщаем класс createDefaultModel*/
    @Override 
    protected Document createDefaultModel() {
        return new CustomPlainDocument();
    }
}

