package kz.ali.foodHibirnate.entities;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "food")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "calories")
    private int calories;

    @Column(name = "amounts")
    private int amounts;

    @Column(name = "price")
    private int price;

    @JoinColumn(name = "manufacturer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Manufacturer manufacturer;
}