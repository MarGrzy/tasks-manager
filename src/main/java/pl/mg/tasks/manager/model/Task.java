package pl.mg.tasks.manager.model;

import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

import java.sql.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tasks")
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement(name = "task")
@XmlAccessorType(XmlAccessType.FIELD)
public class Task {

    @Id
    @XmlElement
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @XmlElement
    @Column(name = "task_name")
    private String name;

    @XmlElement
    private Date date;

    @XmlElementWrapper(name = "elements")
    @XmlElement(name = "element")
    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Element> elements;
}
