package util;

import java.util.concurrent.atomic.AtomicLong;

/** Generates simple unique serial numbers for phone records. */
public final class IdGenerator {
    private static final AtomicLong NEXT = new AtomicLong(1);
    private IdGenerator() {}

    public static String generateId() {
        return String.format("PH-%06d", NEXT.getAndIncrement());
    }

    public static void updateSequence(long next) {
        NEXT.updateAndGet(current -> Math.max(current, next));
    }
}
