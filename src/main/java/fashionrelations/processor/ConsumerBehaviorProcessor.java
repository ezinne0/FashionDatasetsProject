package fashionrelations.processor;

import fashionrelations.common.ConsumerBehavior;
import java.util.List;

public class ConsumerBehaviorProcessor {

    // Calculates the average age of all consumers
    public double getAverageAge(List<ConsumerBehavior> consumers) {

        if (consumers == null || consumers.isEmpty()) {
            return 0;
        }

        int total = 0;

        for (ConsumerBehavior cb : consumers) {
            total += cb.getAge();
        }

        return (int) Math.round((double) total / consumers.size());
    }
}
