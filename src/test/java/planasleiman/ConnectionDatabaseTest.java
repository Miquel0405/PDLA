package planasleiman;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import planasleiman.database.database_interface;

public class ConnectionDatabaseTest {
    @Test
    void connectTest(){
        assertDoesNotThrow(()->database_interface.getConnection());
    }
    
}
