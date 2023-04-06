package com.biaf.exception;

public class OutOfStockException extends RuntimeException{
	
	public OutOfStockException(String message) { //주문수량이 현재재고 수 보다 클 경우 주문되지 않음
		super(message);
	}

}
