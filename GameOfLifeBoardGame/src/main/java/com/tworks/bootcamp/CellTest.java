package com.tworks.bootcamp;



import org.junit.Assert;
import org.junit.Test;

public class CellTest {

	@Test
	public void test() {
		Cell cell = new Cell("X");
		Assert.assertNotNull(cell);
	}

}
