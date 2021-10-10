package com.erp.doa;

import java.util.List;

import org.springframework.stereotype.Service;

import com.erp.model.Customer;

@Service
public interface CustomerDoa {

	int add(Customer customer);

	Customer findByUserIdAndPassword(Customer customer);

	Customer findById(Integer id);

	/**
	 * @return
	 */
	List<Customer> allUsers();

	/**
	 * @param id
	 */
	void delete(Integer id);

	void updateUser(Customer customer);
}
