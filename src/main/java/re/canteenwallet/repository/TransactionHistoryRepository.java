package re.canteenwallet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionHistoryRepository<TransactionHistory>
        extends JpaRepository<TransactionHistory, Long> {

    Page<TransactionHistory> findByWalletId(Long walletId, Pageable pageable);

    @Query("SELECT t FROM TransactionHistory t WHERE t.amount > :amount")
    List<TransactionHistory> findLargeTransactions(@Param("amount") BigDecimal amount);
}