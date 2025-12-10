package fashionrelations.common;

public interface ConsumerIterator {
    boolean hasNext();
    ConsumerBehavior next();
}
