package com.rlaghlwns.stronglifts.dto

import lombok.Data

@Data
class OneRmRequest (
    val squat1RM: Double = 0.0,
    val benchPress1RM: Double = 0.0,
    val barbellRow1RM: Double = 0.0,
    val overheadPress1RM: Double = 0.0,
    val deadlift1RM: Double = 0.0,
    val week:Int? = 0 // For the deloading specific request
)