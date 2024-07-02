package kg.neobis.invoice.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "node")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Node extends BaseEntity {

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "description", nullable = false)
    String description;

    @Column(name = "ip", nullable = false)
    String ip;

    @Column(name = "port", nullable = false)
    String port;


}

