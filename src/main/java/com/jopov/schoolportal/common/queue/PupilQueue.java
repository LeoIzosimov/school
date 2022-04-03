package com.jopov.schoolportal.common.queue;


import com.jopov.schoolportal.common.models.persons.Pupil;
import com.jopov.schoolportal.common.queue.exception.QueueException;
import lombok.Getter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

@Getter
public class PupilQueue {

    private final BlockingQueue<Map<Integer, Pupil>> queue = new LinkedBlockingQueue<>();
    private static final Map<Integer, Integer> valid = new ConcurrentHashMap<>();


    static {
        valid.put(1, 0);
        valid.put(2, 0);
        valid.put(3, 0);
        valid.put(4, 0);
        valid.put(5, 0);
        valid.put(6, 0);
        valid.put(7, 0);
        valid.put(8, 0);
        valid.put(9, 0);
        valid.put(10, 0);
        valid.put(11, 0);

    }

    public void putPupilInAQueue(Pupil pupil) {
        Map<Integer, Pupil> map = new HashMap<>();
        int classNumber = LocalDate.now().getYear() - pupil.getBirthday().getYear() - 7;
        if (classNumber > 11 || classNumber < 1) {
            throw new QueueException.BeyondAgeLimitsException();
        }
        map.put(classNumber, pupil);

        if (valid.get(classNumber) == 30) {
            throw new QueueException.NoPlaceInClassException(classNumber);

        } else {
            valid.computeIfPresent(classNumber, (a, b) -> b + 1);
        }
        queue.add(map);

    }
}
