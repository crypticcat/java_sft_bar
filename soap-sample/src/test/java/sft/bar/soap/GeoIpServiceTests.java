package sft.bar.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIpLocation () {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("80.249.184.114");
        System.out.println(ipLocation);
        assertEquals(ipLocation, "<GeoIP><Country>RU</Country><State>66</State></GeoIP>");
    }
}
