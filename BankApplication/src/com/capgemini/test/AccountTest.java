package com.capgemini.test;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertEquals;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;
import static org.mockito.Mockito.when;
public class AccountTest {

	@Mock
	AccountRepository accountRepository;
	
	@Mock
	Account account;
	AccountService accountService;
	
	
	@Before
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		accountService = new AccountServiceImpl(accountRepository);
		
		account.setAccountNumber(101);
		account.setAmount(5000);
		
	}

	/*
	 * create account
	 * 1. when the amount is less than 500 system should throw exception
	 * 2. when the valid info is passed account should be created successfully
	 */
	
	@Test(expected=com.capgemini.exceptions.InsufficientBalanceException.class)
	public void whenTheAmountIsLessThan500SystemShouldThrowException() throws InsufficientInitialBalanceException
	{
		accountService.createAccount(101, 400);
	}
	
	@Test
	public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsufficientInitialBalanceException{
		Account account = new Account();
		
		when(accountRepository.save(account)).thenReturn(true);
		
		assertEquals(account, accountService.createAccount(101, 5000));
		
	}
	
	/*
	 * show balance
	 * 1. when the valid info is passed account should show balance
	 * 2. when the invalid info is passed system must throw exception
	 */
	
	@Test
	public void whenTheValidInfoIsPassedAccountShouldShowBalance() throws InvalidAccountNumberException
	{
		
		
		when(accountRepository.searchAccount(101)).thenReturn(account);
		
		assertEquals(accountService.showBalance(101),5000);
		
		
	}
	
	@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
	public void whenTheInvalidInfoIsPassedAccountShouldThrowException() throws InvalidAccountNumberException{
	
		when(accountRepository.searchAccount(999)).thenReturn(null);
		accountService.showBalance(999);
		
		
	}
	
	/*withdraw amount
	1. when amount is greater than zero, amount can be withdrawn
	2. when amount is zero, system must throw exception
	*/
	
}
