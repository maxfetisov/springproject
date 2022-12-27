package springproject.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name="purchases")
public class Purchase {
    @Id
    private int number;
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;
    @ManyToOne
    @JoinColumn(name = "good_id", nullable = false)
    private Good good;
}
