package ru.sendel.pastebox.api.request;

import lombok.Data;


@Data
public class PasteBoxRequest {
  private String data;
  private long ExpirationsTime;
  private PublicStatus publicStatus;

}
