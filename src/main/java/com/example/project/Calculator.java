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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.example.project.Exceptions.FieldCannotBeEmptyException;
import com.example.project.Exceptions.InfitityException;
import com.example.project.Operations.Operations;

/**
 * Класс представляет собой калькулятор, реализует интерфейс {@link ActionListener}
 * @author Marina Kravchenko
 * 
 */
public class Calculator implements ActionListener {

	JFrame frame;
	JButton actionButton;
	JTextField tfDividend, tfDivisor, tfResult;
	JLabel lDividend, lDivisor, label, lResult;

	final int FRAME_WIDTH = 300, FRAME_HEIGHT = 355;
	final int FIRST_ROW = 30, SHIFT = 30;
	final int LABEL_POS = 10, FIELD_POS = 100;
	final int FIELD_WIDTH = 150, FIELD_HEIGHT = 20;
	final int LABEL_WIDTH=100;
	final int BUTTON_HEIGHT=30;

	/*
	 * Конструктор
	 */
	public Calculator() {
	}

	/**
	 * Метод собирает калькулятор из отдельных элементов
	 * 
	 */
	public void initialize() {
		
		frame = generateFrame("Калькулятор", FRAME_WIDTH, FRAME_HEIGHT);

		lDividend = generateLabel("Делимое:", LABEL_POS, FIRST_ROW);
		tfDividend = generateTextField(FIELD_POS, FIRST_ROW);

		label = generateLabel("разделить на", FIELD_POS, FIRST_ROW + SHIFT);

		lDivisor = generateLabel("Делитель:", LABEL_POS, FIRST_ROW + 2*SHIFT);
		tfDivisor = generateTextField(FIELD_POS, FIRST_ROW + 2*SHIFT);

		actionButton = generateButton("=", FIELD_POS, FIRST_ROW + 3*SHIFT);

		lResult = generateLabel("Результат:", LABEL_POS, FIRST_ROW + 4*SHIFT+10);
		tfResult = generateTextField(FIELD_POS, FIRST_ROW + 4*SHIFT+10);
		tfResult.setEditable(false);
		
		frame.setLocation(500, 200);
		frame.setVisible(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			//пытаемся выполнить деление содежимого строк, результат помещаем в поле Результат
			this.tfResult.setText(Operations.divideString(this.tfDividend.getText(), this.tfDivisor.getText()));
		//в случае исключений выводим сообщение об ошибке
		} catch (FieldCannotBeEmptyException e) {
			JOptionPane.showMessageDialog(frame, e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
		} catch (InfitityException e) {
			JOptionPane.showMessageDialog(frame, e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(frame, e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Генерирует объект кнопки типа {@link JButton} по входящим параметрам
	 * Добавляет кнопку во фрейм калькулятора {@link Calculator}
	 * @param title - текстовый лэйбл кнопки
	 * @param x - x-координата левого верхнего угла кнопки
	 * @param y - y-координата левого верхнего угла кнопки
	 * @return объект типа {@link JButton}, добавленный во <code>frame</code>
	 */
	public JButton generateButton(String title, int x, int y) {
		JButton button = new JButton(title);
		button.addActionListener(this);
		button.setBounds(x, y, LABEL_WIDTH, BUTTON_HEIGHT);
		frame.add(button);

		return button;
	}

	/**
	 * Генерирует текстовое поле типа {@link JTextField} по входящим параметрам
	 * Добавляет поле во фрейм калькулятора {@link Calculator}
	 * @param x - x-координата левого верхнего угла поля
	 * @param y - x-координата левого верхнего угла поля
	 * @return объект типа {@link JTextField}, добавленный во <code>frame</code>
	 */
	public JTextField generateTextField(int x, int y) {

		JTextField textField = new JTextField();	
		textField.setBounds(x, y, FIELD_WIDTH, FIELD_HEIGHT);
		frame.add(textField);
		
		return textField;
	}

	/**
	 * Генерирует лэйбл типа {@link JLabel} по входящим параметрам
	 * Добавляет кнопку во фрейм калькулятора {@link Calculator}
	 * @param title - текст лэйбла
	 * @param x - x-координата левого верхнего угла лэйбла
	 * @param y - y-координата левого верхнего угла лэйбла
	 * @return объект типа {@link JLabel}, добавленный во <code>frame</code>
	 */
	public JLabel generateLabel(String title, int x, int y) {
		JLabel label = new JLabel(title);
		label.setBounds(x, y, LABEL_WIDTH, FIELD_HEIGHT);
		frame.add(label);
		
		return label;
	}
	
	/**
	 * Генерирует <code>frame</code> типа {@link JFrame}
	 * @param title - заголовок фрейма
	 * @param width - ширина формы в пикселях
	 * @param height - высота формы в пикселях
	 * @return 
	 */
	public JFrame generateFrame(String title, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		return frame;
		
	}
	
}
