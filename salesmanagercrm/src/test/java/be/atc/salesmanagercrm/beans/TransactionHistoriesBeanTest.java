package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.entities.TransactionHistoriesEntity;
import be.atc.salesmanagercrm.entities.UsersEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class TransactionHistoriesBeanTest {

    @Getter
    @Setter
    private TransactionHistoriesBean transactionHistoriesBean;

    @BeforeEach
    public void init() {
        log.info("Appel avant chaque test");
        transactionHistoriesBean = new TransactionHistoriesBean();
    }

    @AfterEach
    public void after() {
        log.info("Appel après chaque test");
        transactionHistoriesBean = null;
    }

    @Test
    void findAllByIdUserAndByIdTransactionShouldReturnTrue() {

        // Mettre un id correct
        int idTransaction = 1;
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1);

        List<TransactionHistoriesEntity> transactionHistoriesEntities = transactionHistoriesBean.findAllByIdUserAndByIdTransaction(idTransaction, usersEntity);


        boolean test = !transactionHistoriesEntities.isEmpty();

        log.info("Le test vaut : " + test);

        assertThat(test).isEqualTo(true);
    }

    @Test
    void findAllByIdUserAndByIdTransactionShouldReturnFalse() {

        // Mettre un id incorrect
        int idTransaction = 1540;
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1);

        List<TransactionHistoriesEntity> transactionHistoriesEntities = transactionHistoriesBean.findAllByIdUserAndByIdTransaction(idTransaction, usersEntity);


        boolean test = !transactionHistoriesEntities.isEmpty();

        log.info("Le test vaut : " + test);

        assertThat(test).isEqualTo(false);
    }
}