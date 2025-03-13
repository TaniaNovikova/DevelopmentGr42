package de.ait.javalessons.controller;

import de.ait.javalessons.model.*;
import de.ait.javalessons.service.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;



@Slf4j
@RestController
@RequestMapping("/accounts")
public class BankAccountController {

    private BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }
    /* 1.***********************************get a list of all accounts****************************************** */

    @GetMapping
    public ResponseEntity<List<BankAccount>> getAllBankAccounts() {
        java.util.List<BankAccount> bankAccounts = bankAccountService.getAllBankAccounts();
        log.info("Found {} bank accounts", bankAccounts.size());
        return ResponseEntity.ok(bankAccounts);
    }
    /* 2.***********************************find an account by its id****************************************** */

    @GetMapping("/account") // Получение одного аккаунта по ID GET /account?id=1
    public ResponseEntity<BankAccount> getBankAccountById(@RequestParam Long id) {
        log.info("Getting bank account with id {}", id);
        return bankAccountService.findBankAccountById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(org.springframework.http.HttpStatus.NOT_FOUND).body(null));
    }
    /* 3.***********************************open an account****************************************** */

    @PostMapping
    public ResponseEntity<BankAccount> createBankAccount(@RequestParam String accountNumber,
                                                         @RequestParam String ownerName) {
        BankAccount bankAccount = new BankAccount(accountNumber, ownerName, 0.0);
        BankAccount savedBankAccount = bankAccountService.saveNewBankAccount(bankAccount);
        if (savedBankAccount == null) {
            log.error("Error saving bank account");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        log.info("Saved bank account with id {}", savedBankAccount.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBankAccount);
    }

    /* 4.***********************************deposit amount into account****************************************** */


    @PostMapping("/{id}/deposit")
    public ResponseEntity<Double> deposit(@PathVariable Long id, @RequestParam double amount) {
        log.info("Depositing {} to bank account with id {}", amount, id);
        return ResponseEntity.ok(bankAccountService.deposit(amount, id));
    }
    /* 5.***********************************withdrawal from account****************************************** */

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<Double> withdraw(@PathVariable Long id, @RequestParam double amount) {
        log.info("Withdrawing {} from bank account with id {}", amount, id);
        return ResponseEntity.ok(bankAccountService.withdraw(amount, id));
    }
    /* 6.*****************************transfer from one account to another********************************** */

    @PostMapping("/{fromAccountId}/{toAccountId}/transfer")
    public void transfer(@RequestParam Long fromAccountId, @RequestParam Long toAccountId, @RequestParam double amount) {
        bankAccountService.transfer(fromAccountId, toAccountId, amount);
    }
    /* 7.***********************************closeAccount****************************************** */

    //закрытие счета по id, с проверкой: удаляет только в том случае, если баланс равен 0
    @DeleteMapping("/{id}/closeAccount")
    public void closeAccount(@RequestParam Long bankAccountId) {
        bankAccountService.closeAccount(bankAccountId);
    }
    /* 8.***********************************updateOwnerName****************************************** */

    @PatchMapping("/{accountId}/{newName}")
    public void updateBankAccount(@PathVariable Long accountId, @PathVariable String newName) {
        bankAccountService.updateOwnerName(accountId, newName);
    }
}
