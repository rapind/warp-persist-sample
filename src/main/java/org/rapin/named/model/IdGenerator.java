package org.rapin.named.model;

import java.util.UUID;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * Generates unique identifiers.
 */
public class IdGenerator {

	/**
	 * Create a UUID.
	 * 
	 * @return The UUID.
	 */
	public static String createId() {
		UUID uuid = java.util.UUID.randomUUID();
		return uuid.toString();
	}
}
