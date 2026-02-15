package com.msys.water_station.dto.audit;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditRequest {
    private String username_id;
    private Long role_id;
    private String method;
    private String endpoint;
    private String ipAddress;
    private Integer status;

}
