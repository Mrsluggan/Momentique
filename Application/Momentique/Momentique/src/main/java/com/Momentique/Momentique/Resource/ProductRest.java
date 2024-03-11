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

        // Bara för testing, Om ingen mockdata finns, ta bort .findall() och kör dessa
        // två en gång. Sedan kommenterabort.
        // List<Product> mockProducts = generateMockProducts();
        // Iterable<Product> restult = productRepository.saveAll(mockProducts);

        // Byt till detta när vi har riktigt data
        Iterable<Product> restult = productRepository.findAll();
        return ResponseEntity.ok(restult);
    }

    @GetMapping("products/{id}")
    public ResponseEntity<Optional<Product>> findProductById(@PathVariable("id") Long id) {
        Optional<Product> restult = productRepository.findById(id);
        return ResponseEntity.ok(restult);
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


    // Gör så att våran frontend kan få tag på bilderna på servern. 
    @GetMapping("/images/products/compressed/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
        Resource resource = new ClassPathResource("static/images/products/compressed/" + imageName);
        byte[] imageBytes = Files.readAllBytes(Path.of(resource.getURI()));
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }



    //Mockdata, gör saker lite finare
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

        return products;
    }

}
