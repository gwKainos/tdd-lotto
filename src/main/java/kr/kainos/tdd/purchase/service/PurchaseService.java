package kr.kainos.tdd.purchase.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import kr.kainos.tdd.purchase.domain.LottoPurchaseRequest;
import kr.kainos.tdd.purchase.domain.LottoPurchaseResult;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

  public static final int LOTTO_PRICE = 1000;

  public LottoPurchaseResult purchaseLotto(LottoPurchaseRequest lottoPurchaseRequest) {
    int affordableGames = lottoPurchaseRequest.getMoney() / LOTTO_PRICE;
    int purchasableGames = Math.min(affordableGames, lottoPurchaseRequest.getCount());
    int remainingMoney = lottoPurchaseRequest.getMoney() - (purchasableGames * LOTTO_PRICE);

    List<List<Integer>> lottoSets = IntStream.range(0, purchasableGames)
            .mapToObj(i -> createLottoNumbers())
            .collect(Collectors.toList());

    String message = purchasableGames == lottoPurchaseRequest.getCount() ?
            purchasableGames + "장이 발행 되었습니다" : "금액이 부족합니다.";

    return new LottoPurchaseResult(lottoSets, remainingMoney, message);
  }

  public List<Integer> createLottoNumbers() {
    return new Random().ints(1, 46)
            .distinct()
            .limit(6)
            .boxed()
            .collect(Collectors.toList());
  }
}
