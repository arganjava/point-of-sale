package gan.cloud.co.id.controllers.pos;

import gan.cloud.co.id.models.pos.Barang;
import gan.cloud.co.id.repository.pos.BarangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Argan.
 */
@Controller
public class BarangController {

    @Autowired
    private BarangRepository barangRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String barangList(ModelMap modelMap){
        List<Barang> barangs = (List<Barang>) barangRepository.findAll();
        modelMap.addAttribute("barangs", barangs);

        return "barang";
    }

}
