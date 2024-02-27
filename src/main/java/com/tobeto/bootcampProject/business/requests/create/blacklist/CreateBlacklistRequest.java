package com.tobeto.bootcampProject.business.requests.create.blacklist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBlacklistRequest {
    private String reason;
    private LocalDateTime date;
    private int applicant_id;
}
