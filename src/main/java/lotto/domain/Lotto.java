package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Lotto {
    public static final String INVALID_SIZE = "[ERROR] 6개의 숫자를 입력해주세요.";
    public static final String DUPLICATE_NUMBER = "[ERROR] 중복된 숫자가 올 수 없습니다.";
    public static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = toLottoNumbers(numbers);
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
    }

    private List<LottoNumber> toLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .toList();
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(INVALID_SIZE);
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        HashSet<Integer> distinctNumbers = new HashSet<>();
        for (Integer number : numbers) {
            if (!distinctNumbers.add(number)) {
                throw new IllegalArgumentException(DUPLICATE_NUMBER);
            }
        }
    }

    public int match(Lotto lotto) {
        int matchCount = 0;
        for (LottoNumber number : lotto.numbers) {
            if (numbers.contains(number)) {
                matchCount++;
            }
        }
        return matchCount;
    }

    public boolean has(LottoNumber bonusNumber) {
        return numbers.stream()
                .anyMatch(number -> number.equals(bonusNumber));
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lotto lotto)) {
            return false;
        }
        return Objects.equals(numbers, lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}
/*
제공된 Lotto 클래스를 활용해 구현해야 한다.
numbers의 접근 제어자인 private을 변경할 수 없다.
Lotto에 필드(인스턴스 변수)를 추가할 수 없다.
Lotto의 패키지 변경은 가능하다.
 */
