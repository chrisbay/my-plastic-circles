package net.chrisbay.myplasticcircles.auth.user;


import jakarta.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue
    private int id;

    @Enumerated(EnumType.STRING)
    private RoleType type;

    public Role(RoleType type) {
        this.type = type;
    }

    public Role() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }
}
