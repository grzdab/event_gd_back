package com.event.logistic;

import com.event.client.Client;
import com.event.client.MiniClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LogisticController {

    private final LogisticService logisticService;

    public LogisticController(LogisticService logisticService) {
        this.logisticService = logisticService;
    }

    @GetMapping("/logistic/{logisticId}")
    public Logistic getLogistic(@PathVariable int logisticId) {
        return logisticService.getLogistic(logisticId);
    }

    @PostMapping("/logistic")
    public Logistic addLogistic(@RequestBody Logistic logistic) {
        return logisticService.addLogistic(logistic);
    }

    @PutMapping("/logistic/{logisticId}")
    public Logistic updateLogistic(@PathVariable int logisticId, @RequestBody Logistic newLogistic) {
        return logisticService.updateLogistic(logisticId, newLogistic);
    }

    @DeleteMapping("/logistic/{logisticId}")
    public String deleteLogistic(@PathVariable int logisticId) {
        return logisticService.deleteLogistic(logisticId);
    }
}
