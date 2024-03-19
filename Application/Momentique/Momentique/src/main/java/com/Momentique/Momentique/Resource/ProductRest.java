package com.Momentique.Momentique.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.Momentique.Momentique.Models.Product;
import com.Momentique.Momentique.Repositories.ProductRepository;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

import java.nio.file.Path;

@RestController
@CrossOrigin(origins = "*")
public class ProductRest {

    private final ProductRepository productRepository;

    public ProductRest(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    @GetMapping("products")
    public ResponseEntity<Iterable<Product>> findAllProducts() {

        Iterable<Product> result = productRepository.findAll();
        return ResponseEntity.ok(result);

    }

    @GetMapping("products/{id}")
    public ResponseEntity<Optional<Product>> findProductById(@PathVariable("id") Long id) {
        Optional<Product> result = productRepository.findById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("products/search/{title}")
    public ResponseEntity<?> findByTitle(@PathVariable("title") String title) {
        System.out.println("Du sökte efter: " + title);

        if (title.length() < 3) {
            String errorMessage = "sökningen måste vara längre än 3 tecken.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }

        Iterable<Product> result = productRepository.searchProductByTitle(title);
        if (!result.iterator().hasNext()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);

    }

    @GetMapping("products/threeRandom")
    public ResponseEntity<Iterable<Product>> getThreeRandom() {
        Iterable<Product> result = productRepository.selectedThreeRandom();

        return ResponseEntity.ok(result);
    }

    @GetMapping("products/loadData")
    public String loadMockData() {

        List<Product> mockProducts = generateMockProducts();
        Iterable<Product> restult = productRepository.saveAll(mockProducts);
        return "yey";
    }

    // Gör så att våran frontend kan få tag på bilderna på servern.
    @GetMapping("/images/products/compressed/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
        Resource resource = new ClassPathResource("static/images/products/compressed/" + imageName);
        byte[] imageBytes = Files.readAllBytes(Path.of(resource.getURI()));
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    // Mockdata, gör saker lite finare
    private List<Product> generateMockProducts() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();

        product1.setTitle("Paris, Frankrike");
        product1.setDescription(
                "Utforska den romantiska staden Paris med dess ikoniska Eiffeltorn, konstgallerier och utsökta mat.");
        product1.setPrice(1200);
        product1.setImageUrl("/001.jpg");
        product1.setPriceId("price_1Orn6GF3bq9e58MhvCDF4wWx");
        products.add(product1);

        Product product2 = new Product();
        product2.setTitle("Kyoto, Japan");
        product2.setDescription(
                "Upptäck den förtrollande staden Kyoto med dess traditionella tempel, vackra trädgårdar och läckra sushi.");
        product2.setPrice(1500);
        product2.setImageUrl("/002.jpg");
        product2.setPriceId("price_1OrnitF3bq9e58MhJukHUfod");
        products.add(product2);

        Product product3 = new Product();
        product3.setTitle("Machu Picchu, Peru");
        product3.setDescription(
                "Upptäck de mystiska ruinerna av Machu Picchu, en av världens mest kända arkeologiska platser, beläget i de peruanska Anderna.");
        product3.setPrice(1800);
        product3.setImageUrl("/003.jpg");
        product3.setPriceId("price_1OsVplF3bq9e58MhknpehdYY");
        products.add(product3);

        Product product4 = new Product();
        product4.setTitle("Rom, Italien");
        product4.setDescription(
                "Utforska den antika staden Rom med dess fascinerande historia, imponerande arkitektur och utsökta italienska kök.");
        product4.setPrice(1400);
        product4.setImageUrl("/004.jpg");
        product4.setPriceId("price_1OsVqZF3bq9e58MhPfrYUOPr");
        products.add(product4);

        Product product5 = new Product();
        product5.setTitle("Santorini, Grekland");
        product5.setDescription(
                "Njut av den spektakulära utsikten över Egeiska havet från de vita kubformade husen i Santorini, en av Greklands vackraste öar.");
        product5.setPrice(1600);
        product5.setImageUrl("/005.jpg");
        product5.setPriceId("price_1OsVrJF3bq9e58MhRqUZnesn");
        products.add(product5);

        Product product6 = new Product();
        product6.setTitle("New York City, USA");
        product6.setDescription(
                "Upptäck den pulserande atmosfären i New York City med dess ikoniska landmärken, brokiga stadsdelar och mångfaldiga matutbud.");
        product6.setPrice(2000);
        product6.setImageUrl("/006.jpg");
        product6.setPriceId("price_1OsVsAF3bq9e58Mh7Jm2Gb0e");
        products.add(product6);

        Product product7 = new Product();
        product7.setTitle("Cape Town, Sydafrika");
        product7.setDescription(
                "Utforska den vackra Cape Town med dess fantastiska stränder, dramatiska bergslandskap och rika kulturella arv.");
        product7.setPrice(1900);
        product7.setImageUrl("/007.jpg");
        product7.setPriceId("price_1OsVsoF3bq9e58MhwkSLD1qk");
        products.add(product7);

        Product product8 = new Product();
        product8.setTitle("Bangkok, Thailand");
        product8.setDescription(
                "Njut av det livliga gatulivet och den rika kulturen i Bangkok, Thailands pulserande huvudstad.");
        product8.setPrice(1300);
        product8.setImageUrl("/008.jpg");
        product8.setPriceId("price_1OsVtjF3bq9e58MhCorfwEUJ");
        products.add(product8);

        Product product9 = new Product();
        product9.setTitle("London, Storbritannien");
        product9.setDescription(
                "Utforska den mångsidiga staden London med dess kungliga palats, världsberömda teatrar och trendiga shoppingdistrikt.");
        product9.setPrice(1700);
        product9.setImageUrl("/009.jpg");
        product9.setPriceId("price_1OsVuCF3bq9e58MhcZ9QhNzE");
        products.add(product9);

        Product product10 = new Product();
        product10.setTitle("Bali, Indonesien");
        product10.setDescription(
                "Njut av den avslappnade atmosfären och de fantastiska stränderna på Bali, en tropisk paradisö i Indonesien.");
        product10.setPrice(1400);
        product10.setImageUrl("/010.jpg");
        product10.setPriceId("price_1OsVwhF3bq9e58MhqMHurSoQ");
        products.add(product10);

        Product product11 = new Product();
        product11.setTitle("Dubai, Förenade Arabemiraten");
        product11.setDescription(
                "Upptäck det moderna och lyxiga Dubai med dess imponerande skyskrapor, lyxiga shoppingcentra och ökenäventyr.");
        product11.setPrice(2200);
        product11.setImageUrl("/011.jpg");
        product11.setPriceId("price_1OsVxZF3bq9e58MhSbCP8ybm");
        products.add(product11);

        Product product12 = new Product();
        product12.setTitle("Barcelona, Spanien");
        product12.setDescription(
                "Utforska den livliga staden Barcelona med dess unika arkitektur, trendiga strandpromenader och pulserande nattliv.");
        product12.setPrice(1500);
        product12.setImageUrl("/012.jpg");
        product12.setPriceId("price_1OsVy2F3bq9e58Mhs38qwP6C");
        products.add(product12);

        Product product13 = new Product();
        product13.setTitle("Tokyo, Japan");
        product13.setDescription(
                "Upptäck den spännande metropolen Tokyo med dess futuristiska teknologi, livliga nöjesdistrikt och traditionella tempel.");
        product13.setPrice(1800);
        product13.setImageUrl("/013.jpg");
        product13.setPriceId("price_1OsVylF3bq9e58MhhNXB39fo");
        products.add(product13);

        Product product14 = new Product();
        product14.setTitle("Venice, Italien");
        product14.setDescription(
                "Njut av en romantisk gondoltur genom Venedigs charmiga kanaler och beundra stadens vackra arkitektur och historiska sevärdheter.");
        product14.setPrice(1600);
        product14.setImageUrl("/014.jpg");
        product14.setPriceId("price_1OsW02F3bq9e58Mhba0gwUmz");
        products.add(product14);

        Product product15 = new Product();
        product15.setTitle("Sydney, Australien");
        product15.setDescription(
                "Utforska den spektakulära staden Sydney med dess ikoniska Operahus, fantastiska stränder och livliga kultur.");
        product15.setPrice(2000);
        product15.setImageUrl("/015.jpg");
        product15.setPriceId("price_1OsW0eF3bq9e58MhgyFahIYT");
        products.add(product15);

        Product product16 = new Product();
        product16.setTitle("Rio de Janeiro, Brasilien");
        product16.setDescription(
                "Njut av den pulserande atmosfären i Rio de Janeiro med dess fantastiska stränder, livliga karneval och imponerande natur.");
        product16.setPrice(1900);
        product16.setImageUrl("/016.jpg");
        product16.setPriceId("price_1OsW1KF3bq9e58MhR5soqbz3");
        products.add(product16);

        Product product17 = new Product();
        product17.setTitle("Prag, Tjeckien");
        product17.setDescription(
                "Upptäck den historiska staden Prag med dess vackra arkitektur, charmiga kullerstensgator och livliga ölstugor.");
        product17.setPrice(1400);
        product17.setImageUrl("/017.jpg");
        product17.setPriceId("price_1OsW1rF3bq9e58MhlpaTd3gm");
        products.add(product17);

        Product product18 = new Product();
        product18.setTitle("Agra, Indien");
        product18.setDescription(
                "Besök den ikoniska Taj Mahal och upplev den rika historien och kulturen i Agra, en av Indiens mest fascinerande städer.");
        product18.setPrice(1700);
        product18.setImageUrl("/018.jpg");
        product18.setPriceId("price_1OsW2PF3bq9e58MhRQaLrYne");
        products.add(product18);

        Product product19 = new Product();
        product19.setTitle("Paris, Frankrike - Vykort");
        product19.setDescription(
                "Utforska den romantiska staden Paris med dess ikoniska Eiffeltorn, konstgallerier och utsökta mat. 14,8cm x 21cm");
        product19.setPrice(80);
        product19.setImageUrl("/001.jpg");
        product19.setPriceId("price_1OvdUjF3bq9e58MhN7CqeKqG");
        product19.setQuntity(24);
        products.add(product19);

        Product product20 = new Product();
        product20.setTitle("Kyoto, Japan - Vykort");
        product20.setDescription(
                "Upptäck den förtrollande staden Kyoto med dess traditionella tempel, vackra trädgårdar och läckra sushi. 14,8cm x 21cm");
        product20.setPrice(80);
        product20.setImageUrl("/002.jpg");
        product20.setPriceId("price_1OvdW5F3bq9e58MhM9SeVScJ");
        product20.setQuntity(35);
        products.add(product20);

        Product product21 = new Product();
        product21.setTitle("Machu Picchu, Peru - Vykort");
        product21.setDescription(
                "Upptäck de mystiska ruinerna av Machu Picchu, en av världens mest kända arkeologiska platser, beläget i de peruanska Anderna. 14,8cm x 21cm");
        product21.setPrice(80);
        product21.setImageUrl("/003.jpg");
        product21.setPriceId("price_1OvdX4F3bq9e58MhqZ9eOGw7");
        product21.setQuntity(37);
        products.add(product21);

        Product product22 = new Product();
        product22.setTitle("Rom, Italien - Vykort");
        product22.setDescription(
                "Utforska den antika staden Rom med dess fascinerande historia, imponerande arkitektur och utsökta italienska kök. 14,8cm x 21cm");
        product22.setPrice(80);
        product22.setImageUrl("/004.jpg");
        product22.setPriceId("price_1OvddaF3bq9e58Mhrs0SVNvl");
        product22.setQuntity(68);
        products.add(product22);

        Product product23 = new Product();
        product23.setTitle("Santorini, Grekland - Vykort");
        product23.setDescription(
                "Njut av den spektakulära utsikten över Egeiska havet från de vita kubformade husen i Santorini, en av Greklands vackraste öar. 14,8cm x 21cm");
        product23.setPrice(80);
        product23.setImageUrl("/005.jpg");
        product23.setPriceId("price_1Ovde5F3bq9e58MhHtc1R4eW");
        product23.setQuntity(8);
        products.add(product23);

        Product product24 = new Product();
        product24.setTitle("New York City, USA - Vykort");
        product24.setDescription(
                "Upptäck den pulserande atmosfären i New York City med dess ikoniska landmärken, brokiga stadsdelar och mångfaldiga matutbud. 14,8cm x 21cm");
        product24.setPrice(80);
        product24.setImageUrl("/006.jpg");
        product24.setPriceId("price_1OvdeeF3bq9e58MhIASWSzo9");
        product24.setQuntity(87);
        products.add(product24);

        Product product25 = new Product();
        product25.setTitle("Cape Town, Sydafrika - Vykort");
        product25.setDescription(
                "Utforska den vackra Cape Town med dess fantastiska stränder, dramatiska bergslandskap och rika kulturella arv. 14,8cm x 21cm");
        product25.setPrice(80);
        product25.setImageUrl("/007.jpg");
        product25.setPriceId("price_1OvdfBF3bq9e58MhV3YopQu5");
        product25.setQuntity(16);
        products.add(product25);

        Product product26 = new Product();
        product26.setTitle("Bangkok, Thailand - Vykort");
        product26.setDescription(
                "Njut av det livliga gatulivet och den rika kulturen i Bangkok, Thailands pulserande huvudstad. 14,8cm x 21cm");
        product26.setPrice(80);
        product26.setImageUrl("/008.jpg");
        product26.setPriceId("price_1OvdfgF3bq9e58Mha6Au4IQi");
        product26.setQuntity(44);
        products.add(product26);

        Product product27 = new Product();
        product27.setTitle("London, Storbritannien - Vykort");
        product27.setDescription(
                "Utforska den mångsidiga staden London med dess kungliga palats, världsberömda teatrar och trendiga shoppingdistrikt. 14,8cm x 21cm");
        product27.setPrice(80);
        product27.setImageUrl("/009.jpg");
        product27.setPriceId("price_1OvdgIF3bq9e58Mh1UwtU0A0");
        product27.setQuntity(48);
        products.add(product27);

        Product product28 = new Product();
        product28.setTitle("Bali, Indonesien - Vykort");
        product28.setDescription(
                "Njut av den avslappnade atmosfären och de fantastiska stränderna på Bali, en tropisk paradisö i Indonesien. 14,8cm x 21cm");
        product28.setPrice(80);
        product28.setImageUrl("/010.jpg");
        product28.setPriceId("price_1OvdgnF3bq9e58MhlUzjwFwv");
        product28.setQuntity(44);
        products.add(product28);

        Product product29 = new Product();
        product29.setTitle("Dubai, Förenade Arabemiraten - Vykort");
        product29.setDescription(
                "Upptäck det moderna och lyxiga Dubai med dess imponerande skyskrapor, lyxiga shoppingcentra och ökenäventyr. 14,8cm x 21cm");
        product29.setPrice(80);
        product29.setImageUrl("/011.jpg");
        product29.setPriceId("price_1OvdhUF3bq9e58MhrB5zIPQJ");
        product29.setQuntity(29);
        products.add(product29);

        Product product30 = new Product();
        product30.setTitle("Barcelona, Spanien - Vykort");
        product30.setDescription(
                "Utforska den livliga staden Barcelona med dess unika arkitektur, trendiga strandpromenader och pulserande nattliv. 14,8cm x 21cm");
        product30.setPrice(80);
        product30.setImageUrl("/012.jpg");
        product30.setPriceId("price_1Ovdi0F3bq9e58MhIFtTuuQw");
        product30.setQuntity(46);
        products.add(product30);

        Product product31 = new Product();
        product31.setTitle("Tokyo, Japan - Vykort");
        product31.setDescription(
                "Upptäck den spännande metropolen Tokyo med dess futuristiska teknologi, livliga nöjesdistrikt och traditionella tempel. 14,8cm x 21cm");
        product31.setPrice(80);
        product31.setImageUrl("/013.jpg");
        product31.setPriceId("price_1OvdiUF3bq9e58MhlNGcEkBe");
        product31.setQuntity(31);
        products.add(product31);

        Product product32 = new Product();
        product32.setTitle("Venice, Italien - Vykort");
        product32.setDescription(
                "Njut av en romantisk gondoltur genom Venedigs charmiga kanaler och beundra stadens vackra arkitektur och historiska sevärdheter. 14,8cm x 21cm");
        product32.setPrice(80);
        product32.setImageUrl("/014.jpg");
        product32.setPriceId("price_1OvdixF3bq9e58MhQNi8VoKf");
        product32.setQuntity(14);
        products.add(product32);

        Product product33 = new Product();
        product33.setTitle("Sydney, Australien - Vykort");
        product33.setDescription(
                "Utforska den spektakulära staden Sydney med dess ikoniska Operahus, fantastiska stränder och livliga kultur. 14,8cm x 21cm");
        product33.setPrice(80);
        product33.setImageUrl("/015.jpg");
        product33.setPriceId("price_1OvdjNF3bq9e58Mhc7tU9MAS");
        product33.setQuntity(52);
        products.add(product33);

        Product product34 = new Product();
        product34.setTitle("Rio de Janeiro, Brasilien - Vykort");
        product34.setDescription(
                "Njut av den pulserande atmosfären i Rio de Janeiro med dess fantastiska stränder, livliga karneval och imponerande natur. 14,8cm x 21cm");
        product34.setPrice(80);
        product34.setImageUrl("/016.jpg");
        product34.setPriceId("price_1OvdjyF3bq9e58MhOZpiq5qI");
        product34.setQuntity(34);
        products.add(product34);

        Product product35 = new Product();
        product35.setTitle("Prag, Tjeckien - Vykort");
        product35.setDescription(
                "Upptäck den historiska staden Prag med dess vackra arkitektur, charmiga kullerstensgator och livliga ölstugor. 14,8cm x 21cm");
        product35.setPrice(80);
        product35.setImageUrl("/017.jpg");
        product35.setPriceId("price_1OvdkRF3bq9e58Mh4CJ1jJoe");
        product35.setQuntity(145);
        products.add(product35);

        Product product36 = new Product();
        product36.setTitle("Agra, Indien - Vykort");
        product36.setDescription(
                "Besök den ikoniska Taj Mahal och upplev den rika historien och kulturen i Agra, en av Indiens mest fascinerande städer. 14,8cm x 21cm");
        product36.setPrice(80);
        product36.setImageUrl("/018.jpg");
        product36.setPriceId("price_1OvdlAF3bq9e58MhJyOcnB40");
        product36.setQuntity(49);
        products.add(product36);

        Product product37 = new Product();
        product37.setTitle("Paris, Frankrike - Tavla");
        product37.setDescription(
                "Utforska den romantiska staden Paris med dess ikoniska Eiffeltorn, konstgallerier och utsökta mat. 100cm x 100cm");
        product37.setPrice(450);
        product37.setImageUrl("/001.jpg");
        product37.setPriceId("price_1Ove67F3bq9e58MhuWEAzobQ");
        product37.setQuntity(38);
        products.add(product37);

        Product product38 = new Product();
        product38.setTitle("Kyoto, Japan - Tavla");
        product38.setDescription(
                "Upptäck den förtrollande staden Kyoto med dess traditionella tempel, vackra trädgårdar och läckra sushi. 100cm x 100cm");
        product38.setPrice(450);
        product38.setImageUrl("/002.jpg");
        product38.setPriceId("price_1OveMbF3bq9e58MhfjjK1bgv");
        product38.setQuntity(16);
        products.add(product38);

        Product product39 = new Product();
        product39.setTitle("Machu Picchu, Peru - Tavla");
        product39.setDescription(
                "Upptäck de mystiska ruinerna av Machu Picchu, en av världens mest kända arkeologiska platser, beläget i de peruanska Anderna. 100cm x 100cm");
        product39.setPrice(450);
        product39.setImageUrl("/003.jpg");
        product39.setPriceId("price_1OveNJF3bq9e58MhfZVcQ1Gj");
        product39.setQuntity(46);
        products.add(product39);

        Product product40 = new Product();
        product40.setTitle("Rom, Italien - Tavla");
        product40.setDescription(
                "Utforska den antika staden Rom med dess fascinerande historia, imponerande arkitektur och utsökta italienska kök. 100cm x 100cm");
        product40.setPrice(450);
        product40.setImageUrl("/004.jpg");
        product40.setPriceId("price_1OveNlF3bq9e58MhJgH9QPAB");
        product40.setQuntity(18);
        products.add(product40);

        Product product41 = new Product();
        product41.setTitle("Santorini, Grekland - Tavla");
        product41.setDescription(
                "Njut av den spektakulära utsikten över Egeiska havet från de vita kubformade husen i Santorini, en av Greklands vackraste öar. 100cm x 100cm");
        product41.setPrice(450);
        product41.setImageUrl("/005.jpg");
        product41.setPriceId("price_1OveO9F3bq9e58Mhtn29xDoH");
        product41.setQuntity(65);
        products.add(product41);

        Product product42 = new Product();
        product42.setTitle("New York City, USA - Tavla");
        product42.setDescription(
                "Upptäck den pulserande atmosfären i New York City med dess ikoniska landmärken, brokiga stadsdelar och mångfaldiga matutbud. 100cm x 100cm");
        product42.setPrice(450);
        product42.setImageUrl("/006.jpg");
        product42.setPriceId("price_1OveP9F3bq9e58MhSxodUqei");
        product42.setQuntity(58);
        products.add(product42);

        Product product43 = new Product();
        product43.setTitle("Cape Town, Sydafrika - Tavla");
        product43.setDescription(
                "Utforska den vackra Cape Town med dess fantastiska stränder, dramatiska bergslandskap och rika kulturella arv. 100cm x 100cm");
        product43.setPrice(450);
        product43.setImageUrl("/007.jpg");
        product43.setPriceId("price_1OvePgF3bq9e58Mh4zWwoerK");
        product43.setQuntity(19);
        products.add(product43);

        Product product44 = new Product();
        product44.setTitle("Bangkok, Thailand - Tavla");
        product44.setDescription(
                "Njut av det livliga gatulivet och den rika kulturen i Bangkok, Thailands pulserande huvudstad. 100cm x 100cm");
        product44.setPrice(450);
        product44.setImageUrl("/008.jpg");
        product44.setPriceId("price_1OveQ6F3bq9e58Mhjb901JbC");
        product44.setQuntity(65);
        products.add(product44);

        Product product45 = new Product();
        product45.setTitle("London, Storbritannien - Tavla");
        product45.setDescription(
                "Utforska den mångsidiga staden London med dess kungliga palats, världsberömda teatrar och trendiga shoppingdistrikt. 100cm x 100cm");
        product45.setPrice(450);
        product45.setImageUrl("/009.jpg");
        product45.setPriceId("price_1OveQZF3bq9e58MhO2LTVxai");
        product45.setQuntity(45);
        products.add(product45);

        Product product46 = new Product();
        product46.setTitle("Bali, Indonesien - Tavla");
        product46.setDescription(
                "Njut av den avslappnade atmosfären och de fantastiska stränderna på Bali, en tropisk paradisö i Indonesien. 100cm x 100cm");
        product46.setPrice(450);
        product46.setImageUrl("/010.jpg");
        product46.setPriceId("price_1OveR8F3bq9e58Mhjb6SMewC");
        product46.setQuntity(414);
        products.add(product46);

        Product product47 = new Product();
        product47.setTitle("Dubai, Förenade Arabemiraten - Tavla");
        product47.setDescription(
                "Upptäck det moderna och lyxiga Dubai med dess imponerande skyskrapor, lyxiga shoppingcentra och ökenäventyr. 100cm x 100cm");
        product47.setPrice(450);
        product47.setImageUrl("/011.jpg");
        product47.setPriceId("price_1OveRVF3bq9e58MhjI4YCDjj");
        product47.setQuntity(78);
        products.add(product47);

        Product product48 = new Product();
        product48.setTitle("Barcelona, Spanien - Tavla");
        product48.setDescription(
                "Utforska den livliga staden Barcelona med dess unika arkitektur, trendiga strandpromenader och pulserande nattliv. 100cm x 100cm");
        product48.setPrice(450);
        product48.setImageUrl("/012.jpg");
        product48.setPriceId("price_1OveSBF3bq9e58MhNcJAMY4Q");
        products.add(product48);

        Product product49 = new Product();
        product49.setTitle("Tokyo, Japan - Tavla");
        product49.setDescription(
                "Upptäck den spännande metropolen Tokyo med dess futuristiska teknologi, livliga nöjesdistrikt och traditionella tempel. 100cm x 100cm");
        product49.setPrice(450);
        product49.setImageUrl("/013.jpg");
        product49.setPriceId("price_1OveSdF3bq9e58Mh0jNIy5rx");
        product49.setQuntity(88);
        products.add(product49);

        Product product50 = new Product();
        product50.setTitle("Venice, Italien - Tavla");
        product50.setDescription(
                "Njut av en romantisk gondoltur genom Venedigs charmiga kanaler och beundra stadens vackra arkitektur och historiska sevärdheter. 100cm x 100cm");
        product50.setPrice(450);
        product50.setImageUrl("/014.jpg");
        product50.setPriceId("price_1OveT9F3bq9e58Mh48JBHhTI");
        product50.setQuntity(44);
        products.add(product50);

        Product product51 = new Product();
        product51.setTitle("Sydney, Australien - Tavla");
        product51.setDescription(
                "Utforska den spektakulära staden Sydney med dess ikoniska Operahus, fantastiska stränder och livliga kultur. 100cm x 100cm");
        product51.setPrice(450);
        product51.setImageUrl("/015.jpg");
        product51.setPriceId("price_1OveUDF3bq9e58Mhgg8HPHqz");
        product51.setQuntity(98);
        products.add(product51);

        Product product52 = new Product();
        product52.setTitle("Rio de Janeiro, Brasilien - Tavla");
        product52.setDescription(
                "Njut av den pulserande atmosfären i Rio de Janeiro med dess fantastiska stränder, livliga karneval och imponerande natur. 100cm x 100cm");
        product52.setPrice(450);
        product52.setImageUrl("/016.jpg");
        product52.setPriceId("price_1OveV1F3bq9e58MhjCafMmKe");
        product52.setQuntity(22);
        products.add(product52);

        Product product53 = new Product();
        product53.setTitle("Prag, Tjeckien - Tavla");
        product53.setDescription(
                "Upptäck den historiska staden Prag med dess vackra arkitektur, charmiga kullerstensgator och livliga ölstugor. 100cm x 100cm");
        product53.setPrice(450);
        product53.setImageUrl("/017.jpg");
        product53.setPriceId("price_1OveVeF3bq9e58MhdpkK2tub");
        product53.setQuntity(12);
        products.add(product53);

        Product product54 = new Product();
        product54.setTitle("Agra, Indien - Tavla");
        product54.setDescription(
                "Besök den ikoniska Taj Mahal och upplev den rika historien och kulturen i Agra, en av Indiens mest fascinerande städer. 100cm x 100cm");
        product54.setPrice(450);
        product54.setImageUrl("/018.jpg");
        product54.setPriceId("price_1OveW6F3bq9e58MhigeZqUiW");
        product54.setQuntity(61);
        products.add(product54);

        return products;
    }

}
