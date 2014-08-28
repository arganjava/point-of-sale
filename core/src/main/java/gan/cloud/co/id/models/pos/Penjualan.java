package gan.cloud.co.id.models.pos;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Argan
 */
@Entity
@Table(name = "tn_penjualan")
public class Penjualan {

    @Id
    @Column(name = "no_penjualan", length = 25)
    private String noPenjualan;
    @Column(name = "tanggal_penjualan")
    private Long tanggalPenjualan;
    @Column(name = "status")
    private boolean status;
    @OneToMany( fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST , CascadeType.MERGE },
            mappedBy = "penjualan", orphanRemoval = true)
    @Column(name = "penjualan", nullable = false)
    private Set<PenjualanBarang> penjualanBarangs= new HashSet<PenjualanBarang>();


    public String getNoPenjualan() {
        return noPenjualan;
    }

    public void setNoPenjualan(String noPenjualan) {
        this.noPenjualan = noPenjualan;
    }

    public Long getTanggalPenjualan() {
        return tanggalPenjualan;
    }

    public void setTanggalPenjualan(Long tanggalPenjualan) {
        this.tanggalPenjualan = tanggalPenjualan;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<PenjualanBarang> getPenjualanBarangs() {
        return penjualanBarangs;
    }

    public void setPenjualanBarangs(Set<PenjualanBarang> penjualanBarangs) {
        this.penjualanBarangs = penjualanBarangs;
    }
}
