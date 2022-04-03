package com.jopov.schoolportal.common.queue.exception;

public class QueueException extends RuntimeException {

    public static class NoPlaceInClassException extends RuntimeException {

        public NoPlaceInClassException(int classNumber) {
            super("В " + classNumber + " классе нет места");
        }
    }

    public static class BeyondAgeLimitsException extends RuntimeException {
        public BeyondAgeLimitsException() {
            super("Неподходящий возраст");
        }
    }
}



