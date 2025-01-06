package kr.kainos.tdd.purchase.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LottoPurchaseRequest {
  private int money;
  private int count;

  public LottoPurchaseRequest(int money, int count) {
    this.money = money;
    this.count = count;
  }
}
