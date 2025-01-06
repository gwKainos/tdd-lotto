package kr.kainos.tdd.purchase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import kr.kainos.tdd.purchase.domain.LottoPurchaseResult;
import org.junit.jupiter.api.Test;

public class PurchaseTest {

  private static final int LOTTO_PRICE = 1000;

  @Test
  public void generateLottoNumbers() throws Exception {
    List<Integer> lottoNumbers = createLottoNumbers();
    assertEquals(6, lottoNumbers.size());
    assertTrue(lottoNumbers.stream().allMatch(num -> num >= 1 && num <= 45));
    assertEquals(6, Set.copyOf(lottoNumbers).size());
  }

  @Test
  public void purchaseLotto_insufficientMoney() throws Exception {
    LottoPurchaseResult lottoPurchaseResult = purchaseLotto(2000, 3);
    assertEquals("금액이 부족합니다.", lottoPurchaseResult.getMessage());
  }

  @Test
  public void purchaseLotto_exactAmount() throws Exception {
    LottoPurchaseResult lottoPurchaseResult = purchaseLotto(3000, 3);

    assertEquals(3, lottoPurchaseResult.getLotto().size());
    assertEquals(0, lottoPurchaseResult.getMoney());
    assertEquals("3장이 발행 되었습니다", lottoPurchaseResult.getMessage());
  }

  @Test
  public void purchaseLotto_withChange() throws Exception {
    LottoPurchaseResult lottoPurchaseResult = purchaseLotto(5500, 5);
    assertEquals(5, lottoPurchaseResult.getLotto().size());
    assertEquals(500, lottoPurchaseResult.getMoney());
    assertEquals("5장이 발행 되었습니다", lottoPurchaseResult.getMessage());
  }

  private LottoPurchaseResult purchaseLotto(int totalMoney, int gameCount) {
    int affordableGames = totalMoney / LOTTO_PRICE;
    int purchasableGames = Math.min(affordableGames, gameCount);
    int remainingMoney = totalMoney - (purchasableGames * LOTTO_PRICE);

    List<List<Integer>> lottoSets = IntStream.range(0, purchasableGames)
            .mapToObj(i -> createLottoNumbers())
            .collect(Collectors.toList());

    String message = purchasableGames == gameCount ?
            purchasableGames + "장이 발행 되었습니다" : "금액이 부족합니다.";

    return new LottoPurchaseResult(lottoSets, remainingMoney, message);
  }


  private List<Integer> createLottoNumbers() {
    return new Random().ints(1, 46)
            .distinct()
            .limit(6)
            .boxed()
            .collect(Collectors.toList());
  }
}
