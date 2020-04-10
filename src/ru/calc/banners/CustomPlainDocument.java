package ru.calc.banners;

import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.AttributeSet;				//��������� ���������� ��������d
import javax.swing.text.BadLocationException;		//���������� - ��������� � ������� ��������� �� ��������������, ������� �� ����������
import javax.swing.text.Document; 					//��������
import javax.swing.text.PlainDocument;              //������� ��������, ������� �� �������� ��������� 
import java.util.regex.Matcher; 					//Matcher � ����� ��������� ������������ � �� � ������ ���������� ����� ������������
import java.util.regex.Pattern; 					//Pattern ������������ ����� ���������������� ������������� ����������� ���������� (��)

/* PlainDocument - ������� ��������, ������� �� �������� ���������
�������������� ����� insertString, ������� �������� ��������
���������: offset - �������� � ��������� > = 0, str - ������ ��� �������, a- ��������, ��������� � ����������� ����������.*/

/* ����� ������� �� ��� ������ ������ ����� ����*/
public class CustomPlainDocument extends PlainDocument {
			@Override 
		    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		        if (str == null) { 
		            return;                        //����� ����������� ������ �� ������ �� �����.
		        }
		        if (isNumber(str)) {               //���� ������ ��� ����� (������) - �� �������� �� �� ��������� �� ��� 300)
		            int number = Integer.parseInt(getText(0, getLength()) + str); //parseInt ����������� ������ � �����(��������,����� ������) 
		            if (number < 300) {
		                super.insertString(offs, str, a);
		            }
		        }
		    } /*����� ��������� ���������� �� ����� � ������ � ���������� ������ ��� ���� */
public boolean isNumber(String string) {     
		        Pattern pattern = Pattern.compile("[0-9]+$");      //����������� ���������� ��������� � ������. $-����� ������
		        Matcher matcher = pattern.matcher(string);        
		        return matcher.matches();                          //Matches - ������ true �����, ����� ��� ������ ������������� ��������� ��
		    }

}
