package keshe.data.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@ToString
@Entity
@Table(name = "TbPosition")
@EntityListeners(AuditingEntityListener.class)
public class TbPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "worker_id")
    private Integer workerid;

    @Column(name = "password")
    private String password;

    @Column(name = "lei_bie")
    private String leibie;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TbPosition other = (TbPosition) that;
        return (this.getWorkerid() == null ? other.getWorkerid() == null : this.getWorkerid().equals(other.getWorkerid()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getLeibie() == null ? other.getLeibie() == null : this.getLeibie().equals(other.getLeibie()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWorkerid() == null) ? 0 : getWorkerid().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getLeibie() == null) ? 0 : getLeibie().hashCode());
        return result;
    }
}