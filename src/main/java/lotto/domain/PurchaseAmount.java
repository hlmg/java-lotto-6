package lotto.domain;

public record PurchaseAmount(long amount) {
    public static final int unit = 1000;

    public PurchaseAmount {
        validate(amount);
    }

    private void validate(long amount) {
        validateIsZero(amount);
        validateDivisible(amount);
    }

    private void validateIsZero(long amount) {
        if (amount == 0L) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 0원일 수 없습니다.");
        }
    }

    private void validateDivisible(long amount) {
        if (amount % unit != 0L) {
            throw new IllegalArgumentException(String.format("[ERROR] 구입 금액은 %s원으로 나누어 떨어져야 합니다.", unit));
        }
    }

    public long getQuantity(int lottoPrice) {
        return amount / lottoPrice;
    }
}
