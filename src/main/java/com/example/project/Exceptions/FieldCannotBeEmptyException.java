/**
 * 
 */
package com.example.project.Exceptions;

/**
 * Исключение <code>FieldCannotBeEmptyException</code> кидается, если
 * обязательное для заполнения поле передано пустым
 * 
 * @author Marina Kravchenko
 *
 */
public class FieldCannotBeEmptyException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Конструктор
	 */
	public FieldCannotBeEmptyException() {
		super();
	}

	/**
	 * Конструктор для генерации исключения с текстом сообщения
	 * 
	 * @param message
	 *            - текст сообщения
	 */
	public FieldCannotBeEmptyException(String message) {
		super(message);
	}

}
