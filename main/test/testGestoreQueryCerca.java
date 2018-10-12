import DB.GestoreQueryCerca;
import Model.Impiegato;
import org.junit.*;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class testGestoreQueryCerca {

    private GestoreQueryCerca gestoreQueryCerca;

    @Before public void setUp()  throws Exception {
        gestoreQueryCerca = new GestoreQueryCerca();
        assertNotNull(gestoreQueryCerca);
    }

    @After public void tearDown() throws  Exception {
        gestoreQueryCerca = null;
        assertNull(gestoreQueryCerca);
    }

    /** 
       Primo caso:
       Username e password inseriti e presente nel DB
     */
    @Test public void testeseguiQueryRicercaImpiegatoConnesso1() {
        Impiegato testAdmin = new Impiegato("admin","admin","V",43);
        Impiegato testResult = null;
        try {
            testResult = gestoreQueryCerca.eseguiQueryRicercaImpiegatoConnesso("admin", "admin");
        } catch ( SQLException e ) {
            e.printStackTrace();
        }

        assertEquals(testAdmin,testResult);
    }
    /** 
       Secondo caso:
       Username || password non inseriti 
     */
    @Test public void testeseguiQueryImpiegatoConnesso2() {
        Impiegato testResult = null;
        try {
            testResult = gestoreQueryCerca.eseguiQueryRicercaImpiegatoConnesso("","");
        } catch ( SQLException e ) { e.printStackTrace(); }

        assertNull(testResult);
    }
    
    /** 
        Terzo caso:
        Username e passwordo inseriti ma non presenti nel DB
     */
    @Test public void testeeseguiQueryImpiegatoConnesso3() {

        Impiegato testResult = null;
        try {
            testResult = gestoreQueryCerca.eseguiQueryRicercaImpiegatoConnesso("prova","prova");
            fail();// Se non lancia un eccezione il test fallisce, ( resultSet.next() deve lanciarla precisamente  )
        } catch ( SQLException e ) { assertTrue(true); }

    }
}
