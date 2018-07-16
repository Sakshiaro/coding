package com.tworks.bootcamp;

import org.junit.Test;

import junit.framework.Assert;

public class BoardTest {

	@Test
	public void createBoard() {
		Board board = new Board();
		Assert.assertNotNull(board);
	}
	
	@Test
	public void validCellValueTest() {
		String value = "0";
		Board b = new Board();
		Assert.assertFalse(b.isValidCellValue(value));
	}
}
