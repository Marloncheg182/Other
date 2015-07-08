package Service;

import Entity.*;
import Utils.Loggson;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * @author Romanenchuk Oleg
 * Database class
 */

@Singleton
@Startup
@Loggson
@DataSourceDefinition(
        className = "com.mysql.jdbc.Driver",
        name = "jdbc:mysql://localhost:33033/internetShop",
        user = "root",
        password = "root",
        databaseName = "internetShop",
        properties = {"connection=; create=true"}
)
public class DataBaseSource {

    private Category phone;
    private Category tablet;
    private Client user;
    private Client oleg;
    private Client admin;

    @Inject
    private CatalogService catalog;
    @Inject
    private ClientService client;

    @PostConstruct
    private void entities() {
        initCatalog();
        initClient();
    }

    @PreDestroy
    private void clear() {
        catalog.remove(phone);
        catalog.remove(tablet);
        client.removeClient(user);
        client.removeClient(oleg);
        client.removeClient(admin);
    }

    private void initCatalog() {
        phone = new Category("Phone", "Mobile phone device, based on Android OS or IOS OS");
        tablet = new Category("Tablet", "3G and WiFi, based on Android OS or IOS OS");

        // I took all data chars from Amazon.com
        Device iPhone = new Device("iPhone", "Apple corp. phones, IOS device", phone);
        Device hTCOne = new Device("HTC One", "HTC corp. phones, Android device", phone);
        Device samsungGalaxy = new Device("Samsung Galaxy", " Samsung corp.phones, Android device", phone);

        phone.addDevice(iPhone);
        phone.addDevice(hTCOne);
        phone.addDevice(samsungGalaxy);

        Device iPad = new Device("iPad ", "Apple tablets", tablet);
        Device nokia = new Device("Nokia N1", "Nokia licensed tablets", tablet);
        Device samsungGalaxyTab = new Device("Samsung Galaxy Tab", "Samsung tablets", tablet);
        Device googleNexus = new Device("Google Nexus", "Google tablets", tablet);

        tablet.addDevice(iPad);
        tablet.addDevice(nokia);
        tablet.addDevice(samsungGalaxyTab);
        tablet.addDevice(googleNexus);


        Model iPhone5 = new Model("iPhone 5", "Size 16 GB\n" + "Unlocked cell phones are compatible with GSM carriers like AT&T and T-Mobile as well as with GSM SIM cards (e.g. H20, Straight Talk, and select prepaid carriers). Unlocked cell phones will not work with CDMA Carriers like Sprint, Verizon, Boost or Virgin.\n" +
                "16 GB Storage Capacity (Estimated Free Space 12.2 GB)", 370.00f, "iphone5.jpg", iPhone);
        Model iPhone5S = new Model("iPhone 5S", "A7 chip with M7 motion coprocessor,16 GB Storage Capacity (12.2 GB Available)\n" +
                "Touch ID fingerprint sensor\n" +
                "8MP iSight camera with True Tone flash and 1080p HD video recording\n" +
                "Unlocked cell phones are compatible with GSM carriers like AT&T and T-Mobile as well as with GSM SIM cards (e.g. H20 and select prepaid carriers). Unlocked cell phones will not work with CDMA Carriers like Sprint, Verizon, Boost or Virgin.\n" +
                "Unlocked cell phones are compatible with GSM carriers like AT&T and T-Mobile as well as with GSM SIM cards (e.g. H20, Straight Talk, and select prepaid carriers). Unlocked cell phones will not work with CDMA Carriers like Sprint, Verizon, Boost or Virgin.", 515.00f, "iphone5s.jpg", iPhone);
        Model iPhone6 = new Model("iPhone 6", "4.7-inch (diagonal) LED-backlit widescreen Multi-Touch display with IPS technology\n" +
                "New 8-megapixel iSight camera with 1.5µ pixels\n" +
                "A8 chip with 64-bit architecture. M8 motion coprocessor\n" +
                "1080p HD video recording (30 fps or 60 fps)\n" +
                "Unlocked cell phones are compatible with GSM carriers like AT&T and T-Mobile as well as with GSM SIM cards (e.g. H20 and select prepaid carriers). Unlocked cell phones will not work with CDMA Carriers like Sprint, Verizon, Boost or Virgin.", 700.00f, "iphone6.jpg", iPhone);
        Model hTCOneM8 = new Model("HTC One M8", "Display: 5.0-inches\n" +
                "Camera: HTC UltraPixel\n" +
                "Processor Speed: 2.3 GHz\n" +
                "OS: Android 4.4.2 (KitKat)\n" +
                "Unlocked cell phones are compatible with GSM carriers like AT&T and T-Mobile as well as with GSM SIM cards (e.g. H20, Straight Talk, and select prepaid carriers). Unlocked cell phones will not work with CDMA Carriers like Sprint, Verizon, Boost or Virgin.", 400.00f, "htconem8.jpg", hTCOne);
        Model htcOneM9 = new Model("HTC One M9", "New, Unlocked, International GSM model. Will work with AT&T and T-mobile. Will not work with Verizon or sprint.\n" +
                "Please check if your carrier support these GSM networks before purchasing:\n" +
                "2G bands GSM 850 / 900 / 1800 / 1900\n" +
                "3G bands HSDPA 850 / 900 / 1900 / 2100\n" +
                "4G bands LTE band 1(2100), 3(1800), 5(850), 7(2600), 8(900), 20(800), 28(700), 38(2600), 40(2300), 41(2500)\n" +
                "This cell phone may not include a US warranty as some manufacturers do not honor warranties for international version phones. Please contact the seller for specific warranty information.", 599.00f, "htconem9.jpg", hTCOne);
        Model samsungGalaxyS6 = new Model("Samsung Galaxy S6", "Display: Super AMOLED capacitive touchscreen, 16M colors - .1 inches (~70.7% screen-to-body ratio) - 1440 x 2560 pixels (~577 ppi pixel density)\n" +
                "CPU: Quad-core 1.5 GHz Cortex-A53 & Quad-core 2.1 GHz - Exynos 7420\n" +
                "OS: Android OS, v5.0.2 (Lollipop)\n" +
                "Camera Primary: 16 MP, 2988 x 5312 pixels, optical image stabilization, autofocus, LED flash - Secondary: 5 MP, 1080p@30fps, dual video call, Auto HDR\n" +
                "Internal Memory: 32GB, 3 GB RAM\n" +
                "This cell phone may not include a US warranty as some manufacturers do not honor warranties for international version phones. Please contact the seller for specific warranty information.\n" +
                "Unlocked cell phones are compatible with GSM carriers such as AT&T and T-Mobile, but are not compatible with CDMA carriers such as Verizon and Sprint.", 555.95f, "samsunggalaxys6", samsungGalaxy);

        iPhone.addModel(iPhone5);
        iPhone.addModel(iPhone5S);
        iPhone.addModel(iPhone6);
        hTCOne.addModel(hTCOneM8);
        hTCOne.addModel(htcOneM9);
        samsungGalaxy.addModel(samsungGalaxyS6);

        Model iPadMini2 = new Model("iPad Mini 2", "Apple iOS 7, 7.9-Inches Retina Display\n" +
                "Apple A7 chip with 64bit architecture and M7 motion coprocessor\n" +
                "16 GB Flash Memory, 1 GB RAM Memory\n" +
                "10-hour battery life", 295.88f, "ipadmini2.jpg", iPad);
        Model iPadAir = new Model("iPad Air", "Fingerprint-resistant oleophobic coating Display\n" +
                "Apple A7 1.4 GHz\n" +
                "16 GB Flash Memory, 1 GB RAM Memory\n" +
                "10-hour battery life, 1.00 pounds\n" +
                "Apple iOS 7; 9.7 Retina display; 2048 x 1536 resolution", 384.99f, "ipadair", iPad);
        Model nokiaN1 = new Model("Nokia N1", "Android 5.0 Lollipop?Nokia Z Launcher?\n" +
                "Intel 64-bit Atom Processor Z3580\n" +
                "RAM\t2 GB\n" +
                "ROM\t32 GB\n" +
                "NOTE:This version does not supports Google Play Store.", 389.99f, "nokian1.jpg", nokia);
        Model samsungGalaxyNote10 = new Model("Samsung Galaxy Note 10", "Android 4.3 Jelly Bean, 1.9 GHz Exynos 5 Octa 5420 Processor (1.9 GHz Quad Core + 1.3 GHz Quad Core)\n" +
                "32 GB Flash Memory, 3 GB RAM Memory\n" +
                "Camera: 8MP Rear with LED Flash + 2.1MP Front\n" +
                "Super AMOLED Display with 2560x1600 WQXGA Resolution\n" +
                "Features Ultra Power Saving Mode and Fingerprint Scanner", 348.71f, "samsunggalaxytab10.jpg", samsungGalaxyTab);

        Model googleNexus9 = new Model("Google Nexus 9", "Android 5.0 Lollipop; 8.9-Inch IPS LCD TFT Display; QXGA (2048x1536)\n" +
                "NVIDIA Tegra K1 2.3 GHz x64 Processor\n" +
                "16 GB Flash Memory, 2 GB RAM Memory\n" +
                "1.6MP Front Camera; 8MP Rear Camera\n" +
                "0.96 pounds; 802.11ac WiFi; 2x2 (MIMO)", 376.99f, "googlenexus9.jpg", googleNexus);
        iPad.addModel(iPadMini2);
        iPad.addModel(iPadAir);
        nokia.addModel(nokiaN1);
        samsungGalaxyTab.addModel(samsungGalaxyNote10);
        googleNexus.addModel(googleNexus9);

        catalog.create(phone);
        catalog.create(tablet);
    }

    private void initClient(){
        user = new Client("root", "root", "User", "User", "user@internetshop.ru", new Contacts("Pushkinskaya", "Ukraine", "Kharkiv", "Kharkiv Region","61001"));
        oleg = new Client("oleg", "oleg", "Oleg", "Romanenchuk", "romanenchuk.oleg@gmail.com", new Contacts("Pushkinskaya", "Ukraine", "Kharkiv", "Kharkiv Region", "61001"));
        admin = new Client("admin", "admin", "Admin", "Admin", "admin@internetshop.ru", new Contacts("Pushkinskaya", "Ukraine", "Kharkiv", "Kharkiv Region", "61001"));

        client.createClient(user);
        client.createClient(oleg);
        client.createClient(admin);
    }
}
