package cl.consorcio;

import cl.consorcio.controller.FarmaciasController;
import cl.consorcio.dao.FarmaciasDAORest;
import cl.consorcio.helper.FarmaciasHelper;
import cl.consorcio.vo.Comuna;
import cl.consorcio.vo.Farmacia;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ConsorcioApplicationTests {

	@Autowired
	FarmaciasController farmaciasController;

	@Autowired
	FarmaciasDAORest farmaciasDAORest;

	@Autowired
	FarmaciasHelper farmaciasHelper;
	private static Logger logger = LogManager.getLogger(ConsorcioApplicationTests.class);
	@Test
	void testComunasByRegion() {
		logger.info("Ejecutando testComunasByRegion");
		List<Comuna> listaComunas = null;
		try {
			listaComunas = farmaciasHelper.getComunasByIdRegion("7");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertThat(listaComunas).isNotEmpty();
	}
	@Test
	void testFarmciasByComuna() {
		logger.info("Ejecutando testFarmciasByComuna");
		List<Farmacia> listaFarmacias = null;
		try {
			listaFarmacias = farmaciasHelper.getFarmaciasByIdComuna("7","127",null, "2");
			assertThat(listaFarmacias).isNotEmpty();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Test
	void testFarmciasFiltroNombre() {
		logger.info("Ejecutando testFarmciasFiltroNomnre");
		List<Farmacia> listaFarmacias = null;
		try {
			listaFarmacias = farmaciasHelper.getFarmaciasByIdComuna("7","127","san Nicolás","2");
			assertThat(listaFarmacias).isNotEmpty();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	void testFarmciasDAORest() {
		logger.info("Ejecutando farmaciasDAORest.callFarmaciasByRegion");
		try {
			String strFarmacias = farmaciasDAORest.callFarmaciasByRegion("7");
			assertThat(strFarmacias).isNotNull();
			assertThat(strFarmacias).isNotEmpty();
		} catch (Exception e) {

		}
	}
}
