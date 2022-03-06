package com.mixer.raw;

import java.util.HashMap;
import java.util.Map;

public final class Index {

    private static Index index;
    private final Map<Long, Long> rowIndex;
    private long totalNumberOfRows = 0;

    private Index() {
        this.rowIndex = new HashMap<>();
    }

    public static Index getInstance() {
        if (index == null) {
            index = new Index();
        }
        return index;
    }

    public void add(long bytePosition) {
        this.rowIndex.put(this.totalNumberOfRows, bytePosition);
        this.totalNumberOfRows++;
    }

    public long getBytePosition(long row) {
        return this.rowIndex.getOrDefault(row, -1L);
    }

    public void remove(long row) {
        this.rowIndex.remove(row);
        this.totalNumberOfRows--;
    }

    public long getTotalNumberOfRows() {
        return this.totalNumberOfRows;
    }

    @Override
    public String toString() {
        return "Index{" +
                "rowIndex=" + rowIndex +
                ", totalNumberOfRows=" + totalNumberOfRows +
                '}';
    }
}
