import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

//Сериализация - это запись файл введенных данных и все их свойства(типа которые были прописаны в классе Profile 

public class Main {

	private static ArrayList<Profile> profiles = new ArrayList<Profile>();
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		//Перед первым запуском эту строчку закоментить, так как файл еще не создан
		profiles = (ArrayList<Profile>) deserData("profiles");  // Этот метод будет деселеризовывать данные и вызывается в начале проги
		// чтобы нам его вернуть в программу нам нужно написать, что profiles - результат работы как раз метода deserData             - здесь продолжение с 35 строки пояснения
		//desrData - ведь как раз и возвращает нам значение сереализованной ранее переменной profiles 
		// Но, почкольку он object, нам нужно скаставать его как Profile
		
		System.out.println(profiles.size());
		Profile profile = new Profile();
		profile.setName(JOptionPane.showInputDialog(null, "Введите ваше имя"));
		profile.setSurname(JOptionPane.showInputDialog(null, "Введите вашу Фамилию"));
		profiles.add(profile);
		for (Profile p : profiles) {
			System.out.println(p.getName() + " " + p.getSurname());
		}
		System.out.println(profiles.size());					
		serData("profiles", profiles);  // В конце проги будет происходить сериализация наших данных                                  ^		
		//Взяли объект profiles типа ArrayList и сереализовали в этот файл ("profiles"),теперь  --------------------------------------|

	}

	private static Object deserData(String file_name) {
		Object retObject = null;
		try {
			FileInputStream   fileIn = new FileInputStream(file_name + ".ser"); // создаем входящий поток из файла file_name + ".ser", 
			// Считываем, что в нем есть, а внем находится массив байт, котрый является предствалением нашего объекта
			
			//далее этот объект байт мы запихиваем в ObjectInputStream, который  преобразует наш массив байт обратно в объект
			ObjectInputStream in = new ObjectInputStream(fileIn); //
			// И мы это складываем в retObject 
				retObject = in.readObject();
		
			fileIn.close();
			in.close();
			} catch (FileNotFoundException e) {
				System.out.println("Файл не найден");
				System.exit(1);  // Закрываем прогу, так как если не серелизуем данные, нам не нужно чтобы они дальше заполнялись неправильно
			} catch (IOException e) {
				System.out.println("Ошибка ввода/вывода");
				System.exit(2);
			}catch (ClassNotFoundException e) {
				System.out.println("Класс не найден");
				System.exit(3);
			}
		
		//и возвращааем этой функции
		return retObject;
	}

	private static void serData(String file_name, Object obj) {
		try {
			// Создаем поток для файла
			FileOutputStream   fileOut = new FileOutputStream(file_name + ".ser"); //OutputStream - Исходящий Поток данных, который подхватывает система
			//и далее работает сним   FileOutputStream - Поток вывода файлов и данных в эти файлы
			
			// Создаем поток для объекта, (profiles - это объект типа ArrayList) и чтобы переконвертировать объект в массив байт
			// нам нужен ObjectSream
			ObjectOutputStream out = new ObjectOutputStream(fileOut); //ObjectOutputStream записывает наши серелизованные данные (наш объект в виде массива байт)
				// в FileOutputStream.  А FileOutputStream  уже будет выводить в наш file_name + .ser	
			out.writeObject(obj);  // Записываем в потоки 
			fileOut.close();
			out.close();
			} catch (FileNotFoundException e) {
				System.out.println("Файл не найден");
				System.exit(1);  // Закрываем прогу, так как если не серелизуем данные, нам не нужно чтобы они дальше заполнялись неправильно
			} catch (IOException e) {
				System.out.println("Ошибка ввода/вывода");
				System.exit(2);
			}
	}
}
