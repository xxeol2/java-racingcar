package domain;

import static validation.CountValidator.COUNT_VALIDATOR;

import java.util.List;
import java.util.stream.Collectors;
import utils.NumberGenerator;

public class Race {

    private final int TOTAL_COUNT;
    private int currentCount = 0;
    private final Participants participants;

    public Race(final int totalCount, final List<String> carNames, NumberGenerator numberGenerator) {
        COUNT_VALIDATOR.validate(totalCount);
        this.TOTAL_COUNT = totalCount;
        List<Car> cars = carNames.stream()
            .map(carName -> generateCar(carName, numberGenerator))
            .collect(Collectors.toList());
        this.participants = new Participants(cars, numberGenerator);
    }

    private Car generateCar(String carName, NumberGenerator numberGenerator) {
        return new Car(carName, numberGenerator);
    }

    public void playRound() {
        participants.drive();
        addCount();
    }

    public List<Car> getParticipants() {
        return participants.getCars();
    }

    public List<Car> findWinners() {
        return participants.findWinners();
    }

    private void addCount() {
        currentCount += 1;
    }

    public boolean isFinished() {
        return TOTAL_COUNT == currentCount;
    }
}
