package kr.kainos.tdd.purchase.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.kainos.tdd.purchase.domain.LottoPurchaseRequest;
import kr.kainos.tdd.purchase.domain.LottoPurchaseResult;
import kr.kainos.tdd.purchase.service.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/purchase")
@Tag(name = "B - Purchase", description = "로또 발행")
public class PurchaseController {
  private final PurchaseService purchaseService;

  public PurchaseController(PurchaseService purchaseService) {
    this.purchaseService = purchaseService;
  }

  @PostMapping
  public LottoPurchaseResult createLottoPurchase(@Valid @RequestBody LottoPurchaseRequest lottoPurchaseRequest) {
    return purchaseService.purchaseLotto(lottoPurchaseRequest);
  }
}
