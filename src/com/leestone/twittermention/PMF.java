/**
 * 
 */
package com.leestone.twittermention;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * The PersistenceManagerFactory class
 * @author Lee Stone
 * @created 24th December 2009
 * @updated 24th December 2009
 *
 */
public final class PMF {
	/** The persistenceManagerFactory item **/
    private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");

    private PMF() {}

    /**
     * 
     * @return PersistenceManagerFactory
     */
    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }
}
