package ru.calc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Test;
import ru.calc.banners.BannerCalculator;
import ru.calc.banners.CustomPlainDocument;
import javax.swing.text.PlainDocument.*;

public class TestBannerCalculator {
	@Test /*�������� ���������: (������*������)*���-��*�������(20) */ 
	public void testVolume() {
		BannerCalculator a=new BannerCalculator();
		double res = a.add(2, 3, 4);
		assertEquals(480, res);      
	}
	
	@Test /*�������� ��� ������� ������ ����� */
	public void testTrue() {
		CustomPlainDocument a=new CustomPlainDocument();
		boolean res = a.isNumber("122");
		if (res !=true)Assert.fail();
	}
	
	@Test /*�������� ��������� ����������: ���������+(���������*��������� ���������)*/
	public void testMateria() {
		BannerCalculator a=new BannerCalculator();
		double res = a.mult(100, 3);
		if (res !=400)Assert.fail();     
		}

	@Test /* �� ��, ��� ��� ��������� ���� ���������*/
	public void testForEmptiness() {
		BannerCalculator a=new BannerCalculator();
		boolean res = a.checkForEmptiness("������","99", "���");
		if (res !=false)Assert.fail();     
		}
}
