package uni.universityhalls.people;

import java.io.Serializable;
import java.time.LocalDateTime;


/**Abstract Base class for custom Residents of uniHalls
 *
 * @author KG
 * @version 10/01/26
 */
public abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String name;
    private final int age;
    private final String email;
    private final Gender gender;
    private final LocalDateTime createdAt;// Account creation timestamp.

    public Person(String name, int age, String email, Gender gender) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.gender = gender;
        createdAt = LocalDateTime.now();

    }
    protected Person(Person other) {
        name = other.name;
        age = other.age;
        email = other.email;
        gender = other.gender;
        createdAt = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

