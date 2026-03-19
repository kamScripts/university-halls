package uni.universityhalls.people;

import uni.universityhalls.ROOM_TYPE;

import java.util.Objects;

public abstract class Tenant extends Person implements Comparable<Tenant>{
    private static final long serialVersionUID = 1L;
    private final String id;

    public Tenant (String name, int age, String email, Gender gender, String id){
        super(name,age,email,gender);
        this.id = id;
    }
    public Tenant (Tenant other) {
        super(other);
        id = other.getId();
    }

    public abstract ROOM_TYPE preferredRoomType();

    public String getId() {
        return id;
    }
    @Override
    public int compareTo(Tenant other) {
        int typeCompare = this.getClass().getName().compareTo((other.getClass().getName()));
        if (typeCompare != 0) return typeCompare;
        return id.compareTo(other.getId());
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
