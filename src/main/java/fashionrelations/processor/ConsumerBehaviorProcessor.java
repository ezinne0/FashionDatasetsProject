// Processor for operation that calculates the average age of all consumers

package fashionrelations.processor;

import fashionrelations.common.ConsumerBehavior;
import fashionrelations.common.ConsumerIterator;
import fashionrelations.common.ConsumerBehaviorIterator;
import java.util.List;

public class ConsumerBehaviorProcessor {

    private Integer cachedAverageAge = null;

    public double getAverageAge(List<ConsumerBehavior> consumers) {

         // Memoization cached age
        if (cachedAverageAge != null) {
            return cachedAverageAge;
        }

        if (consumers == null || consumers.isEmpty()) {
            cachedAverageAge = 0;
            return 0;
        }

        ConsumerIterator iterator = new ConsumerBehaviorIterator(consumers);
        int total = 0;

        while (iterator.hasNext()) {
            ConsumerBehavior cb = iterator.next();
            total += cb.getAge();
        }

        // Average computed and rounded up
        int avg = (int) Math.round ((double) total / consumers.size());

        // Result saved in a cached
        cachedAverageAge = avg;
        return avg;
    }
 }
