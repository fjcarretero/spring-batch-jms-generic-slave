import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.sample.domain.football.PlayerDao;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.fcs.batch.integration.chunk.SerializableChunkProcessor;
import es.fcs.batch.integration.chunk.SerializablePassThroughItemProcessor;
import es.fcs.batch.sample.domain.football.internal.JdbcPlayerDao;
import es.fcs.batch.sample.domain.football.internal.PlayerItemWriter;
import es.fcs.poc.NonValidatingContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:data-source-context-sc.xml"}, loader=NonValidatingContextLoader.class)
public class SerializableTestCase extends AbstractJUnit4SpringContextTests {
	@Test public void testSerialize() {
		ObjectOutputStream out = null; 
		try {
			PlayerDao playerDao = new JdbcPlayerDao();
			PlayerItemWriter playerItemWriter = new PlayerItemWriter();
			playerItemWriter.setPlayerDao(playerDao);
			SerializablePassThroughItemProcessor passThroughItemProcessor = new SerializablePassThroughItemProcessor();
			SerializableChunkProcessor serializableChunkProcessor = new SerializableChunkProcessor(passThroughItemProcessor, playerItemWriter);
			out = new ObjectOutputStream(new FileOutputStream("test.ser"));
			out.writeObject(serializableChunkProcessor);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Test public void testDeserialize() {
		ObjectInputStream in = null; 
		try {
			in = new ObjectInputStream(new FileInputStream("test.ser"));
			SerializableChunkProcessor serializableChunkProcessor = (SerializableChunkProcessor)in.readObject();
			System.out.println(serializableChunkProcessor);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
