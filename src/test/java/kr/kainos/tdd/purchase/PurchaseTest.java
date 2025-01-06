package kr.kainos.tdd.purchase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;
import kr.kainos.tdd.purchase.domain.LottoPurchaseResult;
import kr.kainos.tdd.purchase.service.PurchaseService;
import org.junit.jupiter.api.Test;

public class PurchaseTest {

  private final PurchaseService purchaseService = new PurchaseService();

  @Test
  public void generateLottoNumbers() throws Exception {
    List<Integer> lottoNumbers = purchaseService.createLottoNumbers();
    assertEquals(6, lottoNumbers.size());
    assertTrue(lottoNumbers.stream().allMatch(num -> num >= 1 && num <= 45));
    assertEquals(6, Set.copyOf(lottoNumbers).size());
  }

  @Test
  public void purchaseLotto_insufficientMoney() throws Exception {
    LottoPurchaseResult lottoPurchaseResult = purchaseService.purchaseLotto(2000, 3);
    assertEquals("금액이 부족합니다.", lottoPurchaseResult.getMessage());
  }

  @Test
  public void purchaseLotto_exactAmount() throws Exception {
    LottoPurchaseResult lottoPurchaseResult = purchaseService.purchaseLotto(3000, 3);

    assertEquals(3, lottoPurchaseResult.getLotto().size());
    assertEquals(0, lottoPurchaseResult.getMoney());
    assertEquals("3장이 발행 되었습니다", lottoPurchaseResult.getMessage());
  }

  @Test
  public void purchaseLotto_withChange() throws Exception {
    LottoPurchaseResult lottoPurchaseResult = purchaseService.purchaseLotto(5500, 5);
    assertEquals(5, lottoPurchaseResult.getLotto().size());
    assertEquals(500, lottoPurchaseResult.getMoney());
    assertEquals("5장이 발행 되었습니다", lottoPurchaseResult.getMessage());
  }
}
