package gan.cloud.co.id.models.pos;

import javax.persistence.*;

/**
 * Created by Argan
 */
@Entity
@Table(name = "tn_barang")
public class Barang {

    @Id
    @Column(name = "code_barang",length = 7)
    private String codeBarang;
    @Column(name = "nama_barang", unique = true, length = 15, nullable = false)
    private String namaBarang;
    @Column(name = "harga_beli_barang", nullable = false)
    private Double hargaBeliBarang;
    @Column(name = "harga_jual_barang", nullable = false)
    private Double hargeJualBarang;
    @Column(name = "quantity", nullable = false)
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCodeBarang() {
        return codeBarang;
    }

    public void setCodeBarang(String codeBarang) {
        this.codeBarang = codeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public Double getHargaBeliBarang() {
        return hargaBeliBarang;
    }

    public void setHargaBeliBarang(Double hargaBeliBarang) {
        this.hargaBeliBarang = hargaBeliBarang;
    }

    public Double getHargeJualBarang() {
        return hargeJualBarang;
    }

    public void setHargeJualBarang(Double hargeJualBarang) {
        this.hargeJualBarang = hargeJualBarang;
    }



}
