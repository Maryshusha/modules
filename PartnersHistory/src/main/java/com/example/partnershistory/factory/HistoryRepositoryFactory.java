package com.example.partnershistory.factory;

import com.example.partnershistory.repository.HistoryRepository;

public class HistoryRepositoryFactory {
    private static HistoryRepository INSTANCE;

    public static HistoryRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HistoryRepository();
        }
        return INSTANCE;
    }
}
