package be.atc.salesmanagercrm.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "conversations", schema = "salesmanagercrm")
@NamedQueries({
        @NamedQuery(name = "Conversations.findAll", query = "select c from ConversationsEntity c where c.active = true order by  c.active desc, c.creationDate desc "),
        @NamedQuery(name = "Conversations.findAllConv", query = "select c from ConversationsEntity c order by  c.active desc, c.creationDate desc "),

})
public class ConversationsEntity {
    private int id;
    private LocalDateTime creationDate;
    private String message;
    private boolean isActive;
    private UsersEntity usersByIdUsers;

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
    @Column(name = "Creation_Date", nullable = false)
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "Message", nullable = false)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "IsActive", nullable = false)
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversationsEntity that = (ConversationsEntity) o;
        return id == that.id && isActive == that.isActive && Objects.equals(creationDate, that.creationDate) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationDate, message, isActive);
    }

    @ManyToOne
    @JoinColumn(name = "ID_Users", referencedColumnName = "ID", nullable = false)
    public UsersEntity getUsersByIdUsers() {
        return usersByIdUsers;
    }

    public void setUsersByIdUsers(UsersEntity usersByIdUsers) {
        this.usersByIdUsers = usersByIdUsers;
    }
}
