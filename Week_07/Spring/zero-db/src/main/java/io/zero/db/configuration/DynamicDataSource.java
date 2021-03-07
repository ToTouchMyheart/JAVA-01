package io.zero.db.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import io.zero.db.en.DynamicDataSourceGlobal;

/**
 * @Author zhurui
 * @Date 2021/3/7 8:58 下午
 * @Version 1.0
 */
@Component
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Resource
    private MainDataSourceConfiguration mainDataSourceConfiguration;

    @Resource
    private SlaveDataSourceConfiguration slaveDataSourceConfiguration;

    static List<DataSource> readDataSources = new ArrayList<>();

    private int readDataSourceSize;

    private int readDataSourcePollPattern = 0;

    private AtomicLong counter = new AtomicLong(0);

    private static final Long MAX_POOL = Long.MAX_VALUE;

    private final Lock lock = new ReentrantLock();

    @Override
    public void afterPropertiesSet() {
        if (this.mainDataSourceConfiguration == null) {
            throw new IllegalArgumentException("Property 'writeDataSource' is required");
        }
        setDefaultTargetDataSource(mainDataSourceConfiguration.mainDBDataSource());
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DynamicDataSourceGlobal.WRITE.name(), mainDataSourceConfiguration.mainDBDataSource());
        if (readDataSources == null) {
            readDataSources.add(slaveDataSourceConfiguration.slaveDBDataSource());
        } else {
            for (int i = 0; i < readDataSources.size(); i++) {
                targetDataSources.put(DynamicDataSourceGlobal.READ.name() + i, readDataSources.get(i));
            }
            readDataSourceSize = readDataSources.size();
        }
        setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {

        DynamicDataSourceGlobal dynamicDataSourceGlobal = DynamicDataSourceHolder.getDataSource();

        if (dynamicDataSourceGlobal == null || dynamicDataSourceGlobal == DynamicDataSourceGlobal.WRITE
            || readDataSourceSize <= 0) {
            return DynamicDataSourceGlobal.WRITE.name();
        }

        int index = 1;

        if (readDataSourcePollPattern == 1) {
            // 轮询方式
            long currValue = counter.incrementAndGet();
            if ((currValue + 1) >= MAX_POOL) {
                lock.lock();
                try {
                    if ((currValue + 1) >= MAX_POOL) {
                        counter.set(0);
                    }
                } finally {
                    lock.unlock();
                }
            }
            index = (int)(currValue % readDataSourceSize);
        } else {
            // 随机方式
            index = ThreadLocalRandom.current().nextInt(0, readDataSourceSize);
        }
        return dynamicDataSourceGlobal.name() + index;
    }

}
