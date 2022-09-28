package com.getir.readingisgood.common;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
@Sql({"classpath:schema.sql", "classpath:data.sql"})
public abstract class AbstractIntegrationTest {
}
