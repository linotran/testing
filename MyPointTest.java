import static org.junit.Assert.*;

import java.util.Random;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MyPointTest {
	MyPoint mp1;
	MyPoint mp2;
	MyPoint mpmock;
	ITranslation transmock;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		mp1 = new MyPoint();
		mp2 = new MyPoint(2d, 3d);
		mpmock = EasyMock.createMock(MyPoint.class);
		transmock = EasyMock.createMock(ITranslation.class);
	}

	@After
	public void tearDown() throws Exception {
		mp1 = null;
		mp2 = null;
		mpmock = null;
		transmock = null;
	}

	@Test
	public void testSetX() {
		mp1.setX(1d);
		assertEquals(1d, mp1.getX(), 0.0001);
	}

	@Test
	public void testSetX2() {
		mp2.setX(Double.NaN);
		assertEquals(2d, mp2.getX(), 0.0001);
	}

	@Test
	public void testGetX() {
		assertEquals(2d, mp2.getX(), 0.0001);
	}

	@Test
	public void testSetY() {
		mp1.setY(1d);
		assertEquals(1d, mp1.getY(), 0.0001);
	}

	@Test
	public void testSetY2() {
		mp2.setY(Double.NaN);
		assertEquals(3d, mp2.getY(), 0.0001);
	}

	@Test
	public void testGetY() {
		assertEquals(3d, mp2.getY(), 0.0001);
	}

	@Test
	public void testMyPoint() {
		assertNotNull(mp1);
	}

	@Test
	public void testMyPointDoubleDouble() {
		assertNotNull(mp2);
	}

	@Test
	public void testMyPointMyPoint() {
		MyPoint mp3 = new MyPoint(mp1);
		assertNotNull(mp3);
	}

	@Test
	public void testMyPointMyPoint2() {
		MyPoint mp3 = new MyPoint(mp2);
		assertNotNull(mp3);
	}

	@Test
	public void testScale() {
		MyPoint scaledPoint = mp2.scale(2d);
		assertEquals(4d, scaledPoint.getX(), 0.0001);
		assertEquals(6d, scaledPoint.getY(), 0.0001);
	}

	@Test
	public void testHorizontalSymmetry() {
		MyPoint computedPoint = mp2.horizontalSymmetry(mp1);
		Double expectedX = mp2.getX();
		Double expectedY = 2 * mp1.getY() - mp2.getY();
		assertEquals(expectedX, computedPoint.getX(), 0.0001);
		assertEquals(expectedY, computedPoint.getY(), 0.0001);
	}

	@Test
	public void testHorizontalSymmetry2() throws IllegalArgumentException {
		thrown.expect(IllegalArgumentException.class);
		mp2.horizontalSymmetry(null);
	}
	
	@Test
	public void testSetPoint() {
		Random rand = new Random();
		mpmock.setPoint(rand, rand);
		EasyMock.replay(mpmock);
	}
	
	@Test
	public void testTranslate() {
		mpmock.translate(transmock);
		EasyMock.replay(mpmock);
	}
}
