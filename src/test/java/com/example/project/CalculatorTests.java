/*
 * Copyright 2015-2018 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package com.example.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.example.project.Exceptions.FieldCannotBeEmptyException;
import com.example.project.Exceptions.InfitityException;
import com.example.project.Operations.Operations;

class CalculatorTests {

	@ParameterizedTest(name = "{0} / {1} = {2}")
	@MethodSource("validDataProvider")
	@DisplayName("TEST-1: Позитивные тесты")
	void dividePositiveTest(String first, String second, String expectedResult)
			throws NumberFormatException, InfitityException, FieldCannotBeEmptyException {
		assertEquals(expectedResult, Operations.divideString(first, second),
				() -> first + " / " + second + " should equal " + expectedResult);
	}

	@ParameterizedTest(name = "{0} / {1}")
	@MethodSource("zeroDataProvider")
	@DisplayName("TEST-2: Деление на ноль")
	void divideByZeroTest(String first, String second) {
		InfitityException exeption = assertThrows(InfitityException.class, () -> {
			Operations.divideString(first, second);
		});
		assertEquals("Нельзя делить на 0!", exeption.getMessage());
	}

	@ParameterizedTest(name = "{0} / {1}")
	@MethodSource("emptyDataProvider")
	@DisplayName("TEST-3: Пустое поле \"Делимое\" или \"Делитель\"")
	void validateIsEmptyTest(String first, String second) {
		FieldCannotBeEmptyException exeption = assertThrows(FieldCannotBeEmptyException.class, () -> {
			Operations.divideString(first, second);
		});
		assertEquals("Заполните поля \"Делимое\" и \"Делитель\"!", exeption.getMessage());
	}

	@Test
	@DisplayName("TEST-4: Число в поле \"Делимое\" слишком большое")
	void validateDividendIsInfinityTest() {
		String maxFloatPlusOne = Float.toString(Math.nextUp(Float.MAX_VALUE));
		InfitityException exeption = assertThrows(InfitityException.class, () -> {
			Operations.divideString(maxFloatPlusOne, "1");
		});
		assertEquals("Число в поле \"Делимое\" слишком большое.", exeption.getMessage());
	}

	@ParameterizedTest(name = "{0} / {1}")
	@MethodSource("invalidDataProvider")
	@DisplayName("TEST-7: Некорретный формат полей")
	void validateInvalidFloatTest(String first, String second) {
		NumberFormatException exeption = assertThrows(NumberFormatException.class, () -> {
			Operations.divideString(first, second);
		});
		assertEquals("Некорретный формат ввода.\nРазрешены только числовые значения.", exeption.getMessage());
	}

	static Stream<Arguments> validDataProvider() {
		String maxFloat = Float.toString(Float.MAX_VALUE);
		String minNormalFloat = Float.toString(Float.MIN_NORMAL);
		String maxNegativeFloat = Float.toString(-Float.MAX_VALUE);
		String minFloat = Float.toString(Float.MIN_VALUE);
		return Stream.of(
				Arguments.of("0", maxNegativeFloat, "-0.0"), 
				Arguments.of("5", "1E3", "0.005"),
				Arguments.of("-49", "200000", "-2.45E-4"), 
				Arguments.of("+8E2", "300000", "0.0026666666"),
				Arguments.of(maxNegativeFloat, "-1", maxFloat), 
				Arguments.of("-1", maxFloat, "-2.938736E-39"),
				Arguments.of(minFloat, "1", minFloat), 
				Arguments.of("-1", minNormalFloat, "-8.507059E37"),
				Arguments.of(minFloat, "10.0", "0.0"));

	}

	static Stream<Arguments> zeroDataProvider() {

		String zeroFloat = "1.4E-46";
		String minNormalFloat = Float.toString(Float.MIN_NORMAL);
		return Stream.of(
				Arguments.of(minNormalFloat, "0.0"), 
				Arguments.of("1", zeroFloat));

	}
	
	static Stream<Arguments> emptyDataProvider() {

		return Stream.of(
				Arguments.of("", "2.0"), 
				Arguments.of("1", ""));

	}
	
	static Stream<Arguments> invalidDataProvider() {

		return Stream.of(
				Arguments.of("0,5", "2"), 
				Arguments.of("4567", "4,5"),
				Arguments.of("qwerty", "10"),
				Arguments.of("15", "asd"),
				Arguments.of("5!","2"));

	}
}
