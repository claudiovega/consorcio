package cl.consorcio.helper;

import cl.consorcio.dao.FarmaciasDAORest;
import cl.consorcio.vo.Comuna;
import cl.consorcio.vo.Farmacia;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Type;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FarmaciasHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(FarmaciasHelper.class);

    @Autowired
    FarmaciasDAORest farmaciasDAORest;

    public List<Comuna> getComunasByIdRegion(String idRegion){
        List<Comuna> result = new ArrayList<Comuna>();
        try {
            result = farmaciasDAORest.callFarmaciasMapMinsal(idRegion);
        } catch (Exception ignored) {

        }
        return result;
    }
    public List<Farmacia> getFarmaciasByIdComuna(String idRegion, String idComuna, String nombreFarmacia){
        List<Farmacia> result = new ArrayList<Farmacia>();
        try {
            String jsonFarmacias = farmaciasDAORest.callFarmaciasByRegion(idRegion);
            Gson gson = new Gson();
            Type collectionType = new TypeToken<List<Farmacia>>(){}.getType();
            result = gson.fromJson(jsonFarmacias, collectionType);
            if (!StringUtils.isEmpty(idComuna)){
                result = this.filterByIdComuna(result, idComuna);
            }
            if (!StringUtils.isEmpty(nombreFarmacia)){
                result = this.filterByNombreFarmacia(result, nombreFarmacia);
            }
            if (!CollectionUtils.isEmpty(result)){
                result.sort(Comparator.comparing(Farmacia::getLocalNombre));
            }
            LOGGER.info(result.toString());

        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return result;
    }
    private List<Farmacia> filterByIdComuna(List<Farmacia> listaFarmacias,String idComuna){
        List<Farmacia> result = new ArrayList<>();
        if (!StringUtils.isEmpty(idComuna) && !CollectionUtils.isEmpty(listaFarmacias)){
            result = listaFarmacias.stream().filter(x -> idComuna.trim().equals(x.getFkComuna())).collect(Collectors.toList());

        }

        return result;
    }

    private List<Farmacia> filterByNombreFarmacia(List<Farmacia> listaFarmacias, String nombre){
        List<Farmacia> result = new ArrayList<>();
        if (!StringUtils.isEmpty(nombre) && !CollectionUtils.isEmpty(listaFarmacias)){
            //result = listaFarmacias.stream().filter(x -> limpiaAcentos(nombre).toLowerCase().replace(" ","").equals(limpiaAcentos(x.getLocalNombre()).trim().toLowerCase().replace(" ",""))).collect(Collectors.toList());
            result = listaFarmacias.stream().filter(x -> limpiaAcentos(x.getLocalNombre()).toLowerCase().replace(" ","").contains(limpiaAcentos(nombre).trim().toLowerCase().replace(" ",""))).collect(Collectors.toList());

        }

        return result;
    }
    private String limpiaAcentos(String cadena) {
        cadena = Normalizer.normalize(cadena, Normalizer.Form.NFD);
        cadena = cadena.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return cadena;
    }
}
