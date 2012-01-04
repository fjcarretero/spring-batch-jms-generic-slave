

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
public class testAutowiring extends AbstractJUnit4SpringContextTests {

	@Test public void test1(){
		PlayerDao playerDao = new JdbcPlayerDao();
		PlayerItemWriter playerItemWriter = new PlayerItemWriter();
		playerItemWriter.setPlayerDao(playerDao);
		SerializablePassThroughItemProcessor passThroughItemProcessor = new SerializablePassThroughItemProcessor();
		SerializableChunkProcessor serializableChunkProcessor = new SerializableChunkProcessor(passThroughItemProcessor, playerItemWriter);
		System.out.println(serializableChunkProcessor);
	}
}
