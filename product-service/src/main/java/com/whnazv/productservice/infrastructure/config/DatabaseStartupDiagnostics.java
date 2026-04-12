package com.whnazv.productservice.infrastructure.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class DatabaseStartupDiagnostics {

    private final ConnectionFactory cf;

    public DatabaseStartupDiagnostics(ConnectionFactory cf) {
        this.cf = cf;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run() {

        Mono.from(cf.create())
            .flatMapMany(conn ->
                Flux.from(
                    conn.createStatement(
                        "SELECT table_name FROM information_schema.tables WHERE table_schema = 'product_service'"
                    ).execute()
                )
                .flatMap(result ->
                    result.map((row, meta) -> row.get("table_name", String.class))
                )
                .doOnNext(t -> log.info("Tabla en product_service: {}", t))
                .doFinally(signal -> conn.close())
            )
            .subscribe();

        Mono.from(cf.create())
            .flatMapMany(conn ->
                Flux.from(
                    conn.createStatement("SELECT * FROM product_service.products").execute()
                )
                .flatMap(r ->
                    r.map((row, meta) ->
                        "Product: id=" + row.get("id") + ", title=" + row.get("title")
                    )
                )
                .doOnNext(log::info)
                .doFinally(signal -> conn.close())
            )
            .subscribe();
    }
}
