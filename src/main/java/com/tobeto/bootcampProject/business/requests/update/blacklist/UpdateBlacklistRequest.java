package com.tobeto.bootcampProject.business.requests.update.blacklist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBlacklistRequest {
    private String reason;
    private LocalDateTime date;
    private int applicantId;
}
