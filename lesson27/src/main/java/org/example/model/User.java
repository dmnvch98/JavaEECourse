package org.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "filterUsers", query = "select u from User u where u.username like CONCAT(:prefix,'%')"),
        @NamedQuery(name = "isExists", query = "select u from User u where u.username = :username " +
                "and u.password = :password"),
        @NamedQuery(name = "getUser", query = "select u from User u where u.username = :username")
})
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }
}
