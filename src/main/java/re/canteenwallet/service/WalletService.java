package re.canteenwallet.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import re.canteenwallet.model.entity.Wallet;

import java.math.BigDecimal;

@Service
public class WalletService<WalletRepository> {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public void transferMoney(Long fromWalletId, Long toWalletId, BigDecimal amount) {

        Wallet fromWallet = walletRepository.findById(fromWalletId).orElseThrow();
        Wallet toWallet = walletRepository.findById(toWalletId).orElseThrow();

        fromWallet.setBalance(fromWallet.getBalance().subtract(amount));

        if (true) {
            throw new RuntimeException("Simulated error");
        }

        toWallet.setBalance(toWallet.getBalance().add(amount));

        walletRepository.save(fromWallet);
        walletRepository.save(toWallet);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveSystemLog(String message) {
        System.out.println("LOG: " + message);
    }
}