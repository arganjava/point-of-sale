package gan.cloud.co.id.pos;

import gan.cloud.co.id.models.pos.Barang;
import gan.cloud.co.id.repository.pos.BarangRepository;
import gan.cloud.co.id.service.pos.BarangService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Argan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:META-INF/spring/applicationContext*.xml")
@ActiveProfiles({"repository", "LOC"})
public class BarangRepositoryTest {

    @Autowired
    private BarangRepository barangRepository;
    @Autowired
    private BarangService barangService;

    @Test
    public void saveBarang(){
        double percent = 10/100D; // Mengambil keuntungan 10% dalam setiap penjualan barang
        Barang barang = new Barang();
        barang.setCodeBarang("C-001");
        barang.setNamaBarang("Roko MoalBoros");
        barang.setQuantity(100);
        barang.setHargaBeliBarang(10000D);
        double percentProfit =barang.getHargaBeliBarang()*percent;
        barang.setHargeJualBarang(barang.getHargaBeliBarang() + percentProfit);
        barangRepository.save(barang);
    }
    @Test
    public void updateBarang(){
        Barang barang = barangRepository.findOne("C-001");
        barang.setQuantity(50);
        barangRepository.save(barang);
    }
    @Test
    public void findAllBarang(){
        // Membuat record baru
        double percent = 10/100D; // Mengambil keuntungan 10% dalam setiap penjualan barang
        Barang barang = new Barang();
        barang.setCodeBarang("C-002");
        barang.setNamaBarang("Roko Class Mole");
        barang.setQuantity(25);
        barang.setHargaBeliBarang(90000D);
        double percentProfit =barang.getHargaBeliBarang()*percent;
        barang.setHargeJualBarang(barang.getHargaBeliBarang() + percentProfit);
        barangRepository.save(barang);
        // Find All records from database
        List<Barang> barangs = (List<Barang>) barangRepository.findAll();
        for(Barang b : barangs){
            System.out.println("code -> "+b.getCodeBarang()+" nama -> "+b.getNamaBarang());
        }
    }

    @Test
    public void findByNamaBarangAndHargaJual(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("namaBarang", "roko");
      //  map.put("hargeJualBarang", 99000D);
        List<Barang> barangList= barangService.findByCodeAndNameAndHargaJual(map);
        for(Barang b:barangList){
            System.out.println(" Code -> "+b.getCodeBarang()+"; Nama -> "+b.getNamaBarang()
                    +"; Harga Jual -> "+b.getHargeJualBarang());
        }

    }


}
