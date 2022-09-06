package ru.sendel.pastebox.repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import ru.sendel.pastebox.exeption.NotFoundEntityExceptions;


@Repository
public class PasteboxRepositoryMap implements PasteboxRepository {

  private final Map<String, PasteboxEntyty> vault = new ConcurrentHashMap<>();


  @Override
  public PasteboxEntyty getByHash(String hash) {
    PasteboxEntyty pasteboxEntyty = vault.get(hash);
    if (pasteboxEntyty == null) {
      throw new NotFoundEntityExceptions("Pastebox not found with hash " + hash);
    }
    return pasteboxEntyty;
  }

  @Override
  public List<PasteboxEntyty> getListOfPublicAndAlive(int amount) {
    LocalDateTime now = LocalDateTime.now();

    return vault.values().stream()
        .filter(PasteboxEntyty::isPublic)
        .filter(pasteboxEntyty -> pasteboxEntyty.getLifetime().isAfter(now))
        .sorted(Comparator.comparing(PasteboxEntyty::getId).reversed())
        .limit(amount)
        .collect(Collectors.toList());
  }

  @Override
  public void add(PasteboxEntyty pasteboxEntyty) {
    vault.put(pasteboxEntyty.getHash(), pasteboxEntyty);
  }
}
