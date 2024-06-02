package com.rlaghlwns.stronglifts.service

import com.rlaghlwns.stronglifts.dto.OneRmRequest
import com.rlaghlwns.stronglifts.model.WorkoutSchedule
import com.rlaghlwns.stronglifts.model.WorkoutDay
import com.rlaghlwns.stronglifts.model.Exercises
import org.springframework.stereotype.Service

@Service
class StrongliftsService {
    fun generateSchedule(request: OneRmRequest, totalWeeks: Int, deload: Boolean): List<WorkoutSchedule> {
        val squat = request.squat1RM * 0.5
        val benchPress = request.benchPress1RM * 0.5
        val barbellRow = request.barbellRow1RM * 0.5
        val overheadPress = request.overheadPress1RM * 0.5
        val deadlift = request.deadlift1RM * 0.5

        val schedules: MutableList<WorkoutSchedule> = ArrayList()

        for (week in 1..totalWeeks) {
            val workouts: MutableList<WorkoutDay> = ArrayList()
            val multiplier = if (deload) 0.8 else 1.0 // Apply 20% reduction if deloading
            for (day in 0..2) {
                val routine = if (day == 1) "B" else "A"
                val exercises = Exercises(
                    ((squat + (week - 1) * 2.5 * 3 + day * 2.5) * multiplier),
                    ((benchPress + (week - 1) * 2.5 * 1.5 + (if (routine == "A") day * 2.5 else 0.0)) * multiplier),
                    ((barbellRow + (week - 1) * 2.5 * 1.5 + (if (routine == "A") day * 2.5 else 0.0)) * multiplier),
                    ((overheadPress + (week - 1) * 2.5 * 1.5 + (if (routine == "B") day * 2.5 else 0.0)) * multiplier),
                    ((deadlift + (week - 1) * 5 + (if (routine == "B") 5 else 0)) * multiplier)
                )
                val workoutDay = WorkoutDay(
                    (if (day == 0) "Monday" else if (day == 1) "Wednesday" else "Friday"),
                    routine,
                    exercises
                )
                workouts.add(workoutDay)
            }
            schedules.add(WorkoutSchedule(week, workouts))
        }
        return schedules
    }
}