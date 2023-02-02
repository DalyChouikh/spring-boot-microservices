package dev.daly.productservice;

import dev.daly.productservice.dto.ProductRequest;
import dev.daly.productservice.dto.ProductResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class ProductResponseJsonTest {
    @Autowired
    private JacksonTester<ProductResponse> json;

    @Test
    public void productResponseSerializationTest() throws IOException {
        ProductResponse productResponse = getProductResponse();
        assertThat(json.write(productResponse)).isStrictlyEqualToJson("productResponse.json");
        assertThat(json.write(productResponse)).hasJsonPathStringValue("@.id");
        assertThat(json.write(productResponse)).extractingJsonPathStringValue("@.id").isEqualTo("1");
        assertThat(json.write(productResponse)).hasJsonPathStringValue("@.name");
        assertThat(json.write(productResponse)).extractingJsonPathStringValue("@.name").isEqualTo("Iphone");
        assertThat(json.write(productResponse)).hasJsonPathStringValue("@.description");
        assertThat(json.write(productResponse)).extractingJsonPathStringValue("@.description").isEqualTo("Apple");
        assertThat(json.write(productResponse)).hasJsonPathNumberValue("@.price");
        assertThat(json.write(productResponse)).extractingJsonPathNumberValue("@.price").isEqualTo(1200.0);
    }


    @Test
    public void productResponseDeserializationTest() throws IOException {
        String expected = """
                {
                    "id": 1,
                    "name": "Iphone",
                    "description": "Apple",
                    "price": 1200.0
                }
                """;
        assertThat(json.parse(expected)).isEqualTo(new ProductResponse("1", "Iphone", "Apple", BigDecimal.valueOf(1200.0)));
        assertThat(json.parseObject(expected).getId()).isEqualTo("1");
        assertThat(json.parseObject(expected).getName()).isEqualTo("Iphone");
        assertThat(json.parseObject(expected).getDescription()).isEqualTo("Apple");
        assertThat(json.parseObject(expected).getPrice()).isEqualTo(BigDecimal.valueOf(1200.0));
    }

    private ProductResponse getProductResponse() {
        return ProductResponse.builder()
                .id("1")
                .name("Iphone")
                .description("Apple")
                .price(BigDecimal.valueOf(1200.0))
                .build();
    }
}
