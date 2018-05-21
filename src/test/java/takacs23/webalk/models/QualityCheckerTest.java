package takacs23.webalk.models;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class QualityCheckerTest {

    QualityChecker qualityChecker;

    @Before
    public void setUp(){
        qualityChecker = new QualityChecker();
    }

    @Test
    public void getId() throws Exception {
        Long idValue = 4L;

        qualityChecker.setId(idValue);

        assertEquals(idValue, qualityChecker.getId());
    }

    @Test
    public void getFirstName() throws Exception {
        qualityChecker.setFirstName("István");

        assertEquals("István", qualityChecker.getFirstName());
    }

    @Test
    public void getSpirits() throws Exception {
        Spirit spirit = new Spirit();
        HashSet spiritData = new HashSet();
        spiritData.add(spirit);

        qualityChecker.setSpirits(spiritData);

        assertEquals(spiritData, qualityChecker.getSpirits());
    }

    @Test
    public void getLastName() throws Exception {
        qualityChecker.setLastName("Kovács");

        assertEquals("Kovács", qualityChecker.getLastName());
    }

    @Test
    public void getLocation() throws Exception {
        qualityChecker.setLocation("Tatabánya");

        assertEquals("Tatabánya", qualityChecker.getLocation());
    }

    @Test
    public void getPicture() {
        Byte[] byteArrayValue = { 'A', 'B', 'C'};

        qualityChecker.setPicture(byteArrayValue);

        assertEquals(byteArrayValue, qualityChecker.getPicture());
    }
}