package lotto.domain;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public record LottoTickets(List<Lotto> lottos, PurchaseAmount purchaseAmount) {

    public Map<Rank, Integer> getRankResult(WinningTicket winningTicket) {
        EnumMap<Rank, Integer> rankResult = initRankResult();

        lottos.stream()
                .map(winningTicket::match)
                .map(Rank::of)
                .filter(rank -> rank != Rank.NONE)
                .forEach(rank -> rankResult.put(rank, rankResult.get(rank) + 1));

        return rankResult;
    }

    private EnumMap<Rank, Integer> initRankResult() {
        EnumMap<Rank, Integer> rankResult = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            rankResult.put(rank, 0);
        }
        rankResult.remove(Rank.NONE);
        return rankResult;
    }

    public BigDecimal getRateOfReturn(long totalPrize) {
        return purchaseAmount.getRateOfReturn(totalPrize);
    }
}
