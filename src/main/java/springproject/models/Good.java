package springproject.models;

import javax.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "goods")
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double price;
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;
    @OneToMany(mappedBy = "good", orphanRemoval = true)
    private List<Purchase> purchases;
}
