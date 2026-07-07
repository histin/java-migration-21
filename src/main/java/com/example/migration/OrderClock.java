package com.example.migration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Tier 세분화 검증용: Calendar 없이 인자 없는 new Date()만 사용 → upgrade_09가 Tier A여야 함
public class OrderClock {

    public Date stamp() {
        return new Date();
    }

    public String dayType(int day) {
        String type;
        switch (day) {
            case 1:
            case 7:
                type = "WEEKEND";
                break;
            default:
                type = "WEEKDAY";
                break;
        }
        return type;
    }

    public List<String> stamps(int n) {
        List<String> out = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            out.add("t" + i);
        }
        return out;
    }
}
