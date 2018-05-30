package api.rest.library.exceptions;


public class ElementDoesntMatchException extends Exception
{
    public ElementDoesntMatchException(String elementName, String[] values) {
        super("Element " + elementName + "has wrong value. It should be:" + values.toString());
    }
}
