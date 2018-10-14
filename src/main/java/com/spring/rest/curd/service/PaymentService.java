package com.spring.rest.curd.service;

import com.spring.rest.curd.dto.PaymentResponse;
import com.spring.rest.curd.model.Payment;

public interface PaymentService {
	public PaymentResponse doPayment(Payment payment);
	public PaymentResponse getAllTransaction(String vendor);

}
