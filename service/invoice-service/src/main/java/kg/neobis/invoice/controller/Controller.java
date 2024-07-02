package kg.neobis.invoice.controller;

import kg.neobis.invoice.dto.NodeDto;
import kg.neobis.invoice.service.impl.ServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Controller {

    ServiceImpl service;

    @GetMapping("nodes")
    public ResponseEntity<List<NodeDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("It works");
    }

}
