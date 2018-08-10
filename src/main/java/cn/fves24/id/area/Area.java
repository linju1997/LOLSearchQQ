package cn.fves24.id.area;

/**
 * 大区
 * @author Administrator
 */

public enum Area {
    /**
     * AREAN(ID, NAME)
     */
    AREA1(1, "艾欧尼亚"),
    AREA2(2, "比尔吉沃特"),
    AREA3(3, "祖安(可能查不到)"),
    AREA4(4, "诺克萨斯"),
    AREA5(5, "班德尔城"),
    AREA6(6, "德玛西亚"),
    AREA7(7, "皮尔特沃夫(可能查不到)"),
    AREA8(8, "战争学院(可能查不到)"),
    AREA9(9, "弗雷尔卓德(可能查不到)"),
    AREA10(10, "巨神峰(可能查不到)"),
    AREA11(11, "雷瑟守备"),
    AREA12(12, "无畏先锋"),
    AREA13(13, "裁决之地"),
    AREA14(14, "黑色玫瑰"),
    AREA15(15, "暗影岛"),
    AREA16(16, "恕瑞玛"),
    AREA17(17, "钢铁烈阳"),
    AREA18(18, "水晶之痕"),
    AREA19(19, "均衡教派(可能查不到)"),
    AREA20(20, "扭曲丛林"),
    AREA21(21, "教育网专区"),
    AREA22(22, "影流"),
    AREA23(23, "守望之海"),
    AREA24(24, "征服之海"),
    AREA25(25, "卡拉曼达"),
    AREA26(26, "巨龙之巢"),
    AREA27(27, "皮城警备"),
    AREA30(30, "男爵领域"),
    AREA31(31, "峡谷之巅");

    private int id;
    private String name;

    Area(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static String getName(int i) {
        for (Area area : values()) {
            if (area.getId() == i) {
                return area.getName();
            }
        }
        return "";
    }
    public static int getId(String name) {
        for (Area area : values()) {
            if (area.getName().equals(name)) {
                return area.getId();
            }
        }
        return 1;
    }
}
