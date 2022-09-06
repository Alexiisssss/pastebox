package ru.sendel.pastebox.repository;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.web.util.pattern.PathPattern.PathRemainingMatchInfo;


@Data
public class PasteboxEntyty {

  private int id;
  private String data;
  private String hash;
  private LocalDateTime lifetime;
  private boolean isPublic;
}
