package io.github.shirohoo.racing.app.usecase;

import static java.util.stream.IntStream.rangeClosed;
import io.github.shirohoo.racing.app.domain.Car;
import io.github.shirohoo.racing.app.domain.Cars;
import io.github.shirohoo.racing.app.domain.ForwardCondition;
import io.github.shirohoo.racing.app.domain.RacingGameSettings;
import io.github.shirohoo.racing.app.port.out.RacingGame;
import java.util.List;
import java.util.Objects;

public class RacingGameRunner implements RacingGame {
    private final Cars cars;
    private final ForwardCondition condition;
    private final int tryCount;

    private RacingGameRunner(RacingGameSettings settings) {
        Objects.requireNonNull(settings);
        this.cars = Cars.createCars(settings.carNames());
        this.condition = settings.condition();
        this.tryCount = settings.tryCount();
    }

    public static RacingGameRunner from(RacingGameSettings settings) {
        return new RacingGameRunner(settings);
    }

    @Override
    public List<List<Car>> eachRound() {
        return rangeClosed(1, tryCount)
            .mapToObj(i -> cars.forward(condition))
            .toList();
    }

    @Override
    public List<Car> winners() {
        return cars.winners();
    }
}
