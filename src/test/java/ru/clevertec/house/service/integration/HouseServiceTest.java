package ru.clevertec.house.service.integration;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.clevertec.house.dto.HouseRequest;
import ru.clevertec.house.dto.HouseResponse;
import ru.clevertec.house.service.HouseService;
import ru.clevertec.house.service.TestApplicationRunner;
import ru.clevertec.house.service.impl.HouseRequestTestData;


import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@ActiveProfiles("test")
@Transactional
@Sql({
        "classpath:sql/InitDB.sql"
})
@SpringBootTest(classes = TestApplicationRunner.class)
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class HouseServiceTest {

    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14");

    @BeforeAll
    static void runContainer() {
        container.start();
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
    }

    private final HouseService houseService;

    @Test
    void multithreadingTest() throws ExecutionException, InterruptedException {

        HouseRequest houseRequest = HouseRequestTestData.builder().build().getHouseRequest();
        HouseRequest updateHouseRequest = HouseRequestTestData.builder().city("New").build().getHouseRequest();

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        Callable<UUID> saveCallable = () -> houseService.save(houseRequest);
        Future<UUID> uuidFuture = executorService.submit(saveCallable);
        UUID uuid = uuidFuture.get();
        Callable<HouseResponse> getByIDCallable = () -> houseService.getById(uuid);
        Callable<UUID> updateCallable = () -> houseService.save(updateHouseRequest);
        Runnable deleteRunnable = () -> houseService.delete(uuid);
        Future<HouseResponse> houseResponseFuture = executorService.submit(getByIDCallable);
        executorService.submit(updateCallable);
        executorService.submit(deleteRunnable);
//        assertNotNull(houseResponseFuture.get());
    }
}
