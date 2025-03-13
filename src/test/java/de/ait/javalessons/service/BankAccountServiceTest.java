package de.ait.javalessons.service;

import de.ait.javalessons.model.*;
import de.ait.javalessons.repositories.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BankAccountServiceTest {
    @Value("${bank.min-balance:0.0}")
    private double minBalance;

    @Value("${max-withdrawal-amount:5000.0}")
    private double maxWithdrawalAmount;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    private BankAccount testAccount;

    private List<BankAccount> testBankAccounts;

    @BeforeEach
    void setUp() {
        testBankAccounts = bankAccountRepository.findAll();
        testAccount = bankAccountRepository.findAll().get(0);
    }
    /* 1.***********************************get a list of all accounts****************************************** */
    @Test
    void getAllBankAccountsTest() {

        int sizeOfList = testBankAccounts.size();
        assertEquals(sizeOfList, bankAccountService.getAllBankAccounts().size());

        assertEquals(testAccount.getId(), bankAccountService.getAllBankAccounts().get(0).getId());
        assertEquals(testAccount.getAccountNumber(), bankAccountService.getAllBankAccounts().get(0).getAccountNumber());
        assertEquals(testAccount.getOwnerName(), bankAccountService.getAllBankAccounts().get(0).getOwnerName());
        assertEquals(testAccount.getBalance(), bankAccountService.getAllBankAccounts().get(0).getBalance());

    }
    /* 2.***********************************find an account by its id****************************************** */
      @Test
    void findBankAccountByIdTestSuccessfully() {
        Optional<BankAccount> bankAccount = bankAccountService.findBankAccountById(testAccount.getId());
        assertEquals(testAccount.getAccountNumber(), bankAccount.get().getAccountNumber());
    }

    @Test
    void findBankAccountByIdTestFailed() {
        Long testId = 1000L;
        Optional<BankAccount> bankAccount = bankAccountService.findBankAccountById(testId);
        assertTrue(bankAccount.isEmpty());
    }
    /* 3.***********************************open an account****************************************** */
    @Test
    void saveNewBankAccount() {
        int oldSize = bankAccountService.getAllBankAccounts().size();
        BankAccount testAccountToSave = new BankAccount("1011", "Bob Neumann", 11500.0);


        BankAccount newBankAccount = bankAccountService.saveNewBankAccount(testAccountToSave);
        int newSize = bankAccountService.getAllBankAccounts().size();
        assertTrue(newSize == oldSize + 1);
        assertEquals(testAccountToSave.getAccountNumber(), newBankAccount.getAccountNumber());
    }
    /* 4.***********************************deposit amount into account****************************************** */


    @Test
    void deposit_shouldIncreaseBalance() {
        double initialBalance = testAccount.getBalance();
        double newBalance = bankAccountService.deposit(1000.0, testAccount.getId());

        assertEquals(initialBalance + 1000.0, newBalance);
    }

    @Test
    void deposit_shouldThrowIllegalArgumentException_whenAmountNotGreaterThan0() {
        IllegalArgumentException exception=assertThrows(IllegalArgumentException.class, () -> bankAccountService.deposit(-200.0, testAccount.getId()));
        assertEquals("Amount must be greater than 0", exception.getMessage());

    }
    /* 5.***********************************withdrawal from account****************************************** */


    @Test
    void withdraw_shouldDecreaseBalance() {
        double initialBalance = testAccount.getBalance();
        double newBalance = bankAccountService.withdraw(500.0, testAccount.getId());

        assertEquals(initialBalance - 500.0, newBalance);
    }

    @Test
    void withdtaw_shouldThrowIllegalArgumentException_WhenBankAccountNotFound() {
        IllegalArgumentException exception=assertThrows(IllegalArgumentException.class, () -> bankAccountService.withdraw(300.0, 1000L));
        assertEquals("Bank account not found", exception.getMessage());

    }

    //Нехватка средств
    @Test
    void withdraw_shouldThrowIllegalArgumentException_whenInsufficientFunds() {
        IllegalArgumentException exception= assertThrows(IllegalArgumentException.class, () -> bankAccountService.withdraw(testAccount.getBalance()+10.0, testAccount.getId()));
        assertEquals("Insufficient funds", exception.getMessage());
    }

    @Test
    void withdraw_shouldThrowIllegalArgumentException_whenAmountNotGreaterThan0() {
        IllegalArgumentException exception=assertThrows(IllegalArgumentException.class, () -> bankAccountService.withdraw(-200.0, testAccount.getId()));
        assertEquals("Amount must be greater than 0", exception.getMessage());

    }
    @Test
    void withdraw_shouldThrowIllegalArgumentException_whenAmountGreaterThanMaxWithdrawalAmount(){
        IllegalArgumentException exception=assertThrows(IllegalArgumentException.class, () -> bankAccountService.withdraw(5200.0, testAccount.getId()));
        assertEquals("Cannot withdraw more than " + maxWithdrawalAmount, exception.getMessage());
    }

    @Test
    void withdraw_shouldThrowIllegalArgumentException_WhenTheAmountLeftOnTheAccountWillBeLessThanTheAllowedAmount(){
        Double tooBigAmount = testAccount.getBalance()-minBalance+10.0;
        IllegalArgumentException exception=assertThrows(IllegalArgumentException.class, () -> bankAccountService.withdraw(tooBigAmount, testAccount.getId()));
        assertEquals("Balance cannot go below the minimum allowed", exception.getMessage());
    }


    /* 6.*****************************transfer from one account to another********************************** */

    @Test
    void transfer_shouldMoveFundsBetweenAccounts() {
        BankAccount recipient = bankAccountRepository.findAll().get(1);
        double senderInitialBalance = testAccount.getBalance();
        double recipientInitialBalance = recipient.getBalance();

        bankAccountService.transfer(testAccount.getId(), recipient.getId(), 500.0);

        assertEquals(senderInitialBalance - 500.0, bankAccountService.findBankAccountById(testAccount.getId()).get().getBalance());
        assertEquals(recipientInitialBalance + 500.0, bankAccountService.findBankAccountById(recipient.getId()).get().getBalance());
    }

//
    @Test
    void transfer_shouldThrowIllegalArgumentException_WhenTransferToTheSameAccount() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> bankAccountService.transfer(testAccount.getId(), testAccount.getId(), 500.0));
        assertEquals("Cannot transfer to the same account", exception.getMessage());
    }

    @Test
    void transfer_shouldThrowIllegalArgumentException_WhenAmountNotGreaterThan0() {
       BankAccount testAccount1=testBankAccounts.get(1);
        IllegalArgumentException exception=assertThrows(IllegalArgumentException.class, ()->bankAccountService.transfer(testAccount1.getId(), testAccount.getId(), 0.0));
        assertEquals("Amount must be greater than 0", exception.getMessage());
    }
    /*  if (amount > fromAccount.getBalance()) {
            throw new IllegalArgumentException("Insufficient funds");
        }*/
    @Test
    void transfer_shouldThrowIllegalArgumentException_WhenInsufficientFunds() {
        BankAccount testAccount1=testBankAccounts.get(1);
        IllegalArgumentException exception=assertThrows(IllegalArgumentException.class, ()->bankAccountService.transfer(testAccount1.getId(), testAccount.getId(), 100000.0));
        assertEquals("Insufficient funds", exception.getMessage());
    }

    /* ***********************************closeAccount****************************************** */
    @Test
    void closeAccount_shouldDeleteAccount_whenBalanceIsZero() {
        testAccount.setBalance(0);
        bankAccountRepository.save(testAccount);

        bankAccountService.closeAccount(testAccount.getId());

        assertFalse(bankAccountRepository.findById(testAccount.getId()).isPresent());
    }

    //Если счета нет в базе, метод должен выбросить IllegalArgumentException.
    @Test
    void closeAccount_shouldThrowException_whenAccountNotFound() {
        Long nonExistingId = 999L;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                bankAccountService.closeAccount(nonExistingId));

        assertEquals("Bank account not found", exception.getMessage());
    }

    //Если баланс не ноль, метод должен выбросить IllegalStateException
    @Test
    void closeAccount_shouldThrowException_whenBalanceIsNotZero() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () ->
                bankAccountService.closeAccount(testAccount.getId()));

        assertEquals("Cannot close an account with non-zero balance", exception.getMessage());
    }

    /* 8.***********************************updateOwnerName****************************************** */

    @Test
    void updateOwnerNameTest() {
        testAccount = bankAccountRepository.findAll().get(0);

        Long accountIdWhoseUsernameShouldBeChanged = testAccount.getId();
        String newOwnerName = "Alice Newname";

        bankAccountService.updateOwnerName(accountIdWhoseUsernameShouldBeChanged, newOwnerName);


        // Загружаем обновленный объект из базы
        BankAccount updatedAccount = bankAccountRepository.findById(accountIdWhoseUsernameShouldBeChanged).orElseThrow();

        // Проверяем обновленное значение
        assertEquals(newOwnerName, updatedAccount.getOwnerName());

    }

}
