package ru.sendel.pastebox.repository;

import java.util.List;

public interface PasteboxRepository {

  PasteboxEntyty getByHash(String hash);

  List<PasteboxEntyty> getListOfPublicAndAlive(int amount);

  void add(PasteboxEntyty pasteboxEntyty);
}
