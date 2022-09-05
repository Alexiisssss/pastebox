package ru.sendel.pastebox.controller;

import java.util.Collection;
import java.util.Collections;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sendel.pastebox.api.request.PasteBoxRequest;

@RestController
public class PasteboxController {


  @GetMapping("/")
  public Collection<String> getPublicPasteList() {
    return Collections.emptyList();
  }

  @GetMapping("/{hash}")
  public String getByHash(@PathVariable String hash) {
    return hash;
  }

  @PostMapping("/")
  public String add(@RequestBody PasteBoxRequest request) {
    return request.getData();
  }
}
