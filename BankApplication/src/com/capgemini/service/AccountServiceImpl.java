package com.capgemini.service;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {
	
	/* (non-Javadoc)
	 * @see com.capgemini.service.AccountService#createAccount(int, int)
	 */
	
	AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository)
	{
		this.accountRepository=accountRepository;
	}
	@Override
	public Account createAccount(int accountNumber,int amount) throws InsufficientInitialBalanceException
	{
		if(amount<500)
		{
			throw new InsufficientInitialBalanceException();
		}
		
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		account.setAmount(amount);
		
		if(accountRepository.save(account))
		{
			return account;
		}
		
		return null;
		
	}
	@Override
	public int showBalance(int accountNumber) throws InvalidAccountNumberException
	{
		Account account = accountRepository.searchAccount(accountNumber);
		if(account!=null){
		int bal= account.getAmount();
		System.out.println(bal);
		return bal;
		}
		
		else
		{
			throw new InvalidAccountNumberException();
		}
	}
	@Override
	public int withdrawAmount(int accountNumber, int amount) throws InsufficientBalanceException{
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int depositAmount(int accountNumber, int amount) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String fundTransfer(int accountNumber, int amount) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
