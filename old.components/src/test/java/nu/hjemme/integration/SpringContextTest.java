package nu.hjemme.integration;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

/**
 * Test of some beans integrated.
 * @author Tor Egil Jacobsen
 */
public class SpringContextTest {

    protected transient Logger log = Logger.getLogger(getClass());

    @Test(expected = NoSuchBeanDefinitionException.class)
    public void getBeanException() {
        SpringContext.getSpringBean("noBean");
    }

    @Test
    public void validateContextWithoutException() {
        SpringContext.getSpringBean("menusCache");
    }
}
