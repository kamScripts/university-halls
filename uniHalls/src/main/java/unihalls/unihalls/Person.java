package unihalls.unihalls;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;

/**
 *  
 * @author kg00k
 */
public abstract class Person {
/**
 * Abstract class, acting as base class for Halls Resident
 * 
 */
    private String name;
    private int age;
    private String email;
    private Gender gender;// Timestamp on object init.
    private Room assignedRoom;// Assigned room in Hall.
    private final LocalDateTime createdAt;// Account creation timestamp.
    private final HashSet<Facility> needsList;// Set of the resident's needs.

    public Person(String name, int age, String email, Gender gender) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.gender = gender;
        assignedRoom = null;
        createdAt = LocalDateTime.now();
        needsList = new HashSet<>();
    }
    @Override
    public String toString() {
        return "Person{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", email='" + email + '\'' +
            ", gender=" + gender +
            ", assignedRoom=" + (assignedRoom != null ? getAssignedRoom() : "None") +
            ", createdAt=" + createdAt +
            ", needsList=" + needsList +
            '}';

    }
    public void addNeed(Facility [] needs) {
        Collections.addAll(needsList, needs);
    }
    
    public void removeNeed(Facility item) {
        this.needsList.remove(item);
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

    public Room getAssignedRoom() {
        return assignedRoom;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public HashSet<Facility> getNeedsList() {
        return needsList;
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

    public void setAssignedRoom(Room assignedRoom) {
        this.assignedRoom = assignedRoom;
    }
     
}
