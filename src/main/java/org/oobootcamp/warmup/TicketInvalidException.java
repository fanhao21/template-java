package org.oobootcamp.warmup;

public class TicketInvalidException extends RuntimeException{
    public TicketInvalidException() {
        super("此票无效，请检查");
    }
}
