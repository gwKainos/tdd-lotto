package kr.kainos.tdd.purchase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;
import kr.kainos.tdd.purchase.domain.LottoPurchaseRequest;
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
  public void purchaseLotto_failsWhenAmountIsLessThanLottoPrice() throws Exception {
    LottoPurchaseRequest request = new LottoPurchaseRequest(10000, 0);
    LottoPurchaseResult lottoPurchaseResult = purchaseService.purchaseLotto(request);
    assertEquals("발행 갯수는 0이 될수 없습니다.", lottoPurchaseResult.getMessage());
  }

  @Test
  public void purchaseLotto_insufficientMoneyWhenZeroAmount() throws Exception {
    LottoPurchaseRequest request = new LottoPurchaseRequest(0, 1);
    LottoPurchaseResult lottoPurchaseResult = purchaseService.purchaseLotto(request);
    assertEquals("금액이 부족합니다.", lottoPurchaseResult.getMessage());
  }

  @Test
  public void purchaseLotto_insufficientMoney() throws Exception {
    LottoPurchaseRequest request = new LottoPurchaseRequest(2000, 3);
    LottoPurchaseResult lottoPurchaseResult = purchaseService.purchaseLotto(request);
    assertEquals("금액이 부족합니다.", lottoPurchaseResult.getMessage());
  }

  @Test
  public void purchaseLotto_exactAmount() throws Exception {
    LottoPurchaseRequest request = new LottoPurchaseRequest(3000, 3);
    LottoPurchaseResult lottoPurchaseResult = purchaseService.purchaseLotto(request);

    assertEquals(3, lottoPurchaseResult.getLotto().size());
    assertEquals(0, lottoPurchaseResult.getMoney());
    assertEquals("3장이 발행 되었습니다", lottoPurchaseResult.getMessage());
  }

  @Test
  public void purchaseLotto_withChange() throws Exception {
    LottoPurchaseRequest request = new LottoPurchaseRequest(5500, 5);
    LottoPurchaseResult lottoPurchaseResult = purchaseService.purchaseLotto(request);
    assertEquals(5, lottoPurchaseResult.getLotto().size());
    assertEquals(500, lottoPurchaseResult.getMoney());
    assertEquals("5장이 발행 되었습니다", lottoPurchaseResult.getMessage());
  }
}
