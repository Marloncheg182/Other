package Exceptions;

import javax.ejb.ApplicationException;

/**
 * @author Romanenchuk Oleg
 */
@ApplicationException(rollback = true)
public class ValidationException extends RuntimeException{

    public ValidationException(String message){
        super(message);
    }
}
