import DB.GestoreQueryCerca;
import Model.Impiegato;
import org.junit.*;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class testGestoreQueryCerca {

    private GestoreQueryCerca gestoreQueryCerca;

    @Before
    public void setUp()  throws Exception {
        gestoreQueryCerca = new GestoreQueryCerca();
        assertNotNull(gestoreQueryCerca);
    }

    @After
    public void tearDown() throws  Exception {
        gestoreQueryCerca = null;
        assertNull(gestoreQueryCerca);
    }

    @Test
    public void testeseguiQueryRicercaImpiegatoConnesso() {
        Impiegato testAdmin = new Impiegato("admin","admin","V",43);
        Impiegato testResult = null;
        try {
            testResult = gestoreQueryCerca.eseguiQueryRicercaImpiegatoConnesso(null, "");
        } catch ( SQLException e ) {
            e.printStackTrace();
        }

        assertEquals(testAdmin,testResult);


    }
}
