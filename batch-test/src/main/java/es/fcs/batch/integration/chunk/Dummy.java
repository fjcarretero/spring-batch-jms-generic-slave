package es.fcs.batch.integration.chunk;

import org.springframework.batch.core.step.item.ChunkProcessor;

public class Dummy {
	private ChunkProcessor chunkProcessor;

	public ChunkProcessor getChunkProcessor() {
		return chunkProcessor;
	}

	public void setChunkProcessor(ChunkProcessor chunkProcessor) {
		this.chunkProcessor = chunkProcessor;
	}
}
