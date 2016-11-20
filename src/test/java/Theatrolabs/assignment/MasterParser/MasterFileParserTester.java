package Theatrolabs.assignment.MasterParser;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.List;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

public class MasterFileParserTester {

	@Test
	public void Master_MasterFileParser_CSV() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		MasterFileParser ms = new MasterFileParser("/home/munjal/Desktop/TheatroLabs/assignment.csv");
		Class cls = ms.getClass();
		Field field=cls.getDeclaredField("fr");
		field.setAccessible(true);
		assertTrue((FileParser) field.get(ms) instanceof CSVFileParser);
	}
	
	@Test
	public void Master_MasterFileParser_XLS() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		MasterFileParser ms = new MasterFileParser("/home/munjal/Desktop/TheatroLabs/jexcelapi/assignment.XLS");
		Class cls = ms.getClass();
		Field field=cls.getDeclaredField("fr");
		field.setAccessible(true);
		assertTrue((FileParser) field.get(ms) instanceof XLSFileParser);
	}

}
