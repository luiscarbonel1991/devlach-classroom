package com.devlach.classroom.schedule.controller;


import com.devlach.classroom.schedule.dto.regular.CreateRegularAvailabilityBatchDTO;
import com.devlach.classroom.schedule.dto.regular.RegularAvailabilityDTO;
import com.devlach.classroom.schedule.dto.regular.UpdateRegularAvailabilityBatchDTO;
import com.devlach.classroom.schedule.dto.weekly.CreateWeeklyAvailabilityBatchDTO;
import com.devlach.classroom.schedule.dto.weekly.UpdateWeeklyAvailabilityBatchDTO;
import com.devlach.classroom.schedule.dto.weekly.WeeklyAvailabilityDTO;
import com.devlach.classroom.schedule.gateway.ScheduleGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/availability")
public class ScheduleController {

    private final ScheduleGateway scheduleGateway;

    public ScheduleController(ScheduleGateway scheduleGateway) {
        this.scheduleGateway = scheduleGateway;
    }

    @GetMapping("/regular")
    public ResponseEntity<List<RegularAvailabilityDTO>> findRegularAvailability(@RequestParam String email) {
        return ResponseEntity.ok(scheduleGateway.findRegularAvailability(email));
    }

    @PostMapping("/regular")
    public ResponseEntity<List<RegularAvailabilityDTO>> createRegularAvailability(@RequestBody CreateRegularAvailabilityBatchDTO batchDTO, @RequestParam String email) {
        return ResponseEntity.ok(scheduleGateway.createRegularAvailability(email, batchDTO));
    }

    @PutMapping("/regular")
    public ResponseEntity<List<RegularAvailabilityDTO>> updateRegularAvailability(@RequestBody UpdateRegularAvailabilityBatchDTO batchDTO, @RequestParam String email) {
        return ResponseEntity.ok(scheduleGateway.updateRegularAvailability(email, batchDTO));
    }

    @DeleteMapping("/regular/{regularAvailabilityId}")
    public ResponseEntity<Void> deleteRegularAvailability(@PathVariable Long regularAvailabilityId, @RequestParam String email) {
        scheduleGateway.deleteRegularAvailability(regularAvailabilityId, email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/weekly")
    public ResponseEntity<List<WeeklyAvailabilityDTO>> findWeeklyAvailability(@RequestParam String start, @RequestParam String end, @RequestParam String email) {
        return ResponseEntity.ok(scheduleGateway.findWeeklyAvailability(start, end , email));
    }

    @PostMapping("/weekly")
    public ResponseEntity<List<WeeklyAvailabilityDTO>> createWeeklyAvailability(@RequestBody CreateWeeklyAvailabilityBatchDTO batchDTO, @RequestParam String email) {
        return ResponseEntity.ok(scheduleGateway.createWeeklyAvailability(email, batchDTO));
    }

    @PutMapping("/weekly")
    public ResponseEntity<List<WeeklyAvailabilityDTO>> updateWeeklyAvailability(@RequestBody UpdateWeeklyAvailabilityBatchDTO batchDTO, @RequestParam String email) {
        return ResponseEntity.ok(scheduleGateway.updateWeeklyAvailability(email, batchDTO));
    }

    @DeleteMapping("/weekly/{weeklyAvailabilityId}")
    public ResponseEntity<Void> deleteWeeklyAvailability(@PathVariable Long weeklyAvailabilityId, @RequestParam String email) {
        scheduleGateway.deleteWeeklyAvailability(weeklyAvailabilityId, email);
        return ResponseEntity.noContent().build();
    }
}
