package de.ait.javalessons;

import de.ait.javalessons.model.*;
import de.ait.javalessons.repositories.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.web.client.*;
import org.springframework.http.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BankAccountIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    private BankAccount testAccount;
    @org.springframework.beans.factory.annotation.Autowired
    private de.ait.javalessons.service.BankAccountService bankAccountService;

    @BeforeEach
    void setUp() {
        testAccount = bankAccountRepository.findAll().get(0);
    }

    /* 1.***********************************get a list of all accounts****************************************** */

    @Test
    void getAllBankAccounts_shouldReturnAccounts() {
        ResponseEntity<BankAccount[]> response = restTemplate.getForEntity("/accounts", BankAccount[].class);
        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
    }

    /* 2.***********************************find an account by its id****************************************** */
    @Test
    void getBankAccountById_shouldReturnAccount() {
        ResponseEntity<BankAccount> response = restTemplate.getForEntity("/accounts/account?id=" + testAccount.getId(), BankAccount.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testAccount.getId(), response.getBody().getId());
    }

    /* 3.***********************************open an account****************************************** */
    @Test
    void createBankAccount_shouldSaveNewAccount() {
        ResponseEntity<BankAccount> response = restTemplate.postForEntity("/accounts?accountNumber=1011&ownerName=John Doe", null, BankAccount.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("1011", response.getBody().getAccountNumber());
        assertEquals("John Doe", response.getBody().getOwnerName());
        assertEquals(0.0, response.getBody().getBalance());
    }

    /* 4.***********************************deposit amount into account****************************************** */

    @Test
    void deposit_shouldIncreaseBalance() {
        double initialBalance = testAccount.getBalance();
        restTemplate.postForEntity("/accounts/" + testAccount.getId() + "/deposit?amount=1000", null, Void.class);

        BankAccount updatedAccount = bankAccountRepository.findById(testAccount.getId()).orElseThrow();
        assertEquals(initialBalance + 1000.0, updatedAccount.getBalance());
    }
    /* 5.***********************************withdrawal from account****************************************** */

    @Test
    void withdraw_shouldDecreaseBalance() {
        double initialBalance = testAccount.getBalance();
        restTemplate.postForEntity("/accounts/" + testAccount.getId() + "/withdraw?amount=500", null, Void.class);

        BankAccount updatedAccount = bankAccountRepository.findById(testAccount.getId()).orElseThrow();
        assertEquals(initialBalance - 500.0, updatedAccount.getBalance());
    }

    /* 6.*****************************transfer from one account to another********************************** */
    @Test
    void transfer_shouldMoveFundsBetweenAccounts() {

        BankAccount recipient = bankAccountRepository.findAll().get(1);
        double senderInitialBalance = testAccount.getBalance();
        double recipientInitialBalance = recipient.getBalance();


        bankAccountService.transfer(1L, 2L, 500.0);
        restTemplate.postForEntity("/accounts/" + testAccount.getId() + "/" + recipient.getId() + "/transfer?amount=500", null, Void.class);


        BankAccount updatedSender = bankAccountRepository.findById(testAccount.getId()).orElseThrow();
        BankAccount updatedRecipient = bankAccountRepository.findById(recipient.getId()).orElseThrow();


        assertEquals(senderInitialBalance - 500.0, updatedSender.getBalance());
        assertEquals(recipientInitialBalance + 500.0, updatedRecipient.getBalance());
    }

    /* 7.***********************************closeAccount****************************************** */
    @Test
    void closeAccount_shouldRemoveAccount_whenBalanceIsZero() {
        BankAccount accountToClose = bankAccountRepository.save(new BankAccount("9999", "Test User", 0.0));


        restTemplate.delete("/accounts/" + accountToClose.getId() + "/closeAccount?bankAccountId=" + accountToClose.getId());

        Optional<BankAccount> deletedAccount = bankAccountRepository.findById(accountToClose.getId());
        assertTrue(deletedAccount.isEmpty());


    }

    /* 8.***********************************updateOwnerName****************************************** */
    @Test
    void updateBankAccount_shouldChangeOwnerName() {
        String newName = "Updated Name";
        restTemplate.patchForObject("/accounts/" + testAccount.getId() + "/" + newName, null, Void.class);

        BankAccount updatedAccount = bankAccountRepository.findById(testAccount.getId()).orElseThrow();
        assertEquals(newName, updatedAccount.getOwnerName());
    }


}

