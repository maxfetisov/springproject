package springproject.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "people")
public class Person {
    @Id
    private String login;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    private String email;
    @OneToMany(mappedBy = "person", orphanRemoval = true)
    private List<Good> goods;
    @OneToMany(mappedBy = "person", orphanRemoval = true)
    private List<Purchase> purchases;
}
