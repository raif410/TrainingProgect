package ru.calc.banners;

import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.AttributeSet;				//коллекция уникальных атрибутоd
import javax.swing.text.BadLocationException;		//исключение - сообщения о попытки сослаться на местоположение, которое не существует
import javax.swing.text.Document; 					//документ
import javax.swing.text.PlainDocument;              //Простой документ, который не содержит атрибутов 
import java.util.regex.Matcher; 					//Matcher — класс реализует согласования с РВ и хранит результаты этого согласования
import java.util.regex.Pattern; 					//Pattern представляет собой скомпилированное представление Регулярному Ввыражению (РВ)

/* PlainDocument - Простой документ, который не содержит атрибутов
Перезаписываем метод insertString, который изменяет Документ
Параметры: offset - смещение в документе > = 0, str - строка для вставки, a- атрибуты, связанные с вставленным содержимым.*/

/* Класс который не даёт ввести ничего кроме цифр*/
public class CustomPlainDocument extends PlainDocument {
			@Override 
		    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		        if (str == null) { 
		            return;                        //метод заканчивает работу не доходя до конца.
		        }
		        if (isNumber(str)) {               //Если строка это число (истина) - то проверям не не превышает ли оно 300)
		            int number = Integer.parseInt(getText(0, getLength()) + str); //parseInt преобразует строку в число(смещение,длина строки) 
		            if (number < 300) {
		                super.insertString(offs, str, a);
		            }
		        }
		    } /*метод проверяет принаджети ли число к цифрам и возвращает истину или ложь */
public boolean isNumber(String string) {     
		        Pattern pattern = Pattern.compile("[0-9]+$");      //Компилирует регулярное выражение в шаблон. $-Конец строки
		        Matcher matcher = pattern.matcher(string);        
		        return matcher.matches();                          //Matches - вернет true тогда, когда вся строка соответствует заданному РВ
		    }

}
