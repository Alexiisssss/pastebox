package ru.sendel.pastebox.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import ru.sendel.pastebox.api.request.PasteboxRequest;
import ru.sendel.pastebox.api.request.PublicStatus;
import ru.sendel.pastebox.api.response.PasteboxResponse;
import ru.sendel.pastebox.api.response.PasteboxUrlResponse;
import ru.sendel.pastebox.repository.PasteboxEntyty;
import ru.sendel.pastebox.repository.PasteboxRepository;


@Service
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "app")
public class PasteboxServiceImpl implements PasteboxService {

  private String host = "http://abc.ru";
  private int publicListSize = 10;

  private final PasteboxRepository repository;
  private AtomicInteger idGenerator = new AtomicInteger(0);

  @Override
  public PasteboxResponse getByHash(String hash) {
    PasteboxEntyty pasteboxEntyty = repository.getByHash(hash);
    return new PasteboxResponse(pasteboxEntyty.getData(), pasteboxEntyty.isPublic());
  }

  @Override
  public List<PasteboxResponse> getFirstPublicPasteboxes() {

     List<PasteboxEntyty> list = repository.getListOfPublicAndAlive(publicListSize);
      return list.stream().map(pasteboxEntyty ->
        new PasteboxResponse(pasteboxEntyty.getData(),
             pasteboxEntyty.isPublic()))
         .collect(Collectors.toList());

  }

  @Override
  public PasteboxUrlResponse create(PasteboxRequest request) {
    int hash = generateId();
    PasteboxEntyty pasteboxEntyty = new PasteboxEntyty();
    pasteboxEntyty.setData(request.getData());
    pasteboxEntyty.setId(hash);
    pasteboxEntyty.setHash(Integer.toHexString(hash));
    pasteboxEntyty.setPublic(request.getPublicStatus() == PublicStatus.PUBLIC);
    pasteboxEntyty.setLifetime(LocalDateTime.now().plusSeconds(request.getExpirationsTime()));
    repository.add(pasteboxEntyty);
    return new PasteboxUrlResponse(host + "/" + pasteboxEntyty.getHash());
  }

  private int generateId() {
    return idGenerator.getAndDecrement();
  }
}
