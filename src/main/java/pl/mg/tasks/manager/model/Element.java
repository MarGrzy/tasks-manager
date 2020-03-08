package pl.mg.tasks.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "elements")
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement(name = "element")
@XmlAccessorType(XmlAccessType.FIELD)
public class Element {

    @Id
    @XmlElement
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @XmlElement
    @Column(name = "element_name")
    private String name;

    @XmlElement
    @Column(name = "element_details")
    private String details;

    @ManyToOne
    @JoinColumn(name = "task")
    @XmlTransient
    @JsonIgnore
    private Task task;
}
