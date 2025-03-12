package de.ait.javalessons.service;

import de.ait.javalessons.model.BankAccount;
import de.ait.javalessons.repositories.BankAccountRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    @Value("${bank.min-balance:0.0}")
    private double minBalance;

    @Value("${max-withdrawal-amount:5000.0}")
    private double maxWithdrawalAmount;


    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;

        List<BankAccount> accounts = List.of(
                new BankAccount("1001", "Alice Johnson", 1500.0),
                new BankAccount("1002", "Bob Smith", 2500.0),
                new BankAccount("1003", "Charlie Brown", 3500.0),
                new BankAccount("1004", "David White", 4500.0),
                new BankAccount("1005", "Emma Green", 5500.0),
                new BankAccount("1006", "Frank Black", 6500.0),
                new BankAccount("1007", "Grace Adams", 7500.0),
                new BankAccount("1008", "Henry Scott", 8500.0),
                new BankAccount("1009", "Isabella Lee", 9500.0),
                new BankAccount("1010", "Jack Wilson", 10500.0)
        );
        bankAccountRepository.saveAll(accounts);

    }

    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public Optional<BankAccount> findBankAccountById(Long id) {
        return bankAccountRepository.findById(id);
    }

    public BankAccount saveNewBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }


    @Transactional
    public double deposit(double amount, Long bankAccountId) {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Bank account not found"));
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        return bankAccountRepository.save(bankAccount).getBalance();
    }

    @Transactional
    public double withdraw(double amount, Long bankAccountId) {
        de.ait.javalessons.model.BankAccount bankAccount = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Bank account not found"));
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        if (amount > maxWithdrawalAmount) {
            throw new IllegalArgumentException("Cannot withdraw more than " + maxWithdrawalAmount);
        }
        if (amount > bankAccount.getBalance()) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        if (bankAccount.getBalance() - amount < minBalance) {
            throw new IllegalArgumentException("Balance cannot go below the minimum allowed");
        }
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        return bankAccountRepository.save(bankAccount).getBalance();
    }

    @Transactional
    public void transfer(Long fromAccountId, Long toAccountId, double amount) {
        if (fromAccountId.equals(toAccountId)) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }
        BankAccount fromAccount = bankAccountRepository.findById(fromAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Sender account not found"));
        BankAccount toAccount = bankAccountRepository.findById(toAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Receiver account not found"));

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        if (amount > fromAccount.getBalance()) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        bankAccountRepository.save(fromAccount);
        bankAccountRepository.save(toAccount);
    }

    @Transactional
    public void closeAccount(Long bankAccountId) {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Bank account not found"));
        if (bankAccount.getBalance() != 0) {
            throw new IllegalStateException("Cannot close an account with non-zero balance");
        }
        bankAccountRepository.delete(bankAccount);
    }
}
