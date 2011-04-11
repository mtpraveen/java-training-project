package entryPoint;

/**
 * @author Евгений
 * Задание
 * Дан текст, который содержит  следующую информацию о пользователе:
 * Адрес в соответствии с почтовыми правилами (включая индекс)
 * Фамилию и имя
 * Адрес электронной почты
 * Номер телефона в формате +XXX(XX)XXX-XX-XX
 * Необходимо создать класс User, содержащий все вышеперечисленные поля и заполнить найденными значениями
 * Переопределить метод toString() так, чтобы там использовалось форматирование.
 */
public class EntryPoint {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Start.");
		
		User user = new User();
		user.fillAllFields(//"Богомаз Евгений\n" +
				"ул. Карла Маркса, д. 1, кв. 10\n" +
				"224001 г. Брест\n" +
				"Беларусь\n" +
				"Богомаз Евгений\n" +
				"+375(29)690-49-89\n" +
				"+375(29)690-49-81\n" +
				"+375 (29)      690-49-89\n" +
				"Zhenya_Bogomaz@mail.ru\n" +
				"ZhenyaBogomaz@gmail.com\n");
		System.out.println(user.toString());
		
		System.out.println("End.");
	}
}
