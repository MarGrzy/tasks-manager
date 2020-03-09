package pl.mg.tasks.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "elements")
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Element {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "element_name")
    private String name;

    @Column(name = "element_details")
    private String details;

    @ManyToOne
    @JoinColumn(name = "task")
    @JsonIgnore
    private Task task;
}
