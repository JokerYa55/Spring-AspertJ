package app.controller;

import app.aspect.SecurityAnatation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author vasil
 */
@Controller
public class RestController {

    private static final Logger LOG = LoggerFactory.getLogger(RestController.class);

    @GetMapping(path = "/test", produces = "application/json")
    @SecurityAnatation
    public ResponseEntity test() {
        LOG.info("{}", "test");
        return new ResponseEntity(HttpStatus.OK);
    }
}
