package com.whnazv.productservice.application.service;

import com.whnazv.productservice.application.port.out.DeleteProductPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteProductServiceTest {

    @Mock
    DeleteProductPort deleteProductPort;

    @InjectMocks
    DeleteProductService service;

    @Test
    void shouldDeleteProduct() {
        when(deleteProductPort.delete(1L)).thenReturn(Mono.empty());

        service.delete(1L).block();

        verify(deleteProductPort).delete(1L);
    }
}
