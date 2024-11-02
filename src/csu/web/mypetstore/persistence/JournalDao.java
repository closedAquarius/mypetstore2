package csu.web.mypetstore.persistence;

import csu.web.mypetstore.domain.Journal;

import java.util.List;

public interface JournalDao
{
    void updateJournal(String userId, String description, String date, String color);

    public List<Journal> getAllJournals(String userId);
}
