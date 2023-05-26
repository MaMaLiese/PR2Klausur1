package at.campus02.iwi.devices;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class DeviceTest {

    private Tablet t1, t2;
    private Smartwatch w1, w2;
    private Smartphone s1, s2;
    private Processor c1, c2, c3, c4;
    private DeviceDatabase db;
    @Before
    public void setup() {

        c1 = new Processor("A11", 1, 2);
        c2 = new Processor("A12", 2, 2);
        c3 = new Processor("A12", 2, 6);
        c4 = new Processor("A13", 3, 4);

        s1 = new Smartphone("s1", c1, 2019, 100000, 2);
        s2 = new Smartphone("s2", c3, 2021, 1000000, 3);

        w1 = new Smartwatch("w1", c2, 2020, 1000, 2);
        w2 = new Smartwatch("w2", c3, 2021, 100, 3);

        t1 = new Tablet("t1", c2, 2020, 1000, false);
        t2 = new Tablet("t2", c4, 2021, 10000, true);
        db = new DeviceDatabase();

        db.addDevice(s1);
        db.addDevice(s2);
        db.addDevice(t1);
        db.addDevice(t2);
        db.addDevice(w1);
        db.addDevice(w2);
    }

    @Test
    public void testConstructor() {
        assertEquals("t1", t1.getName());
        assertEquals(c2, t1.getProcessor());
        assertEquals(2020, t1.getReleaseYear());
        assertEquals(1000, t1.getNrProduced());
        assertEquals(false, t1.isArCamera());

        assertEquals("s1", s1.getName());
        assertEquals(c1, s1.getProcessor());
        assertEquals(2019, s1.getReleaseYear());
        assertEquals(100000, s1.getNrProduced());
        assertEquals(2, s1.getNrCameras());

        assertEquals("w1", w1.getName());
        assertEquals(c2, w1.getProcessor());
        assertEquals(2020, w1.getReleaseYear());
        assertEquals(1000, w1.getNrProduced());
        assertEquals(2, w1.getNrWristSizes());

    }

    @Test
    public void testProductionCost() {
        assertEquals(12600000, s1.calculateProductionCost(), 0.001);
        assertEquals(208000000, s2.calculateProductionCost(), 0.001);
        assertEquals(110000, w1.calculateProductionCost(), 0.001);
        assertEquals(12000, w2.calculateProductionCost(), 0.001);
        assertEquals(350000, t1.calculateProductionCost(), 0.001);
        assertEquals(7500000, t2.calculateProductionCost(), 0.001);

    }

    @Test
    public void testSupportCost() {
        assertEquals(4000000, s1.calculateSupportCostForAYear(2021), 0.001);
        assertEquals(0, s2.calculateSupportCostForAYear(2021), 0.001);
        assertEquals(20000, w1.calculateSupportCostForAYear(2021), 0.001);
        assertEquals(6000, w2.calculateSupportCostForAYear(2021), 0.001);
        assertEquals(45000, t1.calculateSupportCostForAYear(2021), 0.001);
        assertEquals(500000, t2.calculateSupportCostForAYear(2021), 0.001);

    }
    @Test
    public void testDeviceDatabase() {
        assertEquals(228572000, db.calculateSumOfProductionCosts(), 0.001);


        assertEquals(w2, db.findDeviceWithCheapestProductionCost());

        assertEquals(4000000, db.calculateSupportCostUntilYear(s1,2021), 0.001);
        assertEquals(0, db.calculateSupportCostUntilYear(s2,2021), 0.001);
        assertEquals(0, db.calculateSupportCostUntilYear(s2,2017), 0.001);

        assertEquals(s1, db.findDeviceWithHighestSupportCostUntil(2021));


        HashMap<Integer, Integer> min = new HashMap<>();
        min.put(2,4);
        min.put(3,2);
        List<Device> d = db.findDevicesThatFulfillMinimumPerformance(min);
        assertEquals(3, d.size());
        assertTrue(d.contains(s2));
        assertTrue(d.contains(w2));
        assertTrue(d.contains(t2));

        HashMap<String, Integer> numD = db.numberDevicesGroupedByConstructionCost(300, 700);
        assertEquals(3, numD.size());
        assertEquals((Integer)4, numD.get("low"));
        assertEquals((Integer)1, numD.get("medium"));
        assertEquals((Integer)1, numD.get("high"));

    }


}