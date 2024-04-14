package com.apitest.TestSping.database;

import com.apitest.TestSping.Reponsitory.ProductReponsitory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration

public class DataProduct {
    //logger để kiểm tra tính đúng đắn của giá trị in ra.
    private static final Logger logger = LoggerFactory.getLogger(DataProduct.class);
    @Bean // dể chạy khi app mới bắt đầu chạy
    // fit dữ liệu ban đầu để test
    CommandLineRunner initDataProduct(ProductReponsitory productReponsitory){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
               // Product productA = new Product("App",2020,"",2000);
             /*   Product productB = new Product("web",2021,"",999);
                Product c = new Product("app",2023,"",9999);
                logger.info("insert data:"+ productReponsitory.<Product>save(c));
                logger.info("insert data:"+ productReponsitory.<Product>save(productB));*/
            }
        };
    }
}
/*
docker run -d --rm --name mysql-spring-boot-tutorial \
-e MYSQL_ROOT_PASSWORD=trongkhang \
-e MYSQL_USER=root \
-e MYSQL_PASSWORD=trongkhang \
-e MYSQL_DATABASE=testspring \
-p 3309:3306\
--volume mysql-spring-boot-tutorial-volume:var/lib/mysql\
mysql:latest
mysql-h localhost -P 3309 --protocol=tcp -u root -p
*/