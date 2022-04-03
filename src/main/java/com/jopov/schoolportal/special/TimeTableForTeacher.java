package com.jopov.schoolportal.special;

import com.jopov.schoolportal.common.models.schoolatribute.DayTimeTable;

import java.util.List;

public interface TimeTableForTeacher{
    DayTimeTable getDayTimeTable();
    List<DayTimeTable> getWeekTimeTable();
}
