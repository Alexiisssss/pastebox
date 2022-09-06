package ru.sendel.pastebox;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.sendel.pastebox.api.response.PasteboxResponse;
import ru.sendel.pastebox.exeption.NotFoundEntityExceptions;
import ru.sendel.pastebox.repository.PasteboxEntyty;
import ru.sendel.pastebox.repository.PasteboxRepository;
import ru.sendel.pastebox.service.PasteboxService;

@SpringBootTest
public class PasteboxServiceTest {

  @Autowired
  private PasteboxService pasteboxService;

  @MockBean
  private PasteboxRepository pasteboxRepository;

  @Test
  public void notExistHash() {
    when(pasteboxRepository.getByHash(anyString())).thenThrow(NotFoundEntityExceptions.class);
    assertThrows(NotFoundEntityExceptions.class, () -> pasteboxService.getByHash("asaaasasasasq"));
  }

  @Test
  public void getExistHash() {

    PasteboxEntyty pasteboxEntyty = new PasteboxEntyty();
    pasteboxEntyty.setHash("1");
    pasteboxEntyty.setData("11");
    pasteboxEntyty.setPublic(true);
    when(pasteboxRepository.getByHash("1")).thenReturn(pasteboxEntyty);

    PasteboxResponse expected = new PasteboxResponse("11", true);
    PasteboxResponse actual = pasteboxService.getByHash("1");
    assertEquals(expected, actual);
  }
}
