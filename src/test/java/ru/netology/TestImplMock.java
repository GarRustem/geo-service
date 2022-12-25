package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

public class TestImplMock {
    GeoService geoService;
    LocalizationService locService;
    MessageSender msImpl;
    Map<String, String> headers;

    @BeforeEach
    void beforeEach() {
        geoService = Mockito.mock(GeoServiceImpl.class);
        locService = Mockito.mock(LocalizationServiceImpl.class);
        msImpl = new MessageSenderImpl(geoService, locService);
        headers = new HashMap<String, String>();
    }

    @Test
    public void testPositiveOneRU() {
        //Test Data
        String ip = "172.123.12.19";
        String testData = "Zdravstvuite";
        Location locationRU = new Location("Moscow", Country.RUSSIA, "Square", 11);
        //Condition
        when(geoService.byIp(ip)).thenReturn(locationRU);
        when(locService.locale(Country.RUSSIA)).thenReturn(testData);
        //Check
        Assertions.assertEquals(testData, locService.locale(Country.RUSSIA));
        //Condition Log
        System.out.println(locService.locale(Country.RUSSIA));
    }

    @Test
    public void testPositiveTwoEN() {
        //Test Data
        String ip = "96.123.12.19";
        String testData = "Welcome";
        Location locationEN = new Location("New York", Country.USA, "Avenue", 9);
        //Condition
        when(geoService.byIp(ip)).thenReturn(locationEN);
        when(locService.locale(Country.USA)).thenReturn(testData);
        //Check
        Assertions.assertEquals(testData, locService.locale(Country.USA));
        //Condition Log
        System.out.println(locService.locale(Country.USA));
    }

    @Test
    public void testPositiveThreeLocationFromIp() {
        // Test Data
        Location location = new Location("New York", Country.USA, " 10th Avenue", 32);
        String expected = "New York";
        // Condition
        when(geoService.byIp("96.44.183.149")).thenReturn(location);
        // Check
        Assertions.assertEquals(expected, location.getCity());
        // Condition Log
        System.out.println(location.getCity());
    }

    @Test
    public void testPositiveCountry() {
        // Test Data
        String expected = "Zdravstvuite";
        // Condition
        when(locService.locale(Country.RUSSIA)).thenReturn(expected);
        // Check
        Assertions.assertEquals(expected, locService.locale(Country.RUSSIA));
        // Condition Log
        System.out.println(locService.locale(Country.RUSSIA));
    }
}
