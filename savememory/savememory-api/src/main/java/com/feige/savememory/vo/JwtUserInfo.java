package com.feige.savememory.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtUserInfo {
    private Long uid;
    private String username;
    private String identity;
    private Boolean blocked;
}
