package gan.cloud.co.id.repository.pos;

import gan.cloud.co.id.models.pos.Barang;
import gan.cloud.co.id.service.pos.BarangService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Argan
 */
public interface BarangRepository extends PagingAndSortingRepository<Barang, String>{

    /* Additional Function or Method*/
    @Query("select b from Barang b where b.namaBarang like :namaBarang ")
    public Barang findByNamaBarang(@Param("namaBarang") String namaBarang);

    @Transactional
    @Modifying
    @Query("update Barang b set b.namaBarang=:namaBarang where b.codeBarang=:codeBarang ")
    public void updateNamaBarangByCodeBarang(@Param("namaBarang") String namaBarang,
                                             @Param("codeBarang") String codeBarang);

}
