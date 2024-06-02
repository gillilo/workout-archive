package com.rlaghlwns.stronglifts.model

import lombok.Data

@Data
class WorkoutSchedule(
    var week:Int = 0,
    var workouts: List<WorkoutDay>? = null
)

@Data
class WorkoutDay (
    var day: String? = null,
    var routine: String? = null,
    var exercises: Exercises? = null
)

@Data
class Exercises (
    var squat: Double = 0.0,
    var benchPress: Double = 0.0,
    var barbellRow: Double = 0.0,
    var overheadPress: Double = 0.0,
    var deadlift: Double = 0.0
)