package entryPoint;

/**
 * @author �������
 * �������
 * ��� �����, ������� ��������  ��������� ���������� � ������������:
 * ����� � ������������ � ��������� ��������� (������� ������)
 * ������� � ���
 * ����� ����������� �����
 * ����� �������� � ������� +XXX(XX)XXX-XX-XX
 * ���������� ������� ����� User, ���������� ��� ����������������� ���� � ��������� ���������� ����������
 * �������������� ����� toString() ���, ����� ��� �������������� ��������������.
 */
public class EntryPoint {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Start.");
		
		User user = new User();
		user.fillAllFields(//"������� �������\n" +
				"��. ����� ������, �. 1, ��. 10\n" +
				"224001 �. �����\n" +
				"��������\n" +
				"������� �������\n" +
				"+375(29)690-49-89\n" +
				"+375(29)690-49-81\n" +
				"+375 (29)      690-49-89\n" +
				"Zhenya_Bogomaz@mail.ru\n" +
				"ZhenyaBogomaz@gmail.com\n");
		System.out.println(user.toString());
		
		System.out.println("End.");
	}
}
