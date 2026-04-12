package com.whnazv.productservice.application.service;

import com.whnazv.productservice.application.port.out.UpdateProductPort;
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
class UpdateProductServiceTest {

    @Mock
    UpdateProductPort updateProductPort;

    @InjectMocks
    UpdateProductService service;

    @Test
    void shouldUpdateProduct() {
        Product p = new Product();
        p.setId(1L);
        p.setTitle("Nuevo");

        when(updateProductPort.update(any(Product.class)))
                .thenReturn(Mono.just(p));

        Product result = service.update(1L, p).block();

        assertNotNull(result);
        assertEquals("Nuevo", result.getTitle());
        verify(updateProductPort).update(any(Product.class));
    }
}
