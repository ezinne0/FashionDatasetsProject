package fashionrelations.common;

import java.util.List;

public class ConsumerBehaviorIterator implements ConsumerIterator {

    private List<ConsumerBehavior> consumers;
    private int index = 0;

    public ConsumerBehaviorIterator(List<ConsumerBehavior> consumers) {
        this.consumers = consumers;
    }

    @Override
    public boolean hasNext() {
        return index < consumers.size();
    }

    @Override
    public ConsumerBehavior next() {
        return consumers.get(index++);
    }
}
