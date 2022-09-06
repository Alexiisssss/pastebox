package ru.sendel.pastebox.service;

import java.util.List;
import ru.sendel.pastebox.api.request.PasteboxRequest;
import ru.sendel.pastebox.api.response.PasteboxResponse;
import ru.sendel.pastebox.api.response.PasteboxUrlResponse;

public interface PasteboxService {

  PasteboxResponse getByHash(String hash);

  List<PasteboxResponse> getFirstPublicPasteboxes();

  PasteboxUrlResponse create(PasteboxRequest request);
}
