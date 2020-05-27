package cl.consorcio.controller;


import cl.consorcio.helper.FarmaciasHelper;
import cl.consorcio.vo.Comuna;
import cl.consorcio.vo.Farmacia;
import cl.consorcio.vo.ResponseVO;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/farmacias")
public class FarmaciasController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FarmaciasController.class);

    @Autowired
    FarmaciasHelper farmaciasHelper;

    @CrossOrigin
    @RequestMapping(value ="/getComuna", method = RequestMethod.POST)
    public ResponseEntity<ResponseVO<List<Comuna>>> getComunasByIdRegion(@RequestBody String idRegion){

        LOGGER.info("INIT getComunasByIdRegion");
        LOGGER.info("PARAM: "+idRegion);
        String id = getValueFromJSON(idRegion, "idRegion");
        List<Comuna> listaComunas = new ArrayList<>();
        ResponseVO<List<Comuna>> responseEntity = new ResponseVO<>();
        try {

            listaComunas = farmaciasHelper.getComunasByIdRegion(id);
            responseEntity.setCodigo("0");
            responseEntity.setMensaje("OK");
            responseEntity.setBody(listaComunas);
        } catch (Exception e) {
            LOGGER.error("ERROR: getComunasByIdRegion "+e);
            return new ResponseEntity<>(new ResponseVO("5", "ERROR: "+e), HttpStatus.OK);

        }

        LOGGER.info("END getComunasByIdRegion");
        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }
    @CrossOrigin
    @RequestMapping(value ="/getFarmacias", method = RequestMethod.POST)
    public ResponseEntity<ResponseVO<List<Farmacia>>> getFarmaciasByIdComuna(@RequestBody String params){

        LOGGER.info("INIT getFarmaciasByIdComuna");
        LOGGER.info("PARAMS: "+params);
        List<Farmacia> listaFarmacias = new ArrayList<>();
        String id = getValueFromJSON(params, "idRegion");
        String comuna = getValueFromJSON(params, "idComuna");
        String farmacia = getValueFromJSON(params, "nombreFarmacia");
        String farmaciaTurno = getValueFromJSON(params, "farmaciaTurno");
        ResponseVO<List<Farmacia>> responseEntity = new ResponseVO<>();

        try {
            listaFarmacias = farmaciasHelper.getFarmaciasByIdComuna(id, comuna, farmacia,farmaciaTurno);
            responseEntity.setCodigo("0");
            responseEntity.setMensaje("OK");
            responseEntity.setBody(listaFarmacias);
        } catch (Exception e) {
            LOGGER.error("ERROR: getFarmaciasByIdComuna "+e);
            return new ResponseEntity<>(new ResponseVO("5", "ERROR: "+e), HttpStatus.OK);
        }

        LOGGER.info("END getFarmaciasByIdComuna");
        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }
    private static String getValueFromJSON(@RequestBody String json, String fieldName) {

        Object obj = JSONValue.parse(json);

        JSONObject jsonObject = (JSONObject) obj;

        return (String) jsonObject.get(fieldName);

    }
}
