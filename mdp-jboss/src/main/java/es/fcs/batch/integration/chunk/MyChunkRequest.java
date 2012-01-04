/*
 * Copyright 2006-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package es.fcs.batch.integration.chunk;

import java.util.Collection;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.step.item.ChunkProcessor;
import org.springframework.batch.integration.chunk.ChunkRequest;

/**
 * Encapsulation of a chunk of items to be processed remotely as part of a step execution.
 * 
 * @author Dave Syer
 * 
 * @param <T> the type of the items to process
 */
public class MyChunkRequest<T> extends ChunkRequest<T> {

	/**
	 * 
	 */
	private final ChunkProcessor<T> chunkProcessor;

	public MyChunkRequest(Collection<? extends T> items, Long jobId, StepContribution stepContribution, ChunkProcessor<T> chunkProcessor) {
		super(items, jobId, stepContribution);
		this.chunkProcessor = chunkProcessor;
	}

	public ChunkProcessor<T> getChunkProcessor() {
		return chunkProcessor;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + ", chunkProcessor=" + chunkProcessor;
	}

}
