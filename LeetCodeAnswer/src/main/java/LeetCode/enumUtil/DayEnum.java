package LeetCode.enumUtil;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum DayEnum {
    MONDAY(1,"星期一"),
    TUESDAY(2,"星期二"),
    WEDNESDAY(3,"星期三"),
    THURSDAY(4,"星期四"),
    FRIDAY(5,"星期五"),
    SATURDAY(6,"星期六"),
    SUNDAY(0,"星期天"),
    ;
    private int id;
    private String desc;

    DayEnum(int id, String desc){
        this.desc = desc;
        this.id = id;
    }

    public static Map<Integer,DayEnum> map = new HashMap<>();

    static {
        for(DayEnum dayEnum : values()){
            map.put(dayEnum.getId(),dayEnum);
        }
    }

    public static DayEnum getById(int id){
        return map.get(id);
    }
}
