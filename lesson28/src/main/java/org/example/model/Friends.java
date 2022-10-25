package org.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "friends")
@Data
@NoArgsConstructor
//@NamedQueries({
//        @NamedQuery(name = "getUserFriends",query = "select u from User u " +
//                "where u.id in (select f.secondUser from Friends f where f.firstUser = :userId)")
        //select * from users where id in (select f.second_user from friends f where first_user = 5)
                // query = "select f.secondUser from Friends f where f.firstUser = :userId
//})
public class Friends {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @ManyToOne
    @JoinColumn(name = "first_user")
    private User firstUser;
    @ManyToOne
    @JoinColumn(name = "second_user")
    private User secondUser;

    public Friends(final User firstUser, final User secondUser) {
        this.firstUser = firstUser;
        this.secondUser = secondUser;
    }
}
