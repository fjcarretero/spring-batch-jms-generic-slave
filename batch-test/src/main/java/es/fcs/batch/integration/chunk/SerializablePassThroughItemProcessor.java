package es.fcs.batch.integration.chunk;

import java.io.Serializable;

import org.springframework.batch.item.ItemProcessor;

public class SerializablePassThroughItemProcessor<T> implements ItemProcessor<T, T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2202531634547919303L;

	/**
	 * Just returns the item back to the caller.
	 * 
	 * @return the item
	 * @see ItemProcessor#process(Object)
	 */
	public T process(T item) throws Exception {
		return item;
	}

}
