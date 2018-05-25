package com.aaxis.pojo;

import javax.persistence.*;
import java.util.List;

@Entity(name = "ROLE")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private long id;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private List<User> users;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

//    @Override
//    public String toString() {
//        return "Role{" +
//                "id=" + id +
//                ", roleName='" + roleName + '\'' +
//                ", users=" + users +
//                '}';
//    }
}
