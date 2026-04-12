package com.whnazv.productservice.application.service;

import com.whnazv.productservice.application.port.out.SaveProductPort;
import com.whnazv.productservice.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateProductServiceTest {

    @Mock
    SaveProductPort saveProductPort;

    @InjectMocks
    CreateProductService service;

    @Test
    void shouldCreateProduct() {
        Product p = new Product();
        p.setTitle("Libro");

        when(saveProductPort.save(p)).thenReturn(Mono.just(p));

        Product result = service.create(p).block();

        assertNotNull(result);
        assertEquals("Libro", result.getTitle());
        verify(saveProductPort).save(p);
    }
}
