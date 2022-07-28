import java.io.Serializable;

public class Profile implements Serializable{  // просто маркер, что этот класс можно сериализовать

	private String name, surname;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}
