package com.whnazv.productservice.application.service;

import com.whnazv.productservice.application.port.out.LoadProductPort;
import com.whnazv.productservice.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetProductServiceTest {

    @Mock
    LoadProductPort loadProductPort;

    @InjectMocks
    GetProductService service;

    @Test
    void shouldFindById() {
        Product p = new Product();
        p.setId(1L);

        when(loadProductPort.findById(1L)).thenReturn(Mono.just(p));

        Product result = service.findById(1L).block();

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void shouldFindAll() {
        Product a = new Product();
        Product b = new Product();

        when(loadProductPort.findAll()).thenReturn(Flux.just(a, b));

        List<Product> result = service.findAll().collectList().block();

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
