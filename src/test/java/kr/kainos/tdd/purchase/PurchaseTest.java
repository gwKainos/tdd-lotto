package kr.kainos.tdd.purchase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class PurchaseTest {

  @Test
  public void generateLottoNumbers() throws Exception {
    List<Integer> lottoNumbers = createLottoNumbers();
    assertEquals(6, lottoNumbers.size(), "로또 번호는 6개여야 합니다.");
    assertTrue(lottoNumbers.stream().allMatch(num -> num >= 1 && num <= 45),
            "로또 번호는 1~45 입니다.");
    assertEquals(6, Set.copyOf(lottoNumbers).size(), "로또 번호는 중복되지 않아야 합니다.");
  }

  private List<Integer> createLottoNumbers() {
    return new Random().ints(1, 46)
            .distinct()
            .limit(6)
            .boxed()
            .collect(Collectors.toList());
  }
}
