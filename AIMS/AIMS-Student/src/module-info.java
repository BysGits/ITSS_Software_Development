module module_info {
	exports entity.cart;
	exports controller;
	exports entity.media;
	exports entity.user;
	exports entity.invoice;
	exports views.screen.cart;
	exports views.screen;
	exports views.screen.invoice;
	exports subsystem;
	exports views.screen.home;
	exports subsystem.interbank;
	exports views.screen.payment;
	exports entity.shipping;
	exports entity.payment;
	exports entity.db;
	exports views.screen.shipping;
	exports utils;
	exports views.screen.popup;
	exports entity.order;
	exports common.exception;

	requires java.logging;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	requires org.junit.jupiter.api;
	requires junit;
	requires org.junit.jupiter.params;
}