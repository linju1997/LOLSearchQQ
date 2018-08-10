package cn.fves24.id.util;

public enum DuoWanArea {
    /**
     * DuoWan（id，name,areaName)
     */
    DuoWanArea1(1,"艾欧尼亚","电信一"),

    DuoWanArea2(2,"比尔吉沃特","网通一"),

    DuoWanArea3(3,"祖安","电信二"),

    DuoWanArea4(4,"诺克萨斯","电信三"),

    DuoWanArea5(5,"班德尔城","电信四"),

    DuoWanArea6(6,"德玛西亚","网通二"),

    DuoWanArea7(7,"皮尔特沃夫","电信五"),

    DuoWanArea8(8,"战争学院","电信六"),

    DuoWanArea9(9,"弗雷尔卓德","网通三"),

    DuoWanArea10(10,"巨神峰","电信七"),

    DuoWanArea11(11,"雷瑟守备","电信八"),

    DuoWanArea12(12,"无畏先锋","网通四"),

    DuoWanArea13(13,"裁决之地","电信九"),

    DuoWanArea14(14,"黑色玫瑰","电信十"),

    DuoWanArea15(15,"暗影岛","电信十一"),

    DuoWanArea16(16,"恕瑞玛","网通五"),

    DuoWanArea17(17,"钢铁烈阳","电信十二"),

    DuoWanArea18(18,"水晶之痕","电信十四"),

    DuoWanArea19(19,"均衡教派","电信十三"),

    DuoWanArea20(20,"扭曲丛林","网通六"),

    DuoWanArea21(21,"教育网专区","教育一"),

    DuoWanArea22(22,"影流","电信十五"),

    DuoWanArea23(23,"守望之海","电信十六"),

    DuoWanArea24(24,"征服之海","电信十七"),

    DuoWanArea25(25,"卡拉曼达","电信十八"),

    DuoWanArea26(26,"巨龙之巢","网通七"),

    DuoWanArea27(27,"皮城警备","电信十九"),

    DuoWanArea28(30,"男爵领域","全网一"),

    DuoWanArea29(31,"峡谷之巅","电信一");

    /**
     * id
     */
    private int id;
    /**
     * name
     */
    private String name;
    /**
     * areaName
     */
    private String gameZone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGameZone() {
        return gameZone;
    }

    public void setGameZone(String gameZone) {
        this.gameZone = gameZone;
    }

    DuoWanArea(int id, String name, String gameZone) {
        this.id = id;
        this.name = name;
        this.gameZone = gameZone;
    }

    public static String getGameZoneByid(int id) {
        for (DuoWanArea duoWanArea : values()) {
            if (duoWanArea.getId() == id) {
                return duoWanArea.getGameZone();
            }
        }
        return null;
    }
}
