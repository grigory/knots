package org.amse.yaroslavtsev.practice.knots.model;

/**
 *
 *	Класс для исключений, используемых в программе
 *
 *	@author Grigory Yaroslavtsev
 *
 */

public class KnotException extends RuntimeException {
	
	private boolean myCritical = false;

	/**
	 *
	 *	Конструктор для исключения	
	 *
	 *	@param message текст сообщения
	 *
	 */

	public KnotException(String message) {
		super(message);
	}	

	/**
	 *
	 *	Конструктор для исключения	
	 *
	 *	@param message текст сообщения
	 *	@param isCritical является ли исключение критическим
	 *
	 */

	public KnotException(String message, boolean isCritical) {
		super(message);
		myCritical = isCritical;
	}

	/**
	 *
	 *	Проверка, является ли исключение критическим
	 *
	 *	@return true, если исключение критическое, false в противном случае
	 *	
	 */


	public boolean isCritical() {
		return myCritical;
	}
}
