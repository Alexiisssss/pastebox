package ru.sendel.pastebox.api.request;

import lombok.Data;


@Data
public class PasteboxRequest {
  private String data;
  private long expirationsTime;
  private PublicStatus publicStatus;

}
