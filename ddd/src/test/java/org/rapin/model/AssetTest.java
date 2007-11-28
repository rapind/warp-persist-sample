package org.rapin.model;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.rapin.model.Asset;
import org.rapin.module.MainModule;

import com.google.inject.Guice;
import com.google.inject.matcher.Matchers;
import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.TransactionStrategy;
import com.wideplay.warp.persist.UnitOfWork;

public class AssetTest extends TestCase {

	private final Log log = LogFactory.getLog(getClass());

	private Asset asset;

	/**
	 * This is run before every unit test and is used to setup test variables.
	 */
	@Before
	public void setUp() {

		log.debug("SETUP");

		// load the asset using guice
		asset = Guice.createInjector(
				new MainModule(),
				PersistenceService.usingJpa().across(UnitOfWork.TRANSACTION)
						.transactedWith(TransactionStrategy.LOCAL).forAll(
								Matchers.any()).buildModule()).getInstance(
				Asset.class);

	}

	/**
	 * Test for save and remove
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testSaveAndRemove() throws Exception {

		Asset mockAsset = new Asset();
		mockAsset.setName("mock");
		mockAsset = asset.save(mockAsset);

		assertNotNull(mockAsset);
		assertEquals(mockAsset.getName(), "mock");

		asset.remove(mockAsset.getId());

		mockAsset = asset.find(mockAsset.getId());
		assertNull(mockAsset);

	}

	/**
	 * This is run after every unit test and is used to undo changes as
	 * necessary.
	 */
	@After
	public void tearDown() {

		log.debug("TEARDOWN");
	}
}
