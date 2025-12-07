package fashionrelations.processor;

import fashionrelations.common.ConsumerBehavior;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsumerAvgAgeTest {

    @Test
    public void testGetAverageAge() {
        ConsumerBehaviorProcessor processor = new ConsumerBehaviorProcessor();

        // null list = return 0
        int resultNull = (int) processor.getAverageAge(null);
        assertEquals(0, resultNull);

        // empty list = return 0
        List<ConsumerBehavior> emptyList = new ArrayList<>();
        int resultEmpty = (int) processor.getAverageAge(emptyList);
        assertEquals(0, resultEmpty);

        // normal case: avg of age 20 and 40 = 30
        List<ConsumerBehavior> list = new ArrayList<>();

        list.add(new ConsumerBehavior(
                20, null, null, null,
                0.0, null, null, null, null));

        list.add(new ConsumerBehavior(
                40, null, null, null,
                0.0, null, null, null, null));

        assertEquals(30, processor.getAverageAge(list));
    }
}
