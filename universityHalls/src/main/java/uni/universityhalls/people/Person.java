package uni.universityhalls.people;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**Abstract Base class for custom Residents of uniHalls
 *
 * @author Kamil Gebski
 * @version 10/01/26
 */
public abstract class Person implements Serializable {
    private String name;
    private int age;
    private String email;
    private Gender gender;// Timestamp on object init.
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
        createdAt = other.createdAt;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}

