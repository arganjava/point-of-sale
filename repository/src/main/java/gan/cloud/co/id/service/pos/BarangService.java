package gan.cloud.co.id.service.pos;

import gan.cloud.co.id.models.pos.Barang;

import java.util.List;
import java.util.Map;

/**
 * Created by Argan.
 */
public interface BarangService {
    public List<Barang> findByCodeAndNameAndHargaJual(Map<String, Object> map);
}
