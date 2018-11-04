/**
 * 
 */
package com.example.project.Exceptions;

/**
 * Исключение <code>InfitityException</code> кидается, если
 * появляется Infinity значение
 * @author Marina Kravchenko
 *
 */
public class InfitityException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Конструктор
	 */
	public InfitityException() {
		super();
	}

	/**
	 * Конструктор для генерации исключения с текстом сообщения
	 * @param message - текст сообщения
	 */
	public InfitityException(String message) {
		super(message);
	}
	
	

}
