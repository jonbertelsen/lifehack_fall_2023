package app.exceptions;

import javax.xml.crypto.Data;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseException extends Exception
{
    public DatabaseException(String message)
    {
        super(message);
    }

    public DatabaseException(String userMessage, String systemMessage)
    {
        super(userMessage);
        Logger.getLogger("web").log(Level.SEVERE, systemMessage);
    }
}
