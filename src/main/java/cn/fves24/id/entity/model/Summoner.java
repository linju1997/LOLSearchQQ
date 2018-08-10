package cn.fves24.id.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Summoner {
    @NotEmpty(message = "名称不能为空")
    private String name;
    @NotNull(message = "大区不能为空")
    private Integer areaId;
    @JsonIgnore
    private String qquin;
    @JsonIgnore
    private Long lolId;
    private String qq;
    @JsonIgnore
    private String gameId;
    private String areaName;
}
