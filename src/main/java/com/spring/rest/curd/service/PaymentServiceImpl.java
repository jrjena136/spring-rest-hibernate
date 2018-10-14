package com.spring.rest.curd.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest.curd.dao.PaymentDao;
import com.spring.rest.curd.dto.PaymentResponse;
import com.spring.rest.curd.model.Payment;

@Service
@Transactional
public class PaymentServiceImpl  implements PaymentService{
	@Autowired
	private PaymentDao dao;

	@Override
	public PaymentResponse doPayment(Payment payment) {
		PaymentResponse response = new PaymentResponse();
		String message = null;
		try{
			payment.setPaymentDate(new Date());
			message= dao.payNow(payment);
			response.setStatus("success");
			response.setMessage(message);
			response.setTransactionDate(new SimpleDateFormat("dd/mm/yyy HH:mm:ss a").format(new Date()));
		}catch(Exception ex){
			response.setStatus("failure");
			response.setMessage(message);
			response.setTransactionDate(new SimpleDateFormat("dd/mm/yyy HH:mm:ss a").format(new Date()));
		}
		return response;
	}

	@Override
	public PaymentResponse getAllTransaction(String vendor) {
		List<Payment> payments = dao.getTransactionDetails(vendor);
		PaymentResponse response = new PaymentResponse();
		if(!payments.isEmpty()){
			response.setPayments(payments);
		}
		else{
			response.setPayments(null);
		}
		return response;
	}

}
