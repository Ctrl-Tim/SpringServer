package com.salon.SpringServer;

import com.salon.SpringServer.model.Cosmetic;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Assertions;
import com.salon.SpringServer.service.CosmeticService;

import java.util.List;

@SpringBootTest
class SpringServerApplicationTests {

	@Test
	void contextLoads() {
	}

    private static final Logger log = LoggerFactory.getLogger(SpringServerApplication.class);

    @Autowired
    private CosmeticService cosmeticService;

    @Test
    void testDeliveryCreate() {
        List<Cosmetic> cosmeticList = cosmeticService.getCosmetics();
        for (Cosmetic c : cosmeticList) {
            cosmeticService.deleteCosmetic(c.getId());
        }
        Cosmetic new_cosmetic = new Cosmetic();
        new_cosmetic.setName("Пудра");
        new_cosmetic.setPrice(200);
        final Cosmetic cosmetic = cosmeticService.addCosmetic(new_cosmetic);
        log.info(cosmetic.toString());
        Assertions.assertNotNull(cosmetic.getId());
    }

    @Test
    void testCosmeticRead() {
        List<Cosmetic> cosmeticList = cosmeticService.getCosmetics();
        for (Cosmetic c : cosmeticList) {
            cosmeticService.deleteCosmetic(c.getId());
        }
        Cosmetic new_cosmetic = new Cosmetic();
        new_cosmetic.setName("Пудра");
        new_cosmetic.setPrice(200);
        final Cosmetic cosmetic = cosmeticService.addCosmetic(new_cosmetic);
        log.info(cosmetic.toString());
        final Cosmetic findCosmetic = cosmeticService.getCosmetic(cosmetic.getId());
        log.info(findCosmetic.toString());
        Assertions.assertEquals(cosmetic.getId(), findCosmetic.getId());
    }

    @Test
    void testCosmeticReadAll() {
        List<Cosmetic> cosmeticList = cosmeticService.getCosmetics();
        for (Cosmetic c : cosmeticList) {
            cosmeticService.deleteCosmetic(c.getId());
        }
        Cosmetic new_cosmetic = new Cosmetic();
        new_cosmetic.setName("Пудра");
        new_cosmetic.setPrice(200);
        final Cosmetic cosmetic = cosmeticService.addCosmetic(new_cosmetic);
        Cosmetic new_cosmetic_2 = new Cosmetic();
        new_cosmetic_2.setName("Тушь");
        new_cosmetic_2.setPrice(100);
        final Cosmetic cosmetic2 = cosmeticService.addCosmetic(new_cosmetic_2);
        final List<Cosmetic> cosmetics = cosmeticService.getCosmetics();
        log.info(cosmetics.toString());
        Assertions.assertEquals(cosmetics.size(), 2);
    }
}
