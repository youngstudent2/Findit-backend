package cn.edu.nju.FindIt.utils.DTO;

import lombok.Data;

@Data
public class TodayDataDTO {
    Integer todayLostCount;
    Integer todayFindCount;
    Integer totalLostCount;
    Integer totalFindCount;
    Integer finishCount;
    Integer userCount;
}
