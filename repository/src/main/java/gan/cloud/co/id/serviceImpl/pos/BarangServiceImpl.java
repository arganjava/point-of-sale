package gan.cloud.co.id.serviceImpl.pos;

import gan.cloud.co.id.models.pos.Barang;
import gan.cloud.co.id.service.pos.BarangService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * Created by Argan.
 */
@Transactional
@Service
public class BarangServiceImpl implements BarangService {

    @PersistenceContext
    @Qualifier("entityManager")
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Barang> findByCodeAndNameAndHargaJual(Map<String, Object> map) {
        String from = " select b from Barang b ";
        String where = " where ";

        for(Map.Entry<String, Object>  entry : map.entrySet()){
            String entrySecond = "b."+entry.getKey();
            if(entrySecond.equals("b.namaBarang")){
                where +=entrySecond+" like "+"'%"+entry.getValue()+"%' and ";
            }
            if(entrySecond.equals("b.hargeJualBarang")){
                where +=entrySecond+" = "+entry.getValue()+" and ";
            }
        }
        Query query =  entityManager.createQuery(from+where+" 1=1 ");
        return query.getResultList();
    }
}
