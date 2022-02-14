package io.github.shirohoo.calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("수식 테스트")
class ExpressionTests {
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {
        "2+4-7*1/0", "5+7", "1 + +", "+", "-", "/", "*", "1 + +", "1 - -", "1 / /", "1 * *"
    })
    @DisplayName("유효하지 않은 수식이 입력되면 예외가 발생해야 한다")
    void validate(String expr) {
        assertThatThrownBy(() -> {
            Expression expression = Expression.from(expr);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수식을 공백단위로 분리할 수 있어야 한다")
    void split() {
        // ...given
        String expr = "2 + 4 - 7 * 1 / 0";
        String[] expected = {"2", "+", "4", "-", "7", "*", "1", "/", "0"};

        // ...when
        Expression expression = Expression.from(expr);

        // ...then
        assertThat(expression.split()).isEqualTo(expected);
    }
}