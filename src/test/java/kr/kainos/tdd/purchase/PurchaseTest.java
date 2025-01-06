package kr.kainos.tdd.purchase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class PurchaseTest {

  @Test
  public void generateLottoNumbers() throws Exception {
    List<Integer> lottoNumbers = createLottoNumbers();
    assertEquals(6, lottoNumbers.size());
  }
}
