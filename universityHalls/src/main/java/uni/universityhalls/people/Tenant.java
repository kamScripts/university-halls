package uni.universityhalls.people;

import uni.universityhalls.ROOM_TYPE;

import java.util.Objects;

public abstract class Tenant extends Person implements Comparable<Tenant>{

    private final String id;

    public Tenant (String name, int age, String email, Gender gender, int id){
        super(name,age,email,gender);
        this.id = Integer.toString(id);
    }
    public Tenant (Tenant other) {
        super(other);
        id = other.getId();
    }

    public abstract ROOM_TYPE prefferedRoomType();
    public String getId() {
        return id;
    }
    @Override
    public int compareTo(Tenant other) {
        return getId().compareTo(other.id);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tenant tenant = (Tenant) o;
        return Objects.equals(getId(), tenant.getId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
