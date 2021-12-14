package keshe.data.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@ToString
@Entity
@Table(name = "TbSeat")
@EntityListeners(AuditingEntityListener.class)
public class TbSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Integer seatid;

    @Column(name = "price")
    private Double price;

    @Column(name = "sold_or_not")
    private String soldornot;

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
        TbSeat other = (TbSeat) that;
        return (this.getSeatid() == null ? other.getSeatid() == null : this.getSeatid().equals(other.getSeatid()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getSoldornot() == null ? other.getSoldornot() == null : this.getSoldornot().equals(other.getSoldornot()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSeatid() == null) ? 0 : getSeatid().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getSoldornot() == null) ? 0 : getSoldornot().hashCode());
        return result;
    }
}