package com.example.application.models;

public class MyFile {

    private long totalSize = 0;
    private long minSize = Long.MAX_VALUE;
    private long maxSize = Long.MIN_VALUE;
    private int count = 0;

    public void add(long size) {
        totalSize += size;
        minSize = Math.min(minSize, size);
        maxSize = Math.max(maxSize, size);
        count++;
    }

    public void combine(MyFile other) {
        totalSize += other.totalSize;
        minSize = Math.min(minSize, other.minSize);
        maxSize = Math.max(maxSize, other.maxSize);
        count += other.count;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public long getMinSize() {
        return minSize;
    }

    public long getMaxSize() {
        return maxSize;
    }

    public int getCount() {
        return count;
    }

    public long getAverageSize() {
        if(count == 0)
            return 0;
        else
            return totalSize/count;
    }
}
