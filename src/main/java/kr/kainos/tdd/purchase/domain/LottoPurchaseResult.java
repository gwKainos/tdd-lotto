package kr.kainos.tdd.purchase.domain;

import java.util.List;
import lombok.Getter;

@Getter
public class LottoPurchaseResult {

  private List<List<Integer>> lotto;
  private int money;
  private String message;

  public LottoPurchaseResult(List<List<Integer>> lotto, int money, String message) {
    this.lotto = lotto;
    this.money = money;
    this.message = message;

  }
}
