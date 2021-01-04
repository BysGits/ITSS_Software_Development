package controller;

import java.io.IOException;

import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import subsystem.interbank.InterbankSubsystemController;

public class reset {
	
	private static InterbankSubsystemController crtl = new InterbankSubsystemController();


	public static void main(String[] args) throws IOException {
		CreditCard card = new CreditCard("121319_group2_2020","Group 2",228,"1125");
		PaymentTransaction trans = crtl.reset(card);
		System.out.println(trans.getBalance());

	}

}
