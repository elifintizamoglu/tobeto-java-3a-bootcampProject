package com.tobeto.bootcampProject.business.responses.update.blacklist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBlacklistResponse {
    private int id;
    private String reason;
    private LocalDateTime date;
    private int applicantId;
}
