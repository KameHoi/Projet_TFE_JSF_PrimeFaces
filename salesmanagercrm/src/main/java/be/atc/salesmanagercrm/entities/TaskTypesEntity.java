package be.atc.salesmanagercrm.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "task_types", schema = "salesmanagercrm")
@NamedQueries({
        @NamedQuery(name = "TaskTypes.findAll", query = "SELECT tT from TaskTypesEntity tT"),
        @NamedQuery(name = "TaskTypes.findTaskTypesEntityByLabel", query = "SELECT tT from TaskTypesEntity tT where tt.label = :label"),
})
public class TaskTypesEntity {
    private int id;
    private String label;
    private Collection<TasksEntity> tasksById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Label", nullable = false)
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskTypesEntity that = (TaskTypesEntity) o;
        return id == that.id && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label);
    }

    @OneToMany(mappedBy = "taskTypesByIdTaskTypes")
    public Collection<TasksEntity> getTasksById() {
        return tasksById;
    }

    public void setTasksById(Collection<TasksEntity> tasksById) {
        this.tasksById = tasksById;
    }
}
