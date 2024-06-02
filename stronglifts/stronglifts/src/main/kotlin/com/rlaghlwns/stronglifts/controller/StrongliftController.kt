package com.rlaghlwns.stronglifts.controller

import com.rlaghlwns.stronglifts.dto.OneRmRequest
import com.rlaghlwns.stronglifts.model.WorkoutSchedule
import com.rlaghlwns.stronglifts.service.StrongliftsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/stronglifts")
class StrongliftsController {

    @Autowired
    final lateinit var strongliftsService: StrongliftsService

    @PostMapping("/schedule")
    fun getWorkoutSchedule(@RequestBody request: OneRmRequest): List<WorkoutSchedule> {
        return strongliftsService.generateSchedule(request, 12, false)
    }

    @PostMapping("/schedule/{week}")
    fun getWeeklySchedule(@RequestBody request: OneRmRequest, @PathVariable week: Int): WorkoutSchedule {
        require(!(week < 1 || week > 12)) { "Week must be between 1 and 12" }
        val schedules: List<WorkoutSchedule> = strongliftsService.generateSchedule(request, 12, false)
        return schedules[week - 1]
    }

    @PostMapping("/deload/{week}")
    fun getDeloadSchedule(@RequestBody request: OneRmRequest, @PathVariable week: Int): WorkoutSchedule {
        require(!(week < 1 || week > 12)) { "Week must be between 1 and 12" }
        val schedules: List<WorkoutSchedule> = strongliftsService.generateSchedule(request, 12, true)
        return schedules[week - 1]
    }
}