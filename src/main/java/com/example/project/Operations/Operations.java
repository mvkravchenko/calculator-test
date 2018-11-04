/**
 * 
 */
package com.example.project.Operations;

import com.example.project.Exceptions.FieldCannotBeEmptyException;
import com.example.project.Exceptions.InfitityException;

/**
 * Класс для арифметических операций
 * @author Marina Kravchenko
 *
 */
public class Operations {
	
	/**
	 * Метод для деления 2х чисел, представленных строками
	 * @param dividend - делимое 
	 * @param divisor - делитель
	 * @return результат деления в виде строки
	 * @throws InfitityException - если делимое или делитель  - бесконечно большие числа (положительные или отрицательные).
	 * Или при попытке делить на 0
	 * @throws NumberFormatException - если <code>dividend</code> и/или
	 *  <code>divisor</code> не могут быть преобразованы по float числа
	 * @throws FieldCannotBeEmptyException - если <code>dividend</code> и/или
	 * <code>divisor</code> - пустые строки
	 */
	public static String divideString(String dividend, String divisor) throws InfitityException, NumberFormatException, FieldCannotBeEmptyException {
		float dividentFloat;
		float divisorFloat;
		float result;
		
		if (dividend.isEmpty() || divisor.isEmpty()) {
			throw new FieldCannotBeEmptyException("Заполните поля \"Делимое\" и \"Делитель\"!");
		} 
		
		try {
			dividentFloat = Float.parseFloat(dividend);
			divisorFloat = Float.parseFloat(divisor);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Некорретный формат ввода.\nРазрешены только числовые значения.");
		}
		
		if (divisorFloat == 0) {
			throw new InfitityException("Нельзя делить на 0!");
		}
		
		if (Float.isInfinite(dividentFloat)) {
			throw new InfitityException("Число в поле \"Делимое\" слишком большое.");
		} 
		
		if (Float.isInfinite(divisorFloat)) {
			throw new InfitityException("Число в поле \"Делитель\" слишком большое.");
		}
		
		result = dividentFloat/divisorFloat;
		
		if (Float.isInfinite(result)) {
			throw new InfitityException("Число в поле \"Результат\" слишком большое.");
		}
		
		return Float.toString(result);
	}
}
