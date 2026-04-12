package com.whnazv.productservice.infrastructure.adapter.in;

import com.whnazv.productservice.application.port.in.CreateProductUseCase;
import com.whnazv.productservice.application.port.in.DeleteProductUseCase;
import com.whnazv.productservice.application.port.in.GetProductUseCase;
import com.whnazv.productservice.application.port.in.UpdateProductUseCase;
import com.whnazv.productservice.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

class ProductControllerTest {

    private CreateProductUseCase createUseCase;
    private GetProductUseCase getUseCase;
    private UpdateProductUseCase updateUseCase;
    private DeleteProductUseCase deleteUseCase;

    private WebTestClient client;

    @BeforeEach
    void setUp() {
        createUseCase = mock(CreateProductUseCase.class);
        getUseCase = mock(GetProductUseCase.class);
        updateUseCase = mock(UpdateProductUseCase.class);
        deleteUseCase = mock(DeleteProductUseCase.class);

        ProductController controller = new ProductController(
                createUseCase,
                getUseCase,
                updateUseCase,
                deleteUseCase
        );

        client = WebTestClient.bindToController(controller).build();
    }

    @Test
    void shouldCreateProduct() {
        Product p = new Product();
        p.setId(1L);
        p.setTitle("Libro");

        when(createUseCase.create(any(Product.class)))
                .thenReturn(Mono.just(p));

        client.post()
                .uri("/products")
                .contentType(APPLICATION_JSON)
                .bodyValue("{\"title\":\"Libro\"}")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.title").isEqualTo("Libro");

        verify(createUseCase).create(any(Product.class));
    }

    @Test
    void shouldFindById() {
        Product p = new Product();
        p.setId(1L);
        p.setTitle("Uno");

        when(getUseCase.findById(1L)).thenReturn(Mono.just(p));

        client.get()
                .uri("/products/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.title").isEqualTo("Uno");

        verify(getUseCase).findById(1L);
    }

    @Test
    void shouldFindAll() {
        Product a = new Product();
        a.setId(1L);
        a.setTitle("A");

        Product b = new Product();
        b.setId(2L);
        b.setTitle("B");

        when(getUseCase.findAll()).thenReturn(Flux.just(a, b));

        client.get()
                .uri("/products")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].id").isEqualTo(1)
                .jsonPath("$[1].id").isEqualTo(2);

        verify(getUseCase).findAll();
    }

    @Test
    void shouldUpdateProduct() {
        Product p = new Product();
        p.setId(1L);
        p.setTitle("Nuevo");

        when(updateUseCase.update(eq(1L), any(Product.class)))
                .thenReturn(Mono.just(p));

        client.put()
                .uri("/products/1")
                .contentType(APPLICATION_JSON)
                .bodyValue("{\"title\":\"Nuevo\"}")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.title").isEqualTo("Nuevo");

        verify(updateUseCase).update(eq(1L), any(Product.class));
    }

    @Test
    void shouldDeleteProduct() {
        when(deleteUseCase.delete(1L)).thenReturn(Mono.empty());

        client.delete()
                .uri("/products/1")
                .exchange()
                .expectStatus().isOk();

        verify(deleteUseCase).delete(1L);
    }
}
