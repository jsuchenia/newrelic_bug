package pl.suchenia;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@RestController
public class ReproduceController {

    @RequestMapping(value = "/", produces = "text/plain")
    public String getInfo() throws InfoException {
        throw new InfoException("This is expected response");
    }


    @ExceptionHandler(InfoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleInfoException(InfoException ie) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ie.printStackTrace(new PrintStream(outputStream));
        return "Expected: " + outputStream.toString();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleAllOtherExceptions(Exception ie) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ie.printStackTrace(new PrintStream(outputStream));
        return "Unexpected: " + outputStream.toString();
    }
}
