package com.jopov.schoolportal.common.queue;

import com.jopov.schoolportal.common.queue.exception.QueueException;
import com.jopov.schoolportal.common.models.persons.Pupil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
class PupilQueueTest {

    @Test
    void multithreadedTesting() {
        PupilQueue expected = new PupilQueue();
        List<Pupil> pupils = new ArrayList<>();
        List<Pupil> pupils1 = new ArrayList<>();
        for (int i  = 0; i <= 31; i++) {
            Pupil pupil = new Pupil("asd" + i, "asdaf" + i, "asdfgha" + i, "M", LocalDate.now().minusYears(10));
            Pupil pupil1 = new Pupil("asd" + i, "asdaf" + i, "asdfgha" + i, "M", LocalDate.now().minusYears(9));
            pupils.add(pupil);
            pupils1.add(pupil1);
        }
        MyThead myThead = new MyThead(pupils, expected, 1);
        MyThead myThead1 = new MyThead(pupils, expected, 2);
        MyThead myThead2 = new MyThead(pupils1, expected, 3);
        MyThead myThead3 = new MyThead(pupils1, expected, 4);

        myThead.start();
        myThead1.start();
        myThead2.start();
        myThead3.start();
        try {
            myThead.join();
            myThead1.join();
            myThead2.join();
            myThead3.join();
        } catch (InterruptedException ignored) {
        }
        Assertions.assertEquals(60, expected.getQueue().size());
    }

    @Test
    void getNoPlaceInClassException() {
        PupilQueue queue = new PupilQueue();
        for (int i  = 0; i <= 30; i++) {
            Pupil pupil = new Pupil("asd" + i, "asdaf" + i, "asdfgha" + i, "M", LocalDate.now().minusYears(11));
            try {
                queue.putPupilInAQueue(pupil);
            } catch (QueueException.NoPlaceInClassException | QueueException.BeyondAgeLimitsException exception) {
                Assertions.assertEquals(QueueException.NoPlaceInClassException.class, exception.getClass());
            }
        }
        Assertions.assertEquals(30, queue.getQueue().size());
    }

    @Test
    void getBeyondAgeLimitsException() {
        PupilQueue expected = new PupilQueue();
        Pupil pupil = new Pupil("asd", "asdaf", "asdfgha", "M", LocalDate.now().minusYears(7));
        try {
            expected.putPupilInAQueue(pupil);
        } catch (QueueException.NoPlaceInClassException | QueueException.BeyondAgeLimitsException exception) {
            Assertions.assertEquals(QueueException.BeyondAgeLimitsException.class, exception.getClass());
        }
        Assertions.assertEquals(0, expected.getQueue().size());
    }

    class MyThead extends Thread {

        List<Pupil> pupils;
        PupilQueue expected;
        int number;
        int a;

        public MyThead(List<Pupil> pupils, PupilQueue expected, int number) {
            this.pupils = pupils;
            this.expected = expected;
            this.number = number;
        }




        @Override
        public void run() {
            for (Pupil pupil : pupils) {
                try {
                    expected.putPupilInAQueue(pupil);
                    a++;
                } catch (QueueException.NoPlaceInClassException ignored) {
                }
            }
        }
    }
}