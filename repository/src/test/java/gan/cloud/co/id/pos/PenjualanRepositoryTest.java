package gan.cloud.co.id.pos;

import gan.cloud.co.id.models.pos.Barang;
import gan.cloud.co.id.models.pos.Penjualan;
import gan.cloud.co.id.models.pos.PenjualanBarang;
import gan.cloud.co.id.repository.pos.BarangRepository;
import gan.cloud.co.id.repository.pos.PenjualanBarangRepository;
import gan.cloud.co.id.repository.pos.PenjualanRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Argan
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:META-INF/spring/applicationContext*.xml")
@ActiveProfiles({"repository", "LOC"})
public class PenjualanRepositoryTest {
    @Autowired
    private PenjualanRepository penjualanRepository;
    @Autowired
    private BarangRepository barangRepository;
    @Autowired
    private PenjualanBarangRepository penjualanBarangRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Test
    public void insertPenjualan() {

        //Pencarian Barang berdasarkan Code Barang
        Barang barang001 = barangRepository.findOne("C-001");
        Barang barang002 = barangRepository.findOne("C-002");
        //Membuat object penjualan yg nantinya akan di save ke dalam repository
        Penjualan penjualan = new Penjualan();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateFormat.format(new Date());
        //set nomer penjualn dengan format dd-MM-yyyy-....
        penjualan.setNoPenjualan(date+"-"+1);
        penjualan.setStatus(true);
        //Set penjualan barang untuk code C-001
        PenjualanBarang penjualanBarang = new PenjualanBarang();
        penjualanBarang.setTanggalPenjualan(new Date().getTime()/1000);
        penjualanBarang.setBarang(barang001);
        penjualanBarang.setPenjualan(penjualan);
        penjualanBarang.setQuantity(2);
        //Set penjualan barang untuk code C-002
        PenjualanBarang penjualanBarang2 = new PenjualanBarang();
        penjualanBarang2.setTanggalPenjualan(new Date().getTime()/1000);
        penjualanBarang2.setBarang(barang002);
        penjualanBarang2.setPenjualan(penjualan);
        penjualanBarang2.setQuantity(2);
        //penjualan barang collections
        Set<PenjualanBarang> penjualanBarangs = new HashSet<PenjualanBarang>();
        //add penjualan barang
        penjualanBarangs.add(penjualanBarang);
        //add penjualan barang
        penjualanBarangs.add(penjualanBarang2);
        //masukan semua penjualan barang kedalam penjualan
        penjualan.setPenjualanBarangs(penjualanBarangs);
        //Set Tanggal Penjualan
        penjualan.setTanggalPenjualan(new Date().getTime()/1000);
        //save disini
        penjualanRepository.save(penjualan);
    }

    @Test
    public void findByJDBCTemplate(){
        String query="select " +
                "p.tanggal_penjualan," +
                "p.no_penjualan," +
                "b.nama_barang," +
                "b.harga_jual_barang," +
                "pb.quantity," +
                "sum(pb.quantity*b.harga_jual_barang) as total " +
                "from tn_penjualan_barang pb " +
                "JOIN tn_penjualan p ON pb.no_penjualan = p.no_penjualan " +
                "JOIN tn_barang b ON b.code_barang=pb.code_barang " +
                "group by " +
                "p.tanggal_penjualan," +
                "p.no_penjualan," +
                "b.nama_barang," +
                "b.harga_jual_barang," +
                "pb.quantity";
      List penjualanBarangs= jdbcTemplate.query(query, new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                PenjualanBarang pb = new PenjualanBarang();
                Penjualan penjualan = new Penjualan();
                Barang barang = new Barang();
                penjualan.setTanggalPenjualan(rs.getLong(1));
                penjualan.setNoPenjualan(rs.getString(2));
                barang.setNamaBarang(rs.getString(3));
                barang.setHargeJualBarang(rs.getDouble(4));
                pb.setPenjualan(penjualan);
                pb.setBarang(barang);
                pb.setQuantity(rs.getInt(5));
                return pb;
            }
        });
        List<PenjualanBarang> list = new ArrayList<PenjualanBarang>(penjualanBarangs);
        Double jumlahTotal = 0D;
        for(PenjualanBarang pb :list){
           System.out.println(
           "  Tanggal : " +new Date(pb.getPenjualan().getTanggalPenjualan()*1000)+
           ", NO : "+pb.getPenjualan().getNoPenjualan()+
           ", Barang : "+pb.getBarang().getNamaBarang()+
           ", Harga Jual : "+pb.getBarang().getHargeJualBarang()+
           ", Quantity : "+pb.getQuantity()+
           ", Total : "+pb.getQuantity()*pb.getBarang().getHargeJualBarang());
            jumlahTotal +=pb.getQuantity()*pb.getBarang().getHargeJualBarang();
        }
        System.out.println("  Jumlah total : Rp."+jumlahTotal);
    }


}
