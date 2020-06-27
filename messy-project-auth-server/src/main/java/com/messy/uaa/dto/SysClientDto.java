package com.messy.uaa.dto;

import com.messy.uaa.model.SysClient;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper=true)
public class SysClientDto extends SysClient {

    private static final long serialVersionUID = 1475637288060027265L;

    private List<Long> permissionIds;

    private Set<Long> serviceIds;

}
